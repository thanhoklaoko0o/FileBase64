<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<!--content-->
		<div class="product">
			<div class="container">
			<div class="col-md-9">
			<c:if test="${empty productList}">
				<h3 class="text-danger">Không tìm thấy sản phẩm nào!</h3>
			</c:if>
			<c:if test="${not empty productList}">
				<div class="mid-popular">
					<c:forEach items="${productList }" var="product">
					<div class="col-md-4 item-grid1 simpleCart_shelfItem">
					<div class=" mid-pop" style="padding: 0px !important;">
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
								<span>
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
				</c:if>
					</div>
			<div class="col-md-3 product-bottom">
			<!--categories-->
				<div class="rsidebar span_1_of_left">
						<h4 class="cate">Danh Mục</h4>
							<ul class="list-group">
							<c:forEach items="${categoryList }" var="category">
								<li class="list-group-item"><a href="/product/0/10/?cID=${category.categoryID}">${category.categoryName }</a></li>
							</c:forEach>
						</ul>
					</div>
				<!--initiate accordion-->
						<script type="text/javascript">
							$(function() {
							    var menu_ul = $('.menu-drop > li > ul'),
							           menu_a  = $('.menu-drop > li > a');
							    menu_ul.hide();
							    menu_a.click(function(e) {
							        e.preventDefault();
							        if(!$(this).hasClass('active')) {
							            menu_a.removeClass('active');
							            menu_ul.filter(':visible').slideUp('normal');
							            $(this).addClass('active').next().stop(true,true).slideDown('normal');
							        } else {
							            $(this).removeClass('active');
							            $(this).next().stop(true,true).slideUp('normal');
							        }
							    });
							
							});
						</script>
<!--//menu-->
 <!-- <section  class="sky-form">
					<h4 class="cate">Discounts</h4>
					 <div class="row row1 scroll-pane">
						 <div class="">
								<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Upto - 10% (20)</label>
						 </div>
						 <div class="">
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>40% - 50% (5)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>30% - 20% (7)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>10% - 5% (2)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other(50)</label>
						 </div>
					 </div>
				 </section> 				 				 
				 
					
					
					 <section  class="sky-form">
						<h4 class="cate">Type</h4>
							<div class="row row1 scroll-pane">
								<div class="">
									<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Sofa Cum Beds (30)</label>
								</div>
								<div class="">
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Bags  (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Caps & Hats (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Jackets & Coats   (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Jeans  (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Shirts   (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Sunglasses  (30)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Swimwear  (30)</label>
								</div>
							</div>
				   </section>
				   		<section  class="sky-form">
						<h4 class="cate">Brand</h4>
							<div class="row row1 scroll-pane">
								<div class="">
									<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Roadstar</label>
								</div>
								<div class="">
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Levis</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Persol</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Nike</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Edwin</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>New Balance</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Paul Smith</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Ray-Ban</label>
								</div>
							</div>
				   </section>		 -->
		</div>
			<div class="clearfix"></div>
			</div>
				<!--products-->
				</div>
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>