package com.cuong.phonestore.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuong.phonestore.model.OnepayResponse;
import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.services.OnepayResponseService;
import com.cuong.phonestore.services.OrderService;
import com.cuong.phonestore.services.impl.OnepayPaymentService;
import com.cuong.phonestore.services.impl.OnepayWorldPaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	private OnepayWorldPaymentService onepayWorldPaymentService;
	private OnepayPaymentService onepayPaymentService;
	private OrderService orderService;
	private OnepayResponseService onepayResponseService;
	
	@Autowired
	public PaymentController(OnepayWorldPaymentService onepayWorldPaymentService,
			OnepayPaymentService onepayPaymentService, OrderService orderService,
			OnepayResponseService onepayResponseService) {
		super();
		this.onepayWorldPaymentService = onepayWorldPaymentService;
		this.onepayPaymentService = onepayPaymentService;
		this.orderService = orderService;
		this.onepayResponseService = onepayResponseService;
	}

	@RequestMapping(value="/OnepayWorld/result", method=RequestMethod.GET)
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public String paymentOnepayWorld(HttpServletRequest request, Model model) {
		Map fields = new HashMap();
	    for (Enumeration enum1 = request.getParameterNames(); enum1.hasMoreElements(); ) {
	        String fieldName = (String) enum1.nextElement();
	        String fieldValue = request.getParameter(fieldName);
	        if ((fieldValue != null) && (fieldValue.length() > 0)) {
	            fields.put(fieldName, fieldValue);
	        }
	    }
	    String vpc_Txn_Secure_Hash = onepayWorldPaymentService.null2unknown((String) fields.remove("vpc_SecureHash"));
	    String hashValidated = null;
	    if (fields.get("vpc_TxnResponseCode") != null || fields.get("vpc_TxnResponseCode") != "No Value Returned") {
	        String secureHash = onepayWorldPaymentService.hashAllFields(fields);
	        if (vpc_Txn_Secure_Hash.equalsIgnoreCase(secureHash)) {
	            hashValidated = "CORRECT";
	        } else {
	            hashValidated = "INVALID HASH";
	        }
	    } else {
	        hashValidated = "INVALID HASH";
	    }

	    String title = onepayWorldPaymentService.null2unknown((String) fields.get("Title"));
	    String amount = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_Amount"));
	    String message = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_Message"));
	    String orderInfo = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_OrderInfo"));
	    String merchantID = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_Merchant"));
	    String merchTxnRef = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_MerchTxnRef"));
	    String transactionNo = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_TransactionNo"));
	    String txnResponseCode = onepayWorldPaymentService.null2unknown((String) fields.get("vpc_TxnResponseCode"));
	    String transStatus = "";
	    Order order = orderService.getOrderById(merchTxnRef);
	    if(order.getPaid() == 0) {
	    	transStatus = "Giao dịch thành công";
	    } else {
			if (hashValidated.equals("CORRECT") && txnResponseCode.equals("0")) {
				transStatus = "Giao dịch thành công";
				//save response
				OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle(title);
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(0);
				orderService.editOrder(order);
		       
		    } else if (hashValidated.equals("INVALID HASH") && txnResponseCode.equals("0")) {
				transStatus = "Giao dịch Pendding";
				//save response
				OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle(title);
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(-2);
				orderService.editOrder(order);
		    } else {
		        transStatus = "Giao dịch thất bại";
		      //save response
				OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle(title);
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(-3);
				orderService.editOrder(order);
		    }
	    }
		
		model.addAttribute("txnResponseCode",txnResponseCode);
		model.addAttribute("transStatus", transStatus);
		model.addAttribute("orderDate", order.getOrderDate());
		model.addAttribute("merchTxnRef", merchTxnRef);
		model.addAttribute("purchaseAmount", amount);
		model.addAttribute("transactionNo",transactionNo);
		return "paymentResult";
	}
	
	@RequestMapping(value="/Onepay/result", method=RequestMethod.GET)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String paymentOnepay(HttpServletRequest request, Model model) {
		Map fields = new HashMap();
	    for (Enumeration enum1 = request.getParameterNames(); enum1.hasMoreElements(); ) {
	        String fieldName = (String) enum1.nextElement();
	        String fieldValue = request.getParameter(fieldName);
	        if ((fieldValue != null) && (fieldValue.length() > 0)) {
	            fields.put(fieldName, fieldValue);
	        }
	    }
	    String vpc_Txn_Secure_Hash = onepayPaymentService.null2unknown((String) fields.remove("vpc_SecureHash"));
	    String hashValidated = null;
	    if (fields.get("vpc_TxnResponseCode") != null || fields.get("vpc_TxnResponseCode") != "No Value Returned") {
	        String secureHash = onepayPaymentService.hashAllFields(fields);
	        if (vpc_Txn_Secure_Hash.equalsIgnoreCase(secureHash)) {
	            hashValidated = "CORRECT";
	        } else {
	            hashValidated = "INVALID HASH";
	        }
	    } else {
	        hashValidated = "INVALID HASH";
	    }
	    String amount = onepayPaymentService.null2unknown((String) fields.get("vpc_Amount"));
	    String message = onepayPaymentService.null2unknown((String) fields.get("vpc_Message"));
	    String orderInfo = onepayPaymentService.null2unknown((String) fields.get("vpc_OrderInfo"));
	    String merchantID = onepayPaymentService.null2unknown((String) fields.get("vpc_Merchant"));
	    String merchTxnRef = onepayPaymentService.null2unknown((String) fields.get("vpc_MerchTxnRef"));
	    String transactionNo = onepayPaymentService.null2unknown((String) fields.get("vpc_TransactionNo"));
	    String txnResponseCode = onepayPaymentService.null2unknown((String) fields.get("vpc_TxnResponseCode"));

	    String transStatus = "";
	    Order order = orderService.getOrderById(merchTxnRef);
	    if(order.getPaid() == 0) {
	    	transStatus = "Giao dịch thành công";
	    } else {
		    if (hashValidated.equals("CORRECT") && txnResponseCode.equals("0")) {
		    	transStatus = "Giao dịch thành công";
		    	OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle("Payment Result");
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(0);
				orderService.editOrder(order);
		        
		    } else if (hashValidated.equals("INVALID HASH") && txnResponseCode.equals("0")) {
				transStatus = "Giao dịch Pendding";
				OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle("Payment Result");
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(-2);
				orderService.editOrder(order);
		    } else {
		        transStatus = "Giao dịch thất bại";
		        OnepayResponse onepayResponse = new OnepayResponse();
				onepayResponse.setTransactionID(transactionNo);
				onepayResponse.setResponseCode(Integer.valueOf(txnResponseCode));
				onepayResponse.setOrderInfo(orderInfo);
				onepayResponse.setPurchaseAmount(Long.valueOf(amount)/100);
				onepayResponse.setSecure_Hash(vpc_Txn_Secure_Hash);
				onepayResponse.setMerchantID(merchantID);
				onepayResponse.setTitle("Payment Result");
				onepayResponse.setMessage(message);
				onepayResponse.setTransStatus(transStatus);
				OnepayResponse onepayResponse2 = onepayResponseService.save(onepayResponse);
				order.setOnepayResponse(onepayResponse2);
				order.setPaid(-3);
				orderService.editOrder(order);
		    }
	    }
		
		model.addAttribute("txnResponseCode",txnResponseCode);
		model.addAttribute("transStatus", transStatus);
		model.addAttribute("orderDate", order.getOrderDate());
		model.addAttribute("merchTxnRef", merchTxnRef);
		model.addAttribute("purchaseAmount", amount);
		model.addAttribute("transactionNo",transactionNo);
		return "paymentResult";
	}
	
}
