<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="slider.jsp"></jsp:include>

<div class="content">
	<div class="container">
		<h3 class="text-uppercase">sản phẩm mới nhất</h3>
		<br>
	</div>
	<div class="container content-main">
		<div class="content-mid">
			<div class="mid-popular">
				<c:forEach items="${productsBestSeller }" var="product">
					<div class="col-md-3 item-grid simpleCart_shelfItem">
					<div class=" mid-pop">
					<div class="pro-img">
						<a href="product/detail/${product.productID }"><img src="${contextPath}/${product.imageUrl }" class="img-responsive" alt=""></a>
						</div>
						<div class="mid-1">
						<div class="women">
						<div class="women-top">
							<!-- <span>Women</span> -->
							<h6><a href="single.html">${product.productName }</a></h6>
							</div>
							<div class="img item_add">
								<a href="#" data-id="${product.productID }" onclick="addCartItem(this)"><img src="${contextPath}/images/ca.png" alt=""></a>
							</div>
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span ><!-- <label>$100.00</label> -->
								<fmt:setLocale value="vi_VN"/>
        						 <fmt:formatNumber value = "${product.price}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>đ
        						</span>
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>
								
								<div class="clearfix"></div>
							</div>
							
						</div>
					</div>
					</div>
					</c:forEach>
					</div>
					</div>
	</div>
</div>
 <div class="brands-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="brand-wrapper">
                        <div class="brand-list">
                            <img src="images/brand1.png" alt="">
                            <img src="images/brand2.png" alt="">
                            <img src="images/brand3.png" alt="">
                            <img src="images/brand4.png" alt="">
                            <img src="images/brand5.png" alt="">
                            <img src="images/brand6.png" alt="">
                            <img src="images/brand1.png" alt="">
                            <img src="images/brand2.png" alt="">                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
   <!-- End brands area -->
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>