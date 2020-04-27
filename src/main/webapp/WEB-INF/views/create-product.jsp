<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create product</title>
</head>
<body>
<h1>Create product: </h1>
<form method="post" action="${pageContext.request.contextPath}/create-product">
    Name: <input type="text" name="name">
    Price: <input type="text" name="price">
    <button type="submit">Done</button>
</form>
</body>
</html>
