<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration:</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Name: <input type="text" name="name">
    Login: <input type="text" name="login">
    Password: <input type="password" name="password">
    Repeat password: <input type="password" name="password-repeat">
    <button type="submit">Done</button>
</form>
<button><a href="/internet_shop_war_exploded/">Home</a></button>
</body>
</html>
