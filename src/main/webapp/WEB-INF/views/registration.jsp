<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Registration</title>
</head>
<body>
<div class="container-fluid">
<form method="post" action="${pageContext.request.contextPath}/registration">
    <div class="form-group">
        <h2><label for="exampleInputText1">Name</label></h2>
        <input type="text" name="name"class="form-control" id="exampleInputText1" placeholder="Name">
    </div>
    <div class="form-group">
        <h2><label for="exampleInputText2">Login</label></h2>
        <input type="text" name="login"class="form-control" id="exampleInputText2" placeholder="Login">
    </div>
    <div class="form-group">
        <h2><label for="exampleInputPassword1">Password</label></h2>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-group">
        <h2><label for="exampleInputPassword2">Repeat password</label></h2>
        <input type="password" name="password-repeat" class="form-control" id="exampleInputPassword2" placeholder="Repeat password">
    </div>
    <h6 style="color: red">${message}</h6>
    <button type="submit" class="btn btn-primary">Submit</button>
    <a class="btn btn-primary" href="/internet_shop_war_exploded/login" role="button">Login</a>
</form>
</div>
</body>
</html>
