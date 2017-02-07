<%-- 
    Document   : view
    Created on : Dec 22, 2016, 5:45:07 PM
    Author     : node
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/WEB-INF/tld/function.tld" prefix="util" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                            <fmt:formatDate value="${purchasedInvoice.purchasedDate}" type="BOTH" dateStyle="medium"/>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="col-lg-5">
                            <label>Supplier: </label>
                        </div>
                        <div class="col-lg-7">
                            ${purchasedInvoice.vendorId.name}
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
                <th>Batch No</th>
                <th>Qty</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="itemList" items="${purchasedInvoice.purchasedItemsListSave}">
                <tr>
                    <td>${itemList.productId.name}</td>
                    <td>${itemList.batchId}</td>
                    <td>${itemList.pruchasedQty} ${itemList.qtyIn}</td>
                    <td>${itemList.baseAmount}</td>
                    <td>${itemList.baseAmount * itemList.pruchasedQty}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-lg-12 body-padding">
    <div class="row-fluid " style="text-align: center">
        <a href="${contextPath}/${role}/inventory/purchaseinvoice/${util:encrypt(0)}" class="btn btn-primary" id="newUser" >Back</a>
    </div>
</div>