package com.cuong.phonestore.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.cuong.phonestore.model.OnepayCode;
import com.cuong.phonestore.model.OnepayRequest;
import com.cuong.phonestore.model.OnepayWorldCode;
import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.services.CartService;
import com.cuong.phonestore.services.OnepayRequestService;
import com.cuong.phonestore.services.OrderService;
import com.cuong.phonestore.services.UserService;
import com.cuong.phonestore.services.impl.ExChangeRateService;
import com.cuong.phonestore.services.impl.OnepayPaymentService;
import com.cuong.phonestore.services.impl.OnepayWorldPaymentService;
import com.cuong.phonestore.utils.Config;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/order")
public class OrderController {
	private OrderService orderService;
	private CartService cartService;
	private OnepayRequestService onepayRequestService;
	private UserService userService;
	
	@Autowired
	public OrderController(OrderService orderService, CartService cartService,
			ExChangeRateService exChangeRateService, OnepayRequestService onepayRequestService, UserService userService) {
		super();
		this.orderService = orderService;
		this.cartService = cartService;
		this.onepayRequestService = onepayRequestService;
		this.userService = userService;
	}


	/**
	 * 
	 * @param model
	 * @param principal
	 * @return view checkout
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value="")
	public String index(Model model, Principal principal, HttpSession session) {
		ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
		if(cart == null || cart.isEmpty()) {
			return "redirect:/";
		} else {
			model.addAttribute("totalPrice", cartService.totalPrice(cart));
			model.addAttribute("order", new Order());
			model.addAttribute("cart", cart);
			model.addAttribute("profile", userService.finUserByUsername(principal.getName()));
			return "checkout";
		}
		
	}
	

    /**
     * save order
     * @param order
     * @param session
     * @param principal
     * @param model
     * @param request
     * @return
     * @ModelAttribute("order")
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     * @throws MalformedURLException 
     */
    @PostMapping(value = "/checkout")
    @ResponseBody
    public String postOrder(@RequestBody Order order, HttpSession session, Principal principal, Model model, HttpServletRequest request) throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
    	String orderID = String.valueOf(new Date().getTime());
    	order.setOrderID(orderID);
    	order.setShipName("Name of Shipper");//change
    	String urlPayment = "";
    	if(session.getAttribute("cart") != null) {
    		@SuppressWarnings("unchecked")
			ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
    		Long totalPriceCart = cartService.totalPrice(cart);
    		order.setPurchaseAmount(totalPriceCart);
    		if(order.getPayment_method().trim().equals("paymentOffline")) {
    			order.setPayment_method("Thanh Toán khi nhận hàng");
    			orderService.postOrder(principal, order, cart);
    			session.removeAttribute("cart");
    	    	session.removeAttribute("amount");
    	    	model.addAttribute("mess","đặt hàng thành công!");
    			return "/historyOrder/0/20";
    		}
    		Order saveOrder = orderService.postOrder(principal, order, cart);
    		saveOrder.setOrderDetailsList(null);
    		if(order.getPayment_method().trim().equals("onepayW")) {
    			saveOrder.setPayment_method("onepay Quốc Tế");
    			OnepayWorldPaymentService vpc = new OnepayWorldPaymentService();
    			OnepayWorldCode onepayWorldCode = new OnepayWorldCode();
    			OnepayRequest onepayRequest = new OnepayRequest();
    			onepayRequest.setIpAddress("192.168.1.3");
    			onepayRequest.setPurchaseAmount(totalPriceCart);
    			onepayRequest.setReturnUrl(onepayWorldCode.ReturnURL);
    			onepayRequest.setTitle(Config.TITLE);
    			onepayRequest.setVpcVersion(Config.VPC_VERSION);
    			onepayRequest.setVpcAccessCode(onepayWorldCode.AccessCode);
    			onepayRequest.setVpcCommand(Config.VPC_COMMAND);
    			onepayRequest.setVpcCurrency(Config.VPC_CURRENCY);
    			onepayRequest.setVpcLocale(Config.VPC_LOCALE);
    			onepayRequest.setVpcMerchant(onepayWorldCode.Merchant);
    			onepayRequest.setSecureSecret(onepayWorldCode.SECURE_SECRET);
    			Map <String, String> postData = new HashMap<String, String>();
    	        postData.put("SECURE_SECRET", onepayWorldCode.SECURE_SECRET);
    	        postData.put("Title", Config.TITLE);
    	        postData.put("vpc_Locale", Config.VPC_LOCALE);
    	        postData.put("vpc_Version", Config.VPC_VERSION);
    	        postData.put("vpc_Currency", Config.VPC_CURRENCY);
    	        postData.put("vpc_Command", Config.VPC_COMMAND);
    	        postData.put("vpc_Merchant", onepayWorldCode.Merchant);
    	        postData.put("vpc_AccessCode", onepayWorldCode.AccessCode);
    	        postData.put("vpc_MerchTxnRef", saveOrder.getOrderID());
    	        postData.put("vpc_Amount", String.valueOf(totalPriceCart*100));
    	        postData.put("vpc_ReturnURL", onepayWorldCode.ReturnURL);
    	        postData.put("vpc_OrderInfo", saveOrder.getOrderID());
    	        postData.put("vpc_TicketNo", "192.168.1.3");
    	        postData.put("AgainLink", "http://onepay.vn");
    	       
    	        //thông tin khách hàng
    	        postData.put("vpc_SHIP_Street01", "");
    	        postData.put("vpc_SHIP_Provice", "");
    	        postData.put("vpc_SHIP_City", "");
    	        postData.put("vpc_SHIP_Country", "");
    	        postData.put("vpc_Customer_Phone", "");
    	        postData.put("vpc_Customer_Email", "");
    	        postData.put("vpc_Customer_Id", "");
    	       
    	    	if (onepayWorldCode.SECURE_SECRET != null && onepayWorldCode.SECURE_SECRET.length() > 0) {
    	            String secureHash = vpc.hashAllFields(postData);
    	            postData.put("vpc_SecureHash", secureHash);
    	            StringBuffer buf = new StringBuffer();
    	            buf.append(onepayWorldCode.VPCRequest).append('?');
    	            vpc.appendQueryFields(buf, postData);
    	            urlPayment = buf.toString();
    	            //save request
    	            onepayRequest.setUrlRequest(urlPayment);
    	            onepayRequest.setSecure_Hash(secureHash);
    	            OnepayRequest onepayRequest2 = onepayRequestService.save(onepayRequest);
        			saveOrder.setOnepayRequest(onepayRequest2);
        			orderService.editOrder(saveOrder);
    	    	}
    		} else if(order.getPayment_method().trim().equals("onepay")) {
    			saveOrder.setPayment_method("onepay Nội Địa");
    			OnepayPaymentService vpc_onepay = new OnepayPaymentService();
    			OnepayCode onepayCode = new OnepayCode();
    			OnepayRequest onepayRequest = new OnepayRequest();
    			onepayRequest.setIpAddress("192.168.1.3");
    			onepayRequest.setPurchaseAmount(totalPriceCart);
    			onepayRequest.setReturnUrl(onepayCode.ReturnURL);
    			onepayRequest.setTitle(Config.TITLE);
    			onepayRequest.setVpcVersion(Config.VPC_VERSION);
    			onepayRequest.setVpcAccessCode(onepayCode.AccessCode);
    			onepayRequest.setVpcCommand(Config.VPC_COMMAND);
    			onepayRequest.setVpcCurrency(Config.VPC_CURRENCY);
    			onepayRequest.setVpcLocale(Config.VPC_LOCALE);
    			onepayRequest.setVpcMerchant(onepayCode.Merchant);
    			onepayRequest.setSecureSecret(onepayCode.SECURE_SECRET);
    			Map <String, String> postData = new HashMap<String, String>();
    	        postData.put("SECURE_SECRET", onepayCode.SECURE_SECRET);
    	        postData.put("Title", Config.TITLE);
    	        postData.put("vpc_Locale", Config.VPC_LOCALE);
    	        postData.put("vpc_Version", Config.VPC_VERSION);
    	        postData.put("vpc_Currency", Config.VPC_CURRENCY);
    	        postData.put("vpc_Command", Config.VPC_COMMAND);
    	        postData.put("vpc_Merchant", onepayCode.Merchant);
    	        postData.put("vpc_AccessCode", onepayCode.AccessCode);
    	        postData.put("vpc_MerchTxnRef", saveOrder.getOrderID());
    	        postData.put("vpc_OrderInfo", onepayCode.AccessCode);
    	        postData.put("vpc_Amount", String.valueOf(totalPriceCart*100));
    	        postData.put("vpc_ReturnURL", onepayCode.ReturnURL);
    	        postData.put("vpc_OrderInfo", saveOrder.getOrderID());
    	        postData.put("vpc_TicketNo", "192.168.1.3");
    	        postData.put("AgainLink", "http://onepay.vn");
    	       
    	        //thông tin khách hàng
    	        postData.put("vpc_SHIP_Street01", "");
    	        postData.put("vpc_SHIP_Provice", "");
    	        postData.put("vpc_SHIP_City", "");
    	        postData.put("vpc_SHIP_Country", "");
    	        postData.put("vpc_Customer_Phone", "");
    	        postData.put("vpc_Customer_Email", "");
    	        postData.put("vpc_Customer_Id", "");
    	        
    	       
    	    	if (onepayCode.SECURE_SECRET != null && onepayCode.SECURE_SECRET.length() > 0) {
    	            String secureHash = vpc_onepay.hashAllFields(postData);
    	            postData.put("vpc_SecureHash", secureHash);
    	            StringBuffer buf = new StringBuffer();
    	            buf.append(onepayCode.VPCRequest).append('?');
    	            vpc_onepay.appendQueryFields(buf, postData);
    	            urlPayment = buf.toString();
    	            onepayRequest.setUrlRequest(urlPayment);
    	            onepayRequest.setSecure_Hash(secureHash);
    	            OnepayRequest onepayRequest2 = onepayRequestService.save(onepayRequest);
        			saveOrder.setOnepayRequest(onepayRequest2);
        			orderService.editOrder(saveOrder);
    	    	}
    		}
    	}
    	session.removeAttribute("cart");
    	session.removeAttribute("amount");
    	return urlPayment;
    }

}
