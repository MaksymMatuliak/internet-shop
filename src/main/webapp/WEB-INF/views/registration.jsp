<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration:</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Login: <input type="text" name="login">
    Password: <input type="password" name="password">
    Repeat password: <input type="password" name="password-repeat">
    <button type="submit">Done</button>
</form>
</body>
</html>
