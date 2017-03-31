<%-- 
    Document   : list
    Created on : Jan 3, 2017, 3:34:51 PM
    Author     : node
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="/WEB-INF/tld/function.tld" prefix="util" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>

<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary" id="newPurchaseOrder" onclick="doLoadForm(['add','myStockModal','New Stock Entry']);">Stock Entry</a>
    </div>
</div>
<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Manufactured Date</th>
                <th>Product</th>
                <th>Qty</th>
                <th>Qty In</th>
                <th>Batch no</th>
                <th>MRP</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stock" items="${stockList}">
                <tr>
                <td><fmt:formatDate value="${stock.createdAt}" type="BOTH" dateStyle="MEDIUM"/></td>
                <td>${stock.productId.name}</td>
                <td>${stock.qty}</td>
                <td>${stock.weightIn}</td>
                <td>${stock.batchNo}</td>
                <td>${stock.mrp}</td>
                <td>
                        <a href="${contextPath}/${role}/manufacture/oilstock/viewbill/${util:encrypt(stock.companyMarginList.id)}"><span class="glyphicon glyphicon-list-alt" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a href="${contextPath}/${role}/manufacture/oilstock/edit/${util:encrypt(stock.companyMarginList.id)}"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!-- Modal -->
<div id="myStockModal" class="modal fade ">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="modelHead">Modal Header</h4>
      </div>
      <div class="modal-body" id="modelCnt">
        <p>Some text in the modal.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('postStock','stockForm')">Save</button>
      </div>
    </div>

  </div>
</div>


