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
        <h1 class="h3 mb-0 text-gray-800">Wszystkie plany sali:</h1>
        <a href="/admin/hall/add" class="btn btn-outline-success" role="button" aria-pressed="true">Dodaj nowy plan
            sali</a>
    </div>
</div>
<div class="d-sm-flex align-items-center justify-content-between mb-4 text-danger">
<c:if test="${not empty error}">
  <h5>${error}</h5>
</c:if> </div>

<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nazwa planu sali</th>
                <th scope="col">Liczba rzędów</th>
                <th scope="col">Liczba miejsc w rzędzie</th>
                <th scope="col" colspan="2" style="text-align:center">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${halls}" var="h" varStatus="loop">
                <tr>
                    <th scope="row">${loop.index+1}</th>
                    <td>${h.name}</td>
                    <td>${h.rows}</td>
                    <td>${h.numOfSeatsPerRow}</td>
                    <form action="/admin/hall/add">
                        <td style='display: inline-block; padding: 1px;'>
                            <button name="hallId" value="${h.id}" type="submit" class="btn btn-outline-secondary">
                                EDYTUJ
                            </button>
                        </td>
                    </form>
                    <form action="/admin/hall/details" method="get">
                        <td style='display: inline-block; padding: 1px;'>
                            <button type="submit" name="detailsId" value="${h.id}"
                                    class="btn btn-outline-info">SZCZEGÓŁY
                            </button>
                        </td>
                    </form>
                    <form action="/admin/hall/delete" method="post">
                        <td style='display: inline-block; padding: 1px'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input name="deleteId" value="${h.id}" hidden>
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

