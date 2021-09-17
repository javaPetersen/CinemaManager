<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 07.09.2021
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/admin-header.jsp"/>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dodaj nowy film:</h1>
        <a onclick="window.history.back()" class="btn btn-outline-success" role="button" aria-pressed="true">Powrót</a>
    </div>
</div>
<div class="d-sm-flex align-items-center justify-content-between mb-4 text-danger">
    <c:if test="${not empty error}">
        <h5>${error}</h5>
    </c:if> </div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table class="table">
            <form:form method="post" modelAttribute="movie">
                <form:input path="id" hidden="true"/>
                <form:input path="createdOn" hidden="true"/>
                <tr>
                    <td><form:label path="title">Tytuł:</form:label></td>
                    <td><form:input path="title"/></td>
                    <td><form:errors path="title" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="originalTitle">Tytuł oryginalny:</form:label></td>
                    <td><form:input path="originalTitle"/></td>
                    <td><form:errors path="originalTitle" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="length">Czas trwania (min):</form:label></td>
                    <td><form:input path="length"/></td>
                    <td><form:errors path="length" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="genre">Gatunek:</form:label></td>
                    <td><form:input path="genre"/></td>
                    <td><form:errors path="genre" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="country">Kraj produkcji:</form:label></td>
                    <td><form:input path="country"/></td>
                    <td><form:errors path="country" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="director">Reżyser:</form:label></td>
                    <td><form:input path="director"/></td>
                    <td><form:errors path="director" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="description">Opis:</form:label></td>
                    <td><form:textarea path="description" rows="10" cols="60"/></td>
                    <td><form:errors path="description" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="poster">Link do plakatu:</form:label></td>
                    <td><form:input type="url" path="poster"/></td>
                    <td><form:errors path="poster" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:button class="btn btn-outline-secondary">Zapisz</form:button></td>
                </tr>
            </form:form>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
