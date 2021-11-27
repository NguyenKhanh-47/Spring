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
    <form:form action="saveBook" modelAttribute="book" method="post" enctype="multipart/form-data">
        <form:hidden path="id" />
        <div class="form-group row">
            <label for="title" class="col-2 col-form-label">Title:</label>
            <div class="col-4">
                <form:input path="title" class="form-control" id="title" placeholder="Title"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="content" class="col-2 col-form-label">Content:</label>
            <div class="col-4">
                <form:input path="content" class="form-control" id="content" placeholder="Content"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="price" class="col-2 col-form-label">Price</label>
            <div class="col-4">
                <form:input path="price" class="form-control" id="price" placeholder="Price"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="author" class="col-2 col-form-label">Author</label>
            <div class="col-4">
                <form:select path="author" class="form-control" id="author">
                    <form:option value="NONE" disabled="true" >---Select Author---</form:option>
                    <c:forEach items="${authors}" var="authorName">
                        <option>${authorName.name}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-group row">
            <label for="releaseDate" class="col-2 col-form-label">Release Date</label>
            <div class="col-4">
                <form:input type="date" path="releaseDate" class="form-control" id="releaseDate" placeholder="releaseDate"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="category" class="col-2 col-form-label">Category</label>
            <div class="col-4">
                <form:input path="category" class="form-control" id="category" placeholder="Category"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="fileData" class="col-2 col-form-label">Image</label>
            <div class="col-4">
                <form:input path="fileData" type="file" accept="image/png, image/jpeg" class="form-control" id="fileData"/>
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