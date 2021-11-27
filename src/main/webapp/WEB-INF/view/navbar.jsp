<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/resources/css/book.jpg" alt="Logo" style="width:60px;">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/book/list">List Book</a>
            </li>

            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin</a>
                </li>
            </security:authorize>
        </ul>

        <div class="d-flex justify-content-center mr-3">
            <h5>Hello: <security:authentication property="principal.username" /> </h5>
        </div>

        <form:form method="post" action="${pageContext.request.contextPath}/logout" class="form-inline my-2 my-lg-0">
            <input class="btn btn-outline-primary" type="submit" value="Logout">
        </form:form>
    </div>
</nav>