<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>All users</title>
</head>
<body>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">NAME</th>
            <th scope="col">PRICE</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <c:forEach var="product" items="${products}">
        <tbody>
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
                    <a href="/internet_shop_war_exploded/admin/product/delete?productId=${product.id}">DELETE</a>
                </td>
            </tr>
        </tbody>
    </c:forEach>
</table>
<h1></h1>
<div class="p-3 mb-2 bg-light text-dark">
    <h3>
        <a href="/internet_shop_war_exploded/" style="color: black">Home</a>
        <a href="/internet_shop_war_exploded/admin/create-product" style="color: black">Create product</a>
    </h3>
</div>
</body>
</html>
