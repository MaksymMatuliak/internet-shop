<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>LOGIN</th>
            <th>DELETE</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <a href="/internet_shop_war_exploded/user/delete?userId=${user.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h1></h1>
    <button><a href="/internet_shop_war_exploded/">Home</a></button>
</body>
</html>
