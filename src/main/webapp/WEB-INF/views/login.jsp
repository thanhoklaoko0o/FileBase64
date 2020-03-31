<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>
<!--login-->
<div class="container">
		<div class="login">
			<h3>${message}</h3>
			<form th:action="@{/login}" method='POST' id="login-form">
			<div class="col-md-6 login-do">
				<div class="login-mail">
					<input type="text" name="username" placeholder="Tên đăng nhập" required="required">
					<i  class="glyphicon glyphicon-envelope"></i>
				</div>
				<div class="login-mail">
					<input type="password" name="password" placeholder="Mật khẩu" required="required">
					<i class="glyphicon glyphicon-lock"></i>
				</div>
				   <a class="news-letter " href="#">
						 <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Lưu mật khẩu</label>
					   </a>
				<label class="hvr-skew-backward">
					<input name="submit" type="submit" value="Đăng nhập">
				</label>
			</div>
			<div class="col-md-6 login-right">
				 <h3>Đăng ký tài khoản miễn phí</h3>
				 
				 <p>Đăng ký tài khoản để có thể xem lại lịch sử đặt hàng và có thể phản hồi với người quản lý cửa hàng khi gặp trở ngại trong việc mua hàng.
				  Và ngoài ra sẽ nhận được nhiều ưu đãi trong các đợt giảm giá. Đăng ký tài khoản còn giúp việc mua hàng tiện lợi hơn khi được nhận thông báo khi có sản phẩm mới.</p>
				<a href="register.html" class=" hvr-skew-backward">Đăng Ký</a>

			</div>
			
			<div class="clearfix"> </div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>

</div>

<!--//login-->
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>