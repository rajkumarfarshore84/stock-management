<%-- 
    Document   : template1
    Created on : 18 Dec, 2015, 11:01:06 PM
    Author     : node
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<c1:set var="titleKey"> 
    <tiles:getAsString name="title" />
</c1:set>
<html>
    <head>
        <title> <tiles:getAsString name="title"/>${pageTitle}</title>
        <link rel="shortcut icon" href="/oilmanager/resources/favicon.ico" type="image/x-icon" />
        <tiles:insertAttribute name="head"/>

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <header class="main-header">
                <tiles:insertAttribute name="header" /> 
            </header>
            <aside class="main-sidebar">
                <tiles:insertAttribute name="sidebar" />
            </aside>
            <div class="content-wrapper" style="float: left;width: 82.5%;">
                <tiles:insertAttribute name="body" /> 
            </div>
            
        </div>
    </body>
</html>
