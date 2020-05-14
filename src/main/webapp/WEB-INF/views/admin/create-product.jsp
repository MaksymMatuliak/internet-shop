<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Create product</title>
</head>
<body>
<div class="container-fluid">
<form method="post" action="${pageContext.request.contextPath}/admin/create-product">
    <div class="form-group">
        <h2><label for="exampleInputText1">Name</label></h2>
        <input type="text" name="name"class="form-control" id="exampleInputText1" placeholder="Enter name">
    </div>
    <div class="form-group">
        <h2><label for="exampleInputText2">Price</label></h2>
        <input type="text" name="price"class="form-control" id="exampleInputText2" placeholder="Enter price">
    </div>
    <h6 style="color: red">${message}</h6>
    <button type="submit" class="btn btn-primary">Submit</button>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}" role="button">Home</a>
</form>
</div>
</body>
</html>
