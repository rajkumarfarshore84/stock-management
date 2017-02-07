<%-- 
    Document   : header
    Created on : 24 Dec, 2015, 7:21:00 PM
    Author     : node
--%>
<%@page import="com.rajtech.stockwebservice.model.Users"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security1" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Logo -->
<a href="index2.html" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"><b>S</b> M S</span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b>Stock</b> Manage System</span>
</a>


<nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
    </a>
    <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
                <security1:authorize access="isAuthenticated()">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.servletContext.contextPath}/resources/themes/img/avatar.png" class="user-image" alt="User Image">
                        <span class="hidden-xs">${udetail.name}</span>
                    </a>
                    <ul class="dropdown-menu">

                        <!-- User image -->
                        <li class="user-header">
                            <img src="${pageContext.servletContext.contextPath}/resources/themes/img/avatar.png" class="img-circle" alt="User Image">
                            <p>
                                ${udetail.name}
                                <small>Member since <fmt:formatDate  dateStyle="short" pattern="dd MMM,yyyy" value="${udetail.createdAt}" /></small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <li class="user-body">
                            <div class="col-xs-4 text-center">
                                <a href="#">Followers</a>
                            </div>
                            <div class="col-xs-4 text-center">
                                <a href="#">Sales</a>
                            </div>
                            <div class="col-xs-4 text-center">
                                <a href="#">Friends</a>
                            </div>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div class="pull-right">
                                <a href="<c:url value="/logout" />" class="btn btn-default btn-flat"><i class="fa fa-sign-out"></i> <span> Sign out</span></a>
                            </div>
                        </li>

                    </ul>
                </security1:authorize>
            </li>
        </ul>
    </div>
</nav>