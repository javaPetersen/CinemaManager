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
        <h1 class="h3 mb-0 text-gray-800">Wszystkie filmy:</h1>
        <a href="/admin/movies/add" class="btn btn-outline-success" role="button" aria-pressed="true">Dodaj nowy film</a>
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
                <th scope="col">Plakat</th>
                <th scope="col">Tytuł</th>
                <th scope="col">Czas Trwania</th>
                <th scope="col">Reżyser</th>
                <th scope="col" colspan="2" style="text-align:center">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${movies}" var="m" varStatus="loop">
                <tr>
                    <th scope="row">${loop.index+1}</th>
                    <td><img src="${m.poster}" alt="${m.title} poster" height="80px"/></td>
                    <td>${m.title}</td>
                    <td>${m.convertedLength}</td>
                    <td>${m.director}</td>
                    <form action="/admin/movies/add">
                        <td style='display: inline-block; padding: 1px'>
                            <button name="movieId" value="${m.id}" type="submit" class="btn btn-outline-secondary">
                                EDYTUJ
                            </button>
                        </td>
                    </form>
                    <form action="/admin/movies/delete" method="post">
                        <td style='display: inline-block; padding: 1px'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input name="deleteId" value="${m.id}" hidden>
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

