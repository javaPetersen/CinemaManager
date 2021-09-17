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
                <th scope="col">Miejsca</th>
                <th scope="col">Akcja</th>
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

                    <td><select disabled multiple class="custom-select" size="2" style="width: 80px;">
                        <c:forEach items="${r.seats}" var="s" varStatus="loop">
                            <option>${s.fullName}</option>
                        </c:forEach>
                    </select></td>

                    <form action="/admin/reservation/delete" method="post">
                        <td style='display: inline-block; padding: 1px'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input name="deleteId" value="${r.id}" hidden>
                            <button onclick="return confirm('Are you sure?')"
                                    type="submit"
                                    class="btn btn-outline-danger">USUŃ
                            </button>
                        </td>
                    </form>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../fragments/admin-footer.jsp"/>

