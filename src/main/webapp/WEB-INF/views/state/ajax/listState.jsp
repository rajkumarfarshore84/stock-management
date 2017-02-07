<%-- 
    Document   : listState
    Created on : Feb 6, 2017, 10:33:25 PM
    Author     : Rajkumar Ramasamy
--%>
<%@include file="../../jspfunctions.jsp"%>
<c:forEach var="state" items="${stateList}">
    <option value="${state.id}">${state.name}</option>
</c:forEach>
