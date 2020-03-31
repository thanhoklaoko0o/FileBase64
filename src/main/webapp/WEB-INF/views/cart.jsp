<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Giỏ hàng</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<div style="background: #f8f8f8;">
<div class="content-cart container pl-0">
	<c:if test="${not empty cart }">
        <div class="page-heading columns-2">
            <div class="col">
                <h1 class="text-uppercase">
                    <i class="fa fa-2x fa-cart"></i>Giỏ hàng
                </h1>
            </div>
        </div>
        <div class="row">
        <div class="col-sm-8">
       	<div id="error" class="alert alert-danger alert-dismissible">
       		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	   	</div>
        <table class="shop_table shop_table_responsive cart woocommerce-cart-form__contents" cellspacing="0">
            <thead>
            <tr>
                <th class="product-remove">&nbsp;</th>
                <th class="product-name" colspan="2">Sản phẩm</th>
                <th class="product-quantity">Số lượng</th>
                <th class="product-subtotal col-md-2">Thành tiền</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cart }" var="cartItem">
            <tr class="woocommerce-cart-form__cart-item cart_item" id="${cartItem.productID }ItemCart">
                <td class="product-remove">
                    <a href="#" value="" class="remove rounded btn-delete" aria-label="Remove this item" data-id="${cartItem.productID }" onclick="removeCartItem(this)">×</a>
                </td>
                <td class="product-thumbnail">
                    <a href="${contextPath}/product/detail/${cartItem.productID }"><span class="image-placeholder image-loaded" style="padding-bottom:128.57142857%">
                    <img width="70" height="90" src="${cartItem.imageUrl}" class="attachment-shop-thumb-2 size-shop-thumb-2 wp-post-image lazyloaded" alt="img product"></span></a>
                </td>
                <td class="product-name">
                    <span class="name" id="name${cartItem.productID }"><a href="#">${cartItem.productName }</a></span><br><span class="price">
                        <span class="woocommerce-Price-amount amount">
                           <%-- <fmt:setLocale value="vi_VN"/> --%>
  						 	<fmt:formatNumber value="${cartItem.price}" type="currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>
  						 	<span class="woocommerce-Price-currencySymbol none-text-transform">đ</span>
                        </span>
                    </span>
                </td>
                <td class="product-quantity" data-title="Quantity">
                    <div class="quantity buttons_added">
                        <input type="button" value="-" class="plusminus plus minus" data-id="${cartItem.productID }" onclick="updateCartItem(this)"
                            data-price="${cartItem.price}">
                        <input type="text" id="${cartItem.productID}" class="input-text qty text" disabled
                               value="${cartItem.quantity }" title="Qty" >
                        <input type="button" value="+" class="plusminus plus" data-id="${cartItem.productID }" onclick="updateCartItem(this)"
                           data-price="${cartItem.price}">
                    </div>
                </td>
                <td class="product-subtotal" data-title="Total">
                    <div class="price total"><span class="woocommerce-Price-amount amount">
                        
                            <span id="total${cartItem.productID }">
							<fmt:formatNumber value = "${cartItem.quantity * cartItem.price}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>
                            </span>
                            <span class="woocommerce-Price-currencySymbol none-text-transform">đ</span>
                        </span>
                    </div>
                </td>
            </tr>
           	</c:forEach>
            </tbody>
        </table>
        </div>
    	<div class="cart-collaterals col-sm-3">
        <div class="cart_totals">
            <h2>Thông tin giỏ hàng</h2>
            <table cellspacing="0" class="shop_table_responsive">
                <tbody>
                <tr class="order-total">
                    <th>Tông tiền</th>
                    <td data-title="Total">
                         <span id="total" class="woocommerce-Price amount"><fmt:formatNumber value = "${total}" type = "currency" currencySymbol="VNĐ" currencyCode="VND" pattern="###,##0"/></span>
                         <span class="Price-currencySymbol none-text-transform">đ</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="cart-buttons">
            <div>
                <a class="button-large button-block btn-update-cart button-secondary" href="${contextPath}/order" id="proceed-to-checkout">
                Tiến hành đặt hàng</a>
            </div>
        </div>
    </div>
    </div>
	</c:if>
	<c:if test="${empty cart }">
   <div class="shop-empty-cart-page cart-empty" >
        <div class="container">
            <div class="col-sm-12 text-center alert alert-danger alert-dismissible">
                <div class="cart-bag-env">
                    <div class="cart-smiley shown">
                        <i></i>
                        <i class="cart-smiley-hands"></i>
                    </div>
                    <div class="cart-bag"></div>
                </div>
                <div class="cart-empty-title">
                    <h1>Giỏ hàng rỗng</h1>
                    <p class="return-to-shop">
                        <a class="btn btn-primary text-dark" href="/">
                            Tiếp tục mua sắm!
                        </a>
                    </p>
                </div>
            </div>
        </div>

    </div>
	</c:if>
</div>
</div>
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>