<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>InternetShop</title>
</head>
<body>
    <div class="p-3 mb-2 bg-secondary text-white">
        <h1 align="center" style="color: black"><b>Menu for users</b></h1>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/login" style="color: black">Login</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/registration" style="color: black">Register</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/products" style="color: black">All products</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/shoppingCart" style="color: black">Shopping Cart</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/orders" style="color: black">Orders</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/logout" style="color: black">Logout</a></h2>
    </div>

    <div class="p-3 mb-2 bg-secondary text-white">
        <h1 align="center" style="color: black"><b>Menu for admin</b></h1>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/admin/inject" style="color: black">Inject</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/admin/products" style="color: black">Products</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/admin/create-product" style="color: black">Create product</a></h2>
    </div>

    <div class="p-3 mb-2 bg-light text-dark">
        <h2 align="center"><a href="${pageContext.request.contextPath}/admin/users"style="color: black">All users</a></h2>
    </div>
</body>
</html>
