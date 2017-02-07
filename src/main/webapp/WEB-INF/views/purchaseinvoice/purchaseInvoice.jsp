<%-- 
    Document   : purchaseInvoice
    Created on : 3 Mar, 2016, 10:53:29 PM
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
        <a href="${contextPath}/${role}/inventory/purchase/add" class="btn btn-primary" id="newPurchaseOrder" >Purchase Invoice</a>
    </div>
</div>
<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Purchased Date</th>
                <th>Supplier</th>
                <th>Total</th>
                <th>Discount</th>
                <th>Shipping</th>
                <th>Tax</th>
                <th>Grand Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="invoice" items="${invoceList}">
                <tr>
                    <td><fmt:formatDate value="${invoice.purchasedDate}" type="BOTH" dateStyle="MEDIUM"/></td>
                    <td>${invoice.vendorId.name}</td>
                    <td>${invoice.baseSubtotal}</td>
                    <td>${invoice.baseDiscount}</td>
                    <td>${invoice.baseShippingAmount}</td>
                    <td>${invoice.baseTaxAmount}</td>
                    <td>${invoice.baseTotal}</td>
                    <td>
                        <a href="${contextPath}/${role}/inventory/purchase/viewbill/${util:encrypt(invoice.id)}"><span class="glyphicon glyphicon-list-alt" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a href="${contextPath}/${role}/inventory/purchase/edit/${util:encrypt(invoice.id)}"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>
<c:if test="${pageCount >1}">
    
    <div class="col-lg-12">
        <nav aria-label="Page navigation" style="text-align: center;">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li>
                        <a href="${util:encrypt(currentPage-1)}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="active"><a href="#"><c:out value="${i}" /> <span class="sr-only">(current)</span></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${util:encrypt(i)}"><c:out value="${i}" /></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < pageCount}">
                    <li>
                        <a href="${util:encrypt(currentPage+11)}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</c:if>
