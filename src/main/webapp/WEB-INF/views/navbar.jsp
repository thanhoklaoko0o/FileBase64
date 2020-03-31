<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/css/popuo-box.css" rel="stylesheet" type="text/css" media="all">
<script src="${contextPath}/js/jquery.magnific-popup.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<!--header-->
<div class="header">
<div class="container">
		<div class="head">
			<div class=" logo">
				<a href="${contextPath}/"><img src="${contextPath}/images/logo.jpg" alt=""></a>	
			</div>
		</div>
	</div>
	<div class="header-top">
		<div class="container">
		<div class="col-sm-5 col-md-offset-2  header-login">
					<ul >
						<c:if test="${not empty pageContext.request.userPrincipal.name}">
							<li><a href="${contextPath}/account/profile">${pageContext.request.userPrincipal.name}</a></li>
						</c:if>
						<c:if test="${empty pageContext.request.userPrincipal.name}">
							<li><a href="${contextPath}/login">Đăng nhập</a></li>
						</c:if>
						
						<li><a href="${contextPath}/register">Đăng ký</a></li>
						<%-- <li><a href="${contextPath}/order">Checkout</a></li> --%>
					</ul>
				</div>
				
			<div class="col-sm-5 header-social header-login">		
					<ul >
						<li><a href="#"><i></i></a></li>
						<li><a href="#"><i class="ic1"></i></a></li>
						<li><a href="#"><i class="ic2"></i></a></li>
						<li><a href="#"><i class="ic3"></i></a></li>
						<li><a href="#"><i class="ic4"></i></a></li>
					</ul>
					
			</div>
				<div class="clearfix"> </div>
		</div>
		</div>
		
		<div class="container">
		
			<div class="head-top">
			
		 <div class="col-sm-8 col-md-offset-2 h_menu4">
				<nav class="navbar nav_bottom" role="navigation">
 
 <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header nav_2">
      <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     
   </div> 
   <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
        <ul class="nav navbar-nav nav_1">
            <li><a class="color" href="${contextPath}/">Trang chủ</a></li>
    		<li class="dropdown mega-dropdown active">
			    <a class="color1" href="${contextPath}/product/0/20" class="dropdown-toggle" >Sản phẩm</a>
			</li>
			<li><a class="color3" href="product.html">Khuyến mãi HOT</a></li>
			<li><a class="color3" href="product.html">Dịch vụ</a></li>
            <li ><a class="color6" href="contact.html">Liên hệ</a></li>
        </ul>
     </div>
     <!-- /.navbar-collapse -->
</nav>
	</div>
			<div class="col-sm-2 search-right">
				<ul class="heart">
				<li>
				<a href="wishlist.html" >
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
				</a></li>
				<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog"><i class="glyphicon glyphicon-search"> </i></a></li>
					</ul>
					<div class="cart box_1">
						<a href="${contextPath}/cart">
						<h3>
							<%int amount = session.getAttribute("amount") == null ? 0 : (int) session.getAttribute("amount"); %>
							<img src="${contextPath}/images/cart.png" alt=""/> <div class="amountCart">
							<c:if test="${empty amount}">
								<span id="amountCart">0</span>
							</c:if>
							<c:if test="${not empty amount}">
								<span id="amountCart">${amount}</span>
							</c:if>
							</div></h3>
						</a>

					</div>
					<div class="clearfix"> </div>
					
						<!----->

						<!---pop-up-box---->
			<!---//pop-up-box---->
			<div id="small-dialog" class="mfp-hide">
				<div class="search-top">
					<div class="login-search">
					<form action="/" method="get">
						<input type="submit" value="">
						<input name="st" type="text" value="Tìm kiếm..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tìm kiếm..';}">		
					</form>
					</div>
				</div>				
			</div>
		 <script>
			$(document).ready(function() {
			$('.popup-with-zoom-anim').magnificPopup({
			type: 'inline',
			fixedContentPos: false,
			fixedBgPos: true,
			overflowY: 'auto',
			closeBtnInside: true,
			preloader: false,
			midClick: true,
			removalDelay: 300,
			mainClass: 'my-mfp-zoom-in'
			});
																						
			});
			
			
		</script>		
						<!----->
			</div>
			<div class="clearfix"></div>
		</div>	
	</div>	
</div>
</body>
</html>