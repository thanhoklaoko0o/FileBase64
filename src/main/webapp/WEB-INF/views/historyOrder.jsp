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

<div class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Tên người nhận</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Tổng tiền</th>
                    <th>Ngày đặt</th>
                    <th>Hình thức thanh toán</th>
                    <th>Tình trạng thanh toán</th>
                    <th>thao tác</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td class="py-1">${order.orderID}</td>
                    <td>${order.recipientName}</td>
                    <td>${order.shipAddress}</td>
                    <td>${order.phone}</td>
                    <td><fmt:formatNumber value="${order.purchaseAmount }" type="currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>VNĐ</td>
                    <fmt:parseDate value="${order.orderDate }" pattern="yyyy-MM-dd HH:mm:ss" type="both" var="orderD"/>
                    <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${orderD}" /></td>
                    <td>${order.payment_method}</td>
                    <td>
                    	<c:if test="${order.paid == 0}"><span class="text-success">đã thanh toán</span></c:if>
                    	<c:if test="${order.paid == -1}"><span class="text-danger">chưa thanh toán</span></c:if>
                    	<c:if test="${order.paid == -2}"><span class="text-warning">đang chờ giao dịch</span></c:if>
                    	<c:if test="${order.paid == -3}"><span class="text-warning">giao dịch thất bại</span></c:if>
                    </td>
                    <td class="template-demo">
                        <button class="btn btn-warning" type="button"><i class="fa fa-eye"></i>Chi tiết</button>
                    </td>
                </tr>
               </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>