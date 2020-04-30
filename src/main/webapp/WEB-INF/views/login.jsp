<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login:</h1>
    <h4 style="color: red">${errorMessage}</h4>
    <form method="post" action="${pageContext.request.contextPath}/login">
        Login: <input type="text" name="login">
        Password: <input type="password" name="password">
        <button type="submit">Login</button>
    </form>
</body>
</html>
