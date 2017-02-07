<%-- 
    Document   : address
    Created on : Dec 21, 2016, 11:01:13 PM
    Author     : node
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${(supplierAddress != null) &&  (!empty supplierAddress)}">

    <div class="popover right" style="min-height: 135px;min-width: 500px;display: block;"> 
        <div class="arrow" style="border-right-color: #005384;"></div> 
        <h3 class="popover-title bg-green-gradient" >${supplierAddress.name}</h3> 
        <div class="popover-content bg-blue" style="border-bottom-left-radius: 8px;border-bottom-right-radius: 8px;"> 
            <p>${supplierAddress.address1},</p>
            <p>${supplierAddress.address2}.</p>
            <p>Contact: ${supplierAddress.phone}</p>
        </div> 
    </div>

</c:if>