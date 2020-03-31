<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment result</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                        <strong>flippin.com</strong>
                        <br>
                        <abbr title="Phone">P:</abbr> (213) 484-6829
                    </address>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 text-left">
                <fmt:parseDate value="${orderDate }" pattern="yyyy-MM-dd HH:mm:ss" type="both" var="orderD"/>
                    <p>
                       Ngày đặt: <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${orderD}" />
                    </p>
                    <p>
                       Mã đơn hàng: ${merchTxnRef }
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="text-center">
                <c:if test="${txnResponseCode == 0 }">
                    <h1 class="text-success">${transStatus}</h1>
                </c:if>
                <c:if test="${txnResponseCode != 0 }">
                    <h1 class="text-danger">${transStatus}</h1>
                </c:if>
                </div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="col-md-1">Mã giao dịch:</td>
                            <td class="col-md-1 text-center">${transactionNo }</td>
                        </tr>
                         <tr>
                            <td class="col-md-1">Thông tin đơn hàng:</td>
                            <td class="col-md-1 text-center">${merchTxnRef }</td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Số tiền đã thanh toán:</td>
                            <td class="col-md-1 text-center"><fmt:formatNumber value="${purchaseAmount/100 }" type="currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>VNĐ</td>
                        </tr>
                    </tbody>
                </table>
                <div>
                    <h2 class="text-center text-uppercase">
                        Cảm ơn bạn đã đặt hàng.
                    </h2>
                    
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footerSection.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>