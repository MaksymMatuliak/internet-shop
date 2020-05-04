<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PRICE</th>
        <th>DELETE</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/shopping-cart/delete?productId=${product.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h1></h1>
<button><a href="/internet_shop_war_exploded/">Home</a></button>
<button><a href="/internet_shop_war_exploded/complete-order?shoppingCartId=${shoppingCartId}">Complete</a></button>
</body>
</html>
