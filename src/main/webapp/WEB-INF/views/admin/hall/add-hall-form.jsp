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
        <h1 class="h3 mb-0 text-gray-800">Dodaj nowy plan sali:</h1>
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
            <form:form method="post" modelAttribute="hall">
                <form:input path="id" hidden="true"/>
                <tr>
                    <td><form:label path="name">Nazwa:</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="rows">Liczba rzędów (max26):</form:label></td>
                    <td><form:input type="number" min="0" path="rows"/></td>
                    <td><form:errors path="rows" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="numOfSeatsPerRow">Liczba miejsc w rzędzie:</form:label></td>
                    <td><form:input type="number" min="0" path="numOfSeatsPerRow"/></td>
                    <td><form:errors path="numOfSeatsPerRow" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:button class="btn btn-outline-secondary">Zapisz</form:button></td>
                </tr>
            </form:form>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
