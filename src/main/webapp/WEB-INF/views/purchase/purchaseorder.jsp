<%-- 
    Document   : purchaseorder
    Created on : 3 Mar, 2016, 10:53:08 PM
    Author     : Rajkumar
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/WEB-INF/tld/function.tld" prefix="util" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>

<%@page import="java.net.URLEncoder" %>
<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="${contextPath}/${role}/inventory/placeorder/add" class="btn btn-primary" id="newPurchaseOrder" >Place Order</a>
    </div>
</div>
<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Ordered Date</th>
                <th>Supplier</th>
                <th>Order Total</th>
                <th>Placed By</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ordered" items="${listorders}">
                <tr>
                    <td><fmt:formatDate value="${ordered.orderedDate}" type="BOTH" dateStyle="long"/></td>
                    <td><c:out value="${ordered.vendorId.name}"/></td>
                    <td><c:out value="${ordered.total}"/></td>
                    <td><c:out value="${ordered.orderPlacedBy.name}"/></td>
                    <td width="10%" style="text-align: center;">
                        <a href="placeorder/viewOrder/${util:encrypt(ordered.entityId)}"><span class="glyphicon glyphicon-list-alt" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a href="placeorder/edit/${util:encrypt(ordered.entityId)}"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a>
                    </td>
                </tr>

            </c:forEach>
        </tbody>
    </table>
</div>
    