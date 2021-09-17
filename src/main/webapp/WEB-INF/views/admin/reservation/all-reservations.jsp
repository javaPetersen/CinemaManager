<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 08.09.2021
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1 class="h3 mb-0 text-gray-800">Rezerwacje:</h1>
    </div>
</div>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Seans</th>
                <th scope="col">Data</th>
                <th scope="col">Godzina</th>
                <th scope="col">Użytkownik</th>
                <th scope="col" colspan="5">Miejsca</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allReservations}" var="r" varStatus="loop">
                <tr>
                    <th scope="row">${loop.index+1}</th>
                    <td>${r.seance.movie.title}</td>
                    <td>${r.seance.date}</td>
                    <td>${r.seance.time}</td>
                    <td>${r.user.fullName}</td>
                    <c:forEach items="${r.seats}" var="s">
                        <td>${s.fullName}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>

