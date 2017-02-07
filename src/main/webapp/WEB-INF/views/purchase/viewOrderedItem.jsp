<%-- 
    Document   : viewOrderedItem
    Created on : Jun 22, 2016, 6:36:14 PM
    Author     : node
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>
<div class="col-lg-12 body-padding">
    <div class="form-group">
        <div class="col-lg-12">
            <div class="row">
            <div class="col-lg-5">
                <div class="col-lg-12">
                    <div class="col-lg-5">
                        <label> Order Date: </label>
                    </div>
                    <div class="col-lg-7">
                        <fmt:formatDate value="${itemDetails.orderedDate}" type="BOTH" dateStyle="long"/>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-5">
                        <label> Order Placed By: </label>
                    </div>
                    <div class="col-lg-7">
                          ${itemDetails.orderPlacedBy.name}
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-5">
                        <label>Supplier: </label>
                    </div>
                    <div class="col-lg-7">
                        ${itemDetails.vendorId.name}
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12">
    <table class="table table-bordered table-striped" id="entryOrder">
        <thead>
            <tr class="success">
                <th>Name</th>
                <th>Weight</th>
                <th>Weight In</th>
                <th>Qty</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="itemList" items="${itemDetails.orderItemsListSave}">
            <tr>
                <td>${itemList.productId.name}</td>
                <td>${itemList.weight}</td>
                <td><c:out value="${weightUnit[itemList.weightIn]}"/> (${itemList.weightIn})</td>
                <td>${itemList.qty}</td>
                <td>Received</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-lg-12 body-padding">
    <div class="row-fluid " style="text-align: center">
        <a href="${contextPath}/${role}/inventory/placeorder" class="btn btn-primary" id="newUser" >Back</a>
    </div>
</div>
