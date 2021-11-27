<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Save Book</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="d-flex justify-content-center py-5">
    <h2>Book Form</h2>
</div>
<div class="container">
    <form:form action="saveFile" method="post" enctype="multipart/form-data">
        <form:hidden path="id" />

        <div class="form-group row">
            <label for="image" class="col-2 col-form-label">Image</label>
            <div class="col-4">
                <form:input path="image" type="file" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-2 col-4">
                <input class="btn btn-primary" type="submit" value="Submit">
            </div>
        </div>

    </form:form>
</div>

</body>
</html></html>