<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 09.09.2021
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/admin-header.jsp"/>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Zarezerwuj:</h1>
        <a onclick="window.history.back()" class="btn btn-outline-success" role="button" aria-pressed="true">Powrót</a>
    </div>
</div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table>
        <form:form modelAttribute="reservation" method="post">
            <form:input path="id" hidden="true"/>
            <form:input path="seance" hidden="true" />
            <tr>
                <td><form:label path="user">Wybierz użytkownika:</form:label> </td>
                <td><form:select cssClass="form-select" path="user" items="${users}" itemLabel="fullName" itemValue="id"/> </td>
                <td><form:errors cssClass="text-danger" path="user"/> </td>
            </tr>
            <tr>
                <td><form:label path="seats">Wybierz miejsca:</form:label></td>
                <td><form:select path="seats" items="${activeSeats}" itemValue="id" itemLabel="fullName" multiple="true" size="5" required="true" cssClass="form-select" cssStyle="width: 100px"/></td>
                <td><form:errors path="seats" cssClass="text-danger"/></td>
            </tr>
           <tr>
               <td><form:button>ZAPISZ</form:button></td>
           </tr>
        </form:form>
        </table>
        <div style="width: 70%;padding: 12px; border: 2px solid black; margin: 5px auto 8px;text-align: center; font-size: 20px; ">EKRAN</div>
        <table style="margin-left: auto; margin-right: auto; border-spacing: 10px; border-collapse: separate; ">
            <tbody>
            <c:forEach items="${allSeats}" var="s" varStatus="loop">
                    <c:choose>
                        <c:when test="${reservedSeats.contains(s)}">
                            <td style="background-color: darkslategrey;padding: 9px; border: 2px solid black; vertical-align: center; text-align: center; font-size: 15px; color: white ">
                                <div>${s.fullName}</div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td style="padding: 9px; border: 2px solid black; vertical-align: center; text-align: center; font-size: 15px; ">
                                <div>${s.fullName}</div>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${(loop.index+1) mod reservation.seance.hall.numOfSeatsPerRow == 0}">
                        <tr></tr>
                    </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
