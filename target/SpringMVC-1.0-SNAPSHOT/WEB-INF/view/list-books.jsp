<%@ page import="com.aptech.springmvc.constant.SortBookColumn" %><%--
  Created by IntelliJ IDEA.
  User: NguyenKhanh
  Date: 10/10/2021
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>List Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>
<c:import url="navbar.jsp"/>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center">
        <input class="btn btn-primary form-group row" type="button" value="Add Book"
               onclick="window.location.href='showFormForAdd'; return false;">

        <form:form action="search" method="get">
            <div class="form-group row">
                <div class="col-8">
                    <input name="theSearchName" class="form-control mr-1" id="search" type="search"
                           placeholder="Search" aria-lable="Search">
                </div>
                <div class="col-4">
                    <input class="btn btn-primary" type="submit" value="Search">
                </div>
            </div>
        </form:form>
    </div>
    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <%-- links for sorting --%>
            <c:url value="/book/list" var="sortLinkTitle">
                <c:param name="sort" value="<%=String.valueOf(SortBookColumn.TITLE) %>" />
            </c:url>
            <c:url value="/book/list" var="sortLinkPrice">
                <c:param name="sort" value="<%=String.valueOf(SortBookColumn.PRICE) %>" />
            </c:url>
            <c:url value="/book/list" var="sortLinkAuthor">
                <c:param name="sort" value="<%=String.valueOf(SortBookColumn.AUTHOR) %>" />
            </c:url>
            <c:url value="/book/list" var="sortLinkReleaseDate">
                <c:param name="sort" value="<%=String.valueOf(SortBookColumn.RELEASE_DATE) %>" />
            </c:url>
            <c:url value="/book/list" var="sortLinkCategory">
                <c:param name="sort" value="<%=String.valueOf(SortBookColumn.CATEGORY) %>" />
            </c:url>

            <th scope="col">Order</th>
            <th scope="col"><a href="${sortLinkTitle}"><i class="fas fa-sort-up"></i></a> Title</th>
            <th scope="col">Content</th>
            <th scope="col"><a href="${sortLinkPrice}"><i class="fas fa-sort-up"></i></a> Price</th>
            <th scope="col"><a href="${sortLinkAuthor}"><i class="fas fa-sort-up"></i></a> Author</th>
            <th scope="col"><a href="${sortLinkReleaseDate}"><i class="fas fa-sort-up"></i></a> Release Date</th>
            <th scope="col">Image</th>
            <th scope="col"><a href="${sortLinkCategory}"><i class="fas fa-sort-up"></i></a> Category</th>
            <th scope="col">Action</th>
            </thead>
            <c:forEach items="${books}" var="tempBook" varStatus="iterationCount">
                <c:url var="updateLink" value="/book/showFormForUpdate">
                    <c:param name="bookId" value="${tempBook.id}" />
                </c:url>
                <c:url var="deleteLink" value="/book/delete">
                    <c:param name="bookId" value="${tempBook.id}" />
                </c:url>
                <tbody>
                <td>${iterationCount.count}</td>
                <td>${tempBook.title}</td>
                <td>${tempBook.content}</td>
                <td>${tempBook.price}</td>
                <td>${tempBook.author}</td>
                <td>${tempBook.releaseDate}</td>
                <td><img width="130" height="180" src="${pageContext.request.contextPath}/resources/css/Images/${tempBook.image}"></td>
                <td>${tempBook.category}</td>
                <td>
                    <a href="${updateLink}">Update</a>
                    |
                    <a href="${deleteLink}"
                       onclick="if (!confirm('Are you sure you want to delete this?')) return false;">Delete</a>
                </td>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
