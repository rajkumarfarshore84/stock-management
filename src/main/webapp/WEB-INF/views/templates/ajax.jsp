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
 
        <tiles:insertAttribute name="head"/>
    </head>
    <body style="padding-top: 70px;">
        <div class="col-lg-12" >
            <div class="row-fluid " style="margin: 0 auto;">
                <div class="container-fluid col-lg-9 center-block" style="float:none;">

                    <tiles:insertAttribute name="body" /> 
                </div>
            </div>
        </div>
    </body>
</html>
