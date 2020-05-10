<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>All orders</title>
</head>
<body>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <c:forEach var="order" items="${orders}">
        <tbody>
            <tr>
                <th>
                    Заказ номер: <c:out value="${order.id}"/>
                </th>
                <td>
                    <a href="${pageContext.request.contextPath}/order/details?orderId=${order.id}">DETAILS</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order/delete?orderId=${order.id}">DELETE</a>
                </td>
            </tr>
        </tbody>
    </c:forEach>
</table>
<h1></h1>
<div class="p-3 mb-2 bg-light text-dark">
    <h3>
        <a href="${pageContext.request.contextPath}/" style="color: black">Home</a>
        <a href="${pageContext.request.contextPath}/shoppingCart" style="color: black">Shopping cart</a>
    </h3>
</div>
</body>
</html>
