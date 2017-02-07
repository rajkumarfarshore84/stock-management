<%-- 
    Document   : template
    Created on : 18 Dec, 2015, 9:16:14 PM
    Author     : node
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles1" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="titleKey"> 
    <tiles:getAsString name="title" />
</c:set>
<html>
    <head>
        <title><tiles1:getAsString name="title"/></title>
        <tiles1:insertAttribute name="head"/>
        <meta charset="utf-8">
    </head>
    <body style="padding-top: 70px;" ng-app="billing">
        <div class="col-lg-12" >
            <div class="row-fluid " style="margin: 0 auto;">
                <div class="container-fluid col-lg-9 center-block" style="float:none;">
                    <tiles1:insertAttribute name="body" /> 
                </div>
            </div>
        </div>
    </body>
</html>
