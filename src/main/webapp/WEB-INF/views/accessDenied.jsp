<%-- 
    Document   : accessDenied
    Created on : 16 Feb, 2016, 7:10:07 PM
    Author     : node
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Dear <strong>${user}</strong>, You are not authorized to access this page
    <a href="<c:url value="/logout" />">Logout</a>