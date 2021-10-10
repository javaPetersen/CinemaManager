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
        <h1 class="h3 mb-0 text-gray-800">Dodaj nowy typ biletu:</h1>
        <a onclick="window.history.back()" class="btn btn-outline-success" role="button" aria-pressed="true">Powrót</a>
    </div>
</div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table class="table">
            <form:form method="post" modelAttribute="ticketType">
                <form:input path="id" hidden="true"/>
                <tr>
                    <td><form:label path="name">Nazwa:</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:label path="price">Cena:</form:label></td>
                    <td><form:input type="number" min="0.00" step=".01" path="price"/></td>
                    <td><form:errors path="price" cssClass="text-danger"/></td>
                </tr>

                <tr>
                    <td><form:button class="btn btn-outline-secondary">Zapisz</form:button></td>
                </tr>

            </form:form>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
