<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h1 class="h3 mb-0 text-gray-800">Zaplanuj seans:</h1>
        <a onclick="window.history.back()" class="btn btn-outline-success" role="button" aria-pressed="true">Powrót</a>
    </div>
</div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table class="table">
            <form:form method="post" modelAttribute="seance">
                <form:input path="id" hidden="true"/>
                <tr>
                    <td><form:label path="movie">Film:</form:label></td>
                    <td><form:select path="movie" items="${movies}" itemLabel="title" itemValue="id"/></td>
                    <td><form:errors path="movie" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="hall">Sala:</form:label></td>
                    <td><form:select path="hall" items="${halls}" itemLabel="name" itemValue="id"/></td>
                    <td><form:errors path="hall" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="ticketType">Typ biletów:</form:label></td>
                    <td><form:select path="ticketType" multiple="true" items="${ticketTypes}" itemLabel="nameAndPrice"/></td>
                    <td><form:errors path="ticketType" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="date">Data:</form:label></td>
                    <td><form:input type="date" path="date"/></td>
                    <td><form:errors path="date" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="time">Czas:</form:label></td>
                    <td><form:input type="time" path="time"/></td>
                    <td><form:errors path="time" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:button class="btn btn-outline-secondary">Zapisz</form:button></td>
                </tr>

            </form:form>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
