<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <h1 class="h3 mb-0 text-gray-800">Szczegóły planu:</h1>
        <a onclick="window.history.back()" class="btn btn-outline-success" role="button" aria-pressed="true">Powrót</a>
    </div>
</div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table style="margin-left: auto; margin-right: auto; border-spacing: 10px; border-collapse: separate; ">
            <tbody>
            <c:forEach items="${seats}" var="s" varStatus="loop">

                <td style="padding: 12px; border: 2px solid black; vertical-align: center; text-align: center; font-size: 20px; ">
                    <div>${s.row}-${s.number}</div>
                </td>
                <c:if test="${(loop.index+1) mod hallDetails.numOfSeatsPerRow == 0}">
                    <tr></tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>
