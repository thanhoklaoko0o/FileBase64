<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="html" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<c:if test="${not empty productDetail }">
<div class="container pt-5p">
	<div class="col-md-5 grid">		
		<div class="flexslider">
			  <ul class="slides">
			    <li data-thumb="${contextPath}/${productDetail.imageUrl}">
			        <div class="thumb-image"> <img src="${contextPath}/${productDetail.imageUrl}" data-imagezoom="true" class="img-responsive"> </div>
			    </li>
			    <li data-thumb="${contextPath}/${productDetail.imageUrl}">
			         <div class="thumb-image"> <img src="${contextPath}/${productDetail.imageUrl}" data-imagezoom="true" class="img-responsive"> </div>
			    </li>
			    <li data-thumb="${contextPath}/${productDetail.imageUrl}">
			       <div class="thumb-image"> <img src="${contextPath}/${productDetail.imageUrl}" data-imagezoom="true" class="img-responsive"> </div>
			    </li> 
			  </ul>
		</div>
	</div>	
<div class="col-md-7 single-top-in">
						<div class="span_2_of_a1 simpleCart_shelfItem">
				<h3>${productDetail.productName }</h3>
				
			    <div class="price_single">
				  <span class="reducedfrom item_price"><fmt:formatNumber value="${productDetail.price}" type="currency" currencySymbol="VNĐ" currencyCode="VND" pattern="###,##0"/>đ</span>
				 
				 <div class="clearfix"></div>
				</div>
				<h4 class="quick">Đánh giá nhanh:</h4>
				<p class="quick_desc">
					Chính hãng, nguyên seal, mới 100%, chưa active
					Miễn phí giao hàng toàn quốc			
					Thiết kế: Nguyên khối					
					Màn hình OLED: 6.5 inch Super Retina (2688 x 1242), 458ppi, 3D Touch, TrueTone Dolby Vision HDR 10					
					Camera Trước/Sau: 7MP/ 2 camera 12MP					
					CPU: A12 Bionic 64-bit 7nm					
					Bộ Nhớ: 256GB					
					RAM: 4GB					
					SIM: 1 Nano SIM + 1 eSIM					
					Đạt chuẩn chống nước bụi IP68, Face ID
				</p>
			    <div class="wish-list">
				 	<ul>
				 		<li class="wish"><a href="#"><span class="glyphicon glyphicon-check" aria-hidden="true"></span>Thêm vào sản phẩm yêu thích</a></li>
				 	</ul>
				 </div>
				 <div class="quantity"> 
								<div class="quantity-select" style="display: inline-flex;">                           
									<div class="entry value-minus">&nbsp;</div>
									<div class="entry"><input class="value" type="text" id="quantity" step="1" min="1" max="10" name="" disabled
                               value="1" size="4" pattern="[0-9]*"></div>
									<div class="entry value-plus active">&nbsp;</div>
								</div>
				</div>
	<!--quantity-->
	<script>
    $('.value-plus').on('click', function(){
    	/* var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1; */
    	var newVal = parseInt($("#quantity").val(), 10) + 1;
    	if(newVal<=10) $("#quantity").val(newVal);
    });

    $('.value-minus').on('click', function(){
    	/* var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1; */
    	var newVal = parseInt($("#quantity").val(), 10) - 1;
    	if(newVal>=1) $("#quantity").val(newVal);
    });
	</script>
	<!--quantity-->
				 
		    <a class="add-to" href="#" data-id="${productDetail.productID }" onclick="addCartItem(this)"><img src="${contextPath}/images/ca.png" alt=""></a>
			<div class="clearfix"> </div>
			</div>
		
					</div>
			<div class="clearfix"> </div>
			<!---->
			<div class="tab-head">
			 <nav class="nav-sidebar">
		<ul class="nav tabs">
          <li class="active"><a href="#tab1" data-toggle="tab">Product Description</a></li>
          <li class=""><a href="#tab2" data-toggle="tab">Additional Information</a></li> 
          <li class=""><a href="#tab3" data-toggle="tab">Reviews</a></li>  
		</ul>
	</nav>
	<div class="tab-content one">
<div class="tab-pane active text-style" id="tab1">
<div class="facts">
  <p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
	<ul>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
		<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
	</ul>         
</div>

</div>
<div class="tab-pane text-style" id="tab2">
	
	<div class="facts">									
		<p > Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections </p>
		<ul >
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Multimedia Systems</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Digital media adapters</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Set top boxes for HDTV and IPTV Player  </li>
		</ul>
     </div>	

</div>
<div class="tab-pane text-style" id="tab3">
	 <div class="facts">
	  <p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
		<ul>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
			<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
		</ul>     
   	</div>	
 </div>
  
  </div>
  <div class="clearfix"></div>
  </div>
			<!---->	
</div>
<!----->
</c:if>
<c:if test="${empty productDetail }">
	<div class="container">
		<div class="alert alert-danger">
			<span>Not found product!</span>
		</div>
	</div>
</c:if>
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>