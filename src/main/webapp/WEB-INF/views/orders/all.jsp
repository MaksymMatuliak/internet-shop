<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<table border="1">
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                Заказ номер: <c:out value="${order.id}"/>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/order-details?orderId=${order.id}">DETAILS</a>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/delete-order?orderId=${order.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<button><a href="/internet_shop_war_exploded/">Home</a></button>
</body>
</html>
