<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<body>
    <div class="container-fluid">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <h2><label for="exampleInputText1">Login</label></h2>
            <input type="text" name="login"class="form-control" id="exampleInputText1" placeholder="Enter login">
        </div>
        <div class="form-group">
            <h2><label for="exampleInputPassword1">Password</label></h2>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <h6 style="color: red">${errorMessage}</h6>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/registration" role="button">Register</a>
    </form>
    </div>
</body>
</html>
