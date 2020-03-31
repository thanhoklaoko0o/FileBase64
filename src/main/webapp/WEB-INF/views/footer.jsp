<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
<script src="${contextPath}/js/validate.js"></script>
<script src="${contextPath}/js/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/cart.js"></script>
<script src="${contextPath}/js/owl.carousel.min.js"></script>
<script src="${contextPath}/js/jquery.chocolat.js"></script>
<script src="${contextPath}/js/jquery.flexslider.js"></script>
<script src="${contextPath}/js/jquery.magnific-popup.js"></script>
<script src="${contextPath}/js/jstarbox.js"></script>
<script src="${contextPath}/js/simpleCart.min.js"></script>
<script src="${contextPath}/js/uisearch.js"></script>
<script src="${contextPath}/js/fontawesome.min.js"></script>
<script src="${contextPath}/js/imagezoom.js"></script>
<script src="${contextPath}/js/jquery.flexslider.js"></script>
<script src="${contextPath}/js/jquery.sticky.js"></script>
<script src="${contextPath}/js/jquery.easing.1.3.min.js"></script>
<script src="${contextPath}/js/main.js"></script>
<script src="${contextPath}/js/bxslider.min.js"></script>
<script src="${contextPath}/js/script.slider.js"></script>
<script src="${contextPath}/js/sweetalert2.all.min.js"></script>
<script>
// Can also be used with $(document).ready()
$(window).load(function() {
  $('.flexslider').flexslider({
    animation: "slide",
    controlNav: "thumbnails"
  });
});

jQuery(document).ready(function($) {
	  $(window).load(function() {
	   if ($('.header').length > 0) {
	     var _top = $('.header').offset().top - parseFloat($('.header').css('marginTop').replace(/auto/, 0));
	      $(window).scroll(function(evt) {
	       var _y = $(this).scrollTop();
	       
	       if (_y > _top) {
	       $('.header').addClass('main-menu');
	      /*  $('.main-1').css("margin-top", "30px") */
	       } else {
	       $('.header').removeClass('main-menu');
	       /* $('.main-1').css("margin-top", "0") */
	     }
	    })
	   }
	  });
	 });
</script>
</html>