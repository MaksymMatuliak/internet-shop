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
                <th>ID</th>
                <th>NAME</th>
                <th>LOGIN</th>
                <th>PASSWORD</th>
                <th></th>
            </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tbody>
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
                        <c:out value="${user.password}"/>
                    </td>
                    <td>
                        <a href="/internet_shop_war_exploded/admin/user/delete?userId=${user.id}">DELETE</a>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
    </table>
    <h1></h1>
    <div class="p-3 mb-2 bg-light text-dark">
        <h3>
            <a href="${pageContext.request.contextPath}" style="color: black">Home</a>
        </h3>
    </div>
</body>
</html>
