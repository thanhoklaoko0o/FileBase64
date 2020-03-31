<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="bean" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt hàng</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<article>
	<div class="container" style="padding-bottom: 2%;">
		<div class="row mt-4 mb-5">
			<div class="col-md-7">
				<div class="heading">
					<h2 class="display-4 text-heading">Đặt hàng</h2>
				</div>
			</div>
			
			<!-- <div class="ml-3 col-md-5 mt-3">	
				<div class="form-group" style="margin-left: 25px; margin-top: 10px;">
					<input placeholder="Mã giảm giá" class="form-control col-sm-2" style="margin-right: 20px; width:50%;">
					<button class="btn btn-primary">Áp dụng</button>
				</div>
			</div> -->
		</div>
	</div>	
</article>

<section id="check-out-content">
	<div class="container">
		<div class="row">
		<form:form id="customerInfoForm" method="post" action="#" modelAttribute="order">
			<div class="col-md-4 billing-details">
				<h3>Thông tin người đặt hàng</h3>
				<div class="form-group">
					<a class="btn btn-small btn-default"><i class="fa fa-plus-circle"></i>Thêm địa chỉ nhận hàng</a>
				</div>
				<div class="form-group">
					<label class="small">Tên người nhận hàng:</label>
					<form:input value="${profile.fullName}" id="recipientName" class="form-control" path="recipientName" placeholder="Tên người nhận hàng *"/>
				</div>
				<div class="form-group">
					<label class="small">Địa chỉ email:</label>
					<form:input value="${profile.email}" id="email" path="email" class="form-control" placeholder="Email *"/>
				</div>
				<div class="form-group">
					<label class="small">Địa chỉ nhận hàng:</label>
					<input value="${profile.address}" id="shipAddress" name="shipAddress" class="form-control" placeholder="Số nhà, tên đường *">
				</div>									
				<div class="form-group">
					<label class="small">Tỉnh/Thành phố:</label>
					<select id="ddlProvince" class="form-control" name="city">
						<option>--Chọn--</option>
					</select>
					<%-- <form:input  class="form-control" path="city" placeholder="Tỉnh/Thành phố *"/> --%>
				</div>
				<div class="form-group">
					<label class="small">Quận/Huyện:</label>
					<select id="ddlDistrict" class="form-control" name="district">
						<option>--Chọn--</option>
					</select>
					<%-- <form:input id="ddlDistrict" class="form-control" path="district" placeholder="Quận/Huyện *"/> --%>
				</div>
				
				<div class="form-group">
					<label class="small">Phường/Xã:</label>
					<select id="ddlWard" class="form-control" name="village">
						<option>--Chọn--</option>
					</select>
					<%-- <form:input id="ddlWard" class="form-control" path="village" placeholder="Phường/Xã *"/> --%>
				</div>
				<div class="form-group">
					<label class="small">Mã bưu điện:</label>
					<form:input value="${profile.zip}" id="zip" class="form-control" path="zip" placeholder="Mã bưu điện *"/>
				</div>
				<div class="form-group">
					<label class="small">Số điện thoại:</label>
					<form:input value="${profile.phone}" id="phone" class="form-control" path="phone" placeholder="Số điện thoại *"/>
				</div>
			</div>
			
			<div class="col-md-4 additional mt-3">
				<label class="mb-0">Ghi chú đơn hàng</label>
				<p class="additional-text">ghi chú (có thể bỏ trống)</p>
				
					<div class="form-group">
					<textarea cols="5" rows="2" class="form-control" 
					placeholder="VD: Lời chúc hoặc ghi chú về đơn hàng"></textarea>
				  </div>
				
			</div>
			
			<div class="col-md-4 your-order">
				<p>Đơn hàng của bạn</p>
				<table class="mb-5">
					<tr class="border-double-tr">
						<th width="65%">Sản phẩm</th>
						<th width="35%">Thành tiền</th>
					</tr>
					<c:forEach items="${cart }" var="cartItem">
						<tr class="border-double-tr text-black-tr">
							<td>${cartItem.productName } × ${cartItem.quantity }</td>
							<td><fmt:formatNumber value="${cartItem.price}" type="currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/><span class="woocommerce-Price-currencySymbol none-text-transform">đ</span></td>
						</tr>
					</c:forEach>
					<!-- <tr class="border-single-tr">
						<td>SUBTOTAL</td>
						<td class="text-black-tr">€12.50</td>
					</tr> -->
					<tr>
						<td >TỔNG TIỀN</td>
						<td class="text-black-tr"><fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/><span class="woocommerce-Price-currencySymbol none-text-transform">đ</span></td>
					</tr>
				</table>
				
				<h4>Phương thức thanh toán</h4>
				<br>
				<div id="payment">
					<div class="mt-4 form-check-label">
						<input id="rulesOrder" name="rulesOrder" type="checkbox" class="payment-checkbox"> 
						<span class="text-muted">Tôi đã đọc và đồng ý</span> <span>
						các điều khoản về mua và đặt hàng của website </span> <span class="text-muted">*</span>
					</div>
					<input id="rbPayOffline" type="radio" name="paymentMethod">Thanh toán khi nhận hàng<br>
					<input id="rbOnepay" type="radio" name="paymentMethod">Thanh toán trực tuyến bằng thẻ ATM<br>
  					<input id="rbOnepayW" type="radio" name="paymentMethod">Thanh toán trực tuyến bằng visa card, mastercard<br>
  					<br>
					<div style="overflow: auto;" class="mt-4 form-check-label">
						<script type="text/javascript"src="http://202.9.84.88/documents/payment/logoscript.jsp?logos=v,m,a,j,u,at&lang=vn"></script>
					</div>
					<br>
					<a href="#" id="order" class="btn mt-4 payment-button button-large button-block btn-update-cart button-secondary">
					Đặt hàng và thanh toán</a>
				</div>
			</div>
			</form:form>
		</div>
	</div>	
</section>

<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
<script src="${contextPath}/js/city.js"></script>
</body>
</html>