<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 07.09.2021
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CinemaManager - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/theme/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/admin/dashboard">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Interface
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/admin/movies/all" data-toggle="collapse" data-target="#collapseMovies"
               aria-expanded="true" aria-controls="collapseMovies">
                <i class="fas fa-fw fa-cog"></i>
                <span>Zarządzaj filmami</span>
            </a>
            <div id="collapseMovies" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Operacje:</h6>
                    <a class="collapse-item" href="/admin/movies/all">Wyświetl wszystkie</a>
                    <a class="collapse-item" href="/admin/movies/add">Dodaj nowy</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/admin/ticket-types/all" data-toggle="collapse" data-target="#collapseTickets"
               aria-expanded="true" aria-controls="collapseTickets">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Zarządzaj biletami</span>
            </a>
            <div id="collapseTickets" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Operacje:</h6>
                    <a class="collapse-item" href="/admin/ticket-types/all">Wyświetl typy biletów</a>
                    <a class="collapse-item" href="/admin/ticket-types/add">Dodaj typ biletu</a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/admin/hall/all" data-toggle="collapse" data-target="#collapseHall"
               aria-expanded="true" aria-controls="collapseHall">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Sale kinowe</span>
            </a>
            <div id="collapseHall" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Operacje:</h6>
                    <a class="collapse-item" href="/admin/hall/all">Wszystkie sale</a>
                    <a class="collapse-item" href="/admin/hall/add">Dodaj nową salę</a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/admin/seances/all" data-toggle="collapse" data-target="#collapseSeance"
               aria-expanded="true" aria-controls="collapseSeance">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Zarządzaj seansami</span>
            </a>
            <div id="collapseSeance" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Operacje:</h6>
                    <a class="collapse-item" href="/admin/seances/all">Wszystkie seanse</a>
                    <a class="collapse-item" href="/admin/seances/add">Zaplanuj seans</a>
                </div>
            </div>
        </li>


        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>


    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>


                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                            <img class="img-profile rounded-circle"
                                 src="<c:url value="${pageContext.request.contextPath}/resources/theme/img/undraw_profile.svg" />">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value="/logout"/>">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>
                    <a href="<c:url value="/logout"/>" >Logout</a>
                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
<%--            <div class="container-fluid">--%>

<%--                <!-- Page Heading -->--%>
<%--                <div class="d-sm-flex align-items-center justify-content-between mb-4">--%>
<%--                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>--%>
<%--                </div>--%>
<%--            </div>--%>
