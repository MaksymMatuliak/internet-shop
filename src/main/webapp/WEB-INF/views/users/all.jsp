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
                    <a href="/internet_shop_war_exploded/delete-user?userId=${user.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button><a href="/internet_shop_war_exploded/">Home</a></button>
</body>
</html>
