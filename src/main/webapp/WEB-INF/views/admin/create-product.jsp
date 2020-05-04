<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create product</title>
</head>
<body>
<h1>Create product: </h1>
<form method="post" action="${pageContext.request.contextPath}/admin/create-product">
    Name: <input type="text" name="name">
    Price: <input type="text" name="price">
    <button type="submit">Done</button>
</form>
<button><a href="/internet_shop_war_exploded/">Home</a></button>
</body>
</html>
