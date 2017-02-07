<%-- 
    Document   : addform
    Created on : Dec 21, 2016, 2:12:19 PM
    Author     : node
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>

<div class="col-lg-12 body-padding">
    <form:form method="post" action="${contextPath}/${role}/inventory/purchaseinvoice/postOrder"
               id="purchaseOrderForm" commandName="editInvoice" cssClass="form-horizontal">
        <div class="form-group">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="form-group">
                            <div class="col-lg-5">
                                <label> Order Date: </label>
                            </div>
                            <div class="col-lg-7">
                                <form:input path="purchasedDate" type="date" cssClass="form-control col-lg-3 input" id="purchasedDate"  tabindex="-1" />
                                <form:hidden path="id" style="width:80px;display:none" tabindex="-1"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-5">
                                <form:label path="vendorId.vendorId" cssClass="control-label ">Supplier:</form:label>
                                </div>
                                <div class="col-lg-7">
                                <form:select path="vendorId.vendorId" id="supplier" cssClass="form-control col-lg-3 input" tabindex="1">
                                    <form:option value="0" label="--Select Supplier--" />
                                    <form:options items="${suppliers}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-5">
                                <form:label path="orderAgainstId.entityId" cssClass="control-label ">Supplier:</form:label>
                                </div>
                                <div class="col-lg-7">
                                <form:select path="orderAgainstId.entityId" id="supplier" cssClass="form-control col-lg-3 input" tabindex="1">
                                    <form:option value="0" label="--Select Order--" />
                                    <form:options items="${preOrders}"/>
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5" id="supplierAddress">
                        <c:if test="${(editInvoice.vendorId!= null) &&  (!empty editInvoice.vendorId)}">
                            <div class="popover right" style="min-height: 135px;min-width: 500px;display: block;"> 
                                <div class="arrow" style="border-right-color: #005384;"></div> 
                                <h3 class="popover-title bg-green-gradient">${editInvoice.vendorId.name}</h3> 
                                <div class="popover-content bg-blue-active" style="border-bottom-left-radius: 8px;border-bottom-right-radius: 8px;"> 
                                    <p>${editInvoice.vendorId.address1},</p>
                                    <p>${editInvoice.vendorId.address2}.</p>
                                    <p>Contact: ${editInvoice.vendorId.phone}</p>
                                </div> 
                            </div>

                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- Product to order -->
        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="productSelected">
                <thead>
                    <tr class="success">
                        <th width="35%">Name</th>
                        <th width="15%">Batch No</th>
                        <th width="10%">Qty</th>
                        <th width="10%">Qty In</th>
                        <th width="15%">Price</th>
                        <th width="15%">Total</th>
                        <th width="5%"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr id="itemRow0">
                        <td width="35%">
                            <form:select path="purchasedItemsList[0].productId.id" id="prodCode" style="width:100%" cssClass="form-control col-lg-3 input" required="required" tabindex="2">
                                <form:option value="0" label="--Select Code--" />
                                <form:options items="${productsListByRaw}"/>
                            </form:select>
                        <form:hidden path="purchasedItemsList[0].itemId" id="itemId"/>
                        </td>
                        <td width="15%">
                            <form:input path="purchasedItemsList[0].batchId" style="width:100%;text-transform:uppercase" tabindex="3" id="prodWeight" required="required" />
                        </td>

                        <td width="10%">
                            <form:input path="purchasedItemsList[0].pruchasedQty" style="width:100%" tabindex="4" id="prodQty" required="required"/>
                        </td>
                        <td width="10%">
                            <form:select path="purchasedItemsList[0].qtyIn" style="width:100%"  id="prodUnit" cssClass="form-control col-lg-3 input" tabindex="5" required="required">
                                <form:option value="0" label="--Units--" />
                                <form:options items="${weightUnit}"/>
                            </form:select>
                        </td>

                        <td width="15%">
                            <form:input path="purchasedItemsList[0].baseAmount" style="width:100%" tabindex="6" onblur="calRowTotal(this)" id="prodPrice" required="required"/>
                        </td>
                        <td width="15%">
                            <form:input path="purchasedItemsList[0].rowTotal" style="width:100%" tabindex="7" id="rowTotal" class="price" required="required"/>
                        </td>
                        <td width="5%"><button type="button" tabindex="6" class="add_row" 
                                               onclick="addNewRow('productSelected', 'entryItems');
                                                       sumtotal('entryItems', 'subTotal');
                                                       focuseTo('prodCode');
                                                       sumOrderTotal('prodGrandTotal')"> *</button></td>
                    </tr> 

                </tbody>
            </table>
        </div>

        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="entryItems">
                <thead>
                    <tr class="success">
                        <th width="35%">Name</th>
                        <th width="15%">Batch No</th>
                        <th width="10%">Qty</th>
                        <th width="10%">Qty In</th>
                        <th width="15%">Price</th>
                        <th width="15%">Total</th>
                        <th width="5%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${(editInvoice.purchasedItemsListSave != null) &&  (!empty editInvoice.purchasedItemsListSave)}">
                        <c:forEach var="itemList" items="${editInvoice.purchasedItemsListSave}" varStatus="itemCount">
                            <tr id="itemRow${itemCount.index+1}">
                                <td width="35%">
                                    <form:select path="purchasedItemsList[${itemCount.index+1}].productId.id" id="prodCode1" value="${itemList.productId.id}" style="width:100%" cssClass="form-control col-lg-3 input" tabindex="-1">
                                        <form:option value="0" label="--Select Code--" />
                                        <form:options items="${productsListByRaw}"/>
                                    </form:select>
                                    <form:hidden path="purchasedItemsList[${itemCount.index+1}].itemId" id="itemId"/>
                                </td>
                                <td width="15%">
                                    <form:input path="purchasedItemsList[${itemCount.index+1}].batchId" style="width:100%;text-transform:uppercase" value="${itemList.batchId}" tabindex="-1" id="prodWeight1"/>
                                </td>

                                <td width="10%">
                                    <form:input path="purchasedItemsList[${itemCount.index+1}].pruchasedQty" value="${itemList.pruchasedQty}" style="width:100%" tabindex="-1" id="prodQty1"/>
                                </td>
                                <td width="10%">
                                    <form:select path="purchasedItemsList[${itemCount.index+1}].qtyIn" value="${weightUnit[itemList.qtyIn]}" style="width:100%"  id="prodUnit1" cssClass="form-control col-lg-3 input" tabindex="-1">
                                        <form:option value="0" label="--Units--" />
                                        <form:options items="${weightUnit}"/>
                                    </form:select>
                                </td>

                                <td width="15%">
                                    <form:input path="purchasedItemsList[${itemCount.index+1}].baseAmount" value="${itemList.baseAmount}" style="width:100%" tabindex="-1" onblur="calRowTotal(this);
                                                sumtotal('entryItems', 'subTotal');
                                                sumOrderTotal('prodGrandTotal')" id="prodPrice1"/>
                                </td>
                                <td width="15%">
                                    <form:input path="purchasedItemsList[${itemCount.index+1}].rowTotal" value="${itemList.rowTotal}" style="width:100%" tabindex="7" id="rowTotal1" class="price" />
                                </td>
                                <td width="5%"><button type="button" tabindex="6" class="add_row" 
                                                       onclick="addNewRow('productSelected', 'entryItems');
                                                       sumtotal('entryItems', 'subTotal');
                                                       focuseTo('prodCode');
                                                       sumOrderTotal('prodGrandTotal')"> *</button></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="col-lg-12">
            <div class="row-fluid pull-right totals" style="width:40%">
                <table class="table col-lg-8">
                    <tbody>
                        <tr>
                            <td>Subtotal</td>
                            <td width="50%"><form:input path="baseSubtotal" style="width:100%;text-align: right;" value="" onblur="sumOrderTotal('prodGrandTotal')" tabindex="6" class="sumTotal" id="subTotal"/></td>
                        </tr>
                        <tr>
                            <td>Discount</td>
                            <td width="50%"><form:input path="baseDiscount" style="width:100%;text-align: right;" value="" tabindex="6" onblur="sumOrderTotal('prodGrandTotal')" class="sumTotal" id="discount"/></td>
                        </tr>
                        <tr>
                            <td>Shipping Amount</td>
                            <td width="50%"><form:input path="baseShippingAmount" style="width:100%;text-align: right;" value="" tabindex="6" onblur="sumOrderTotal('prodGrandTotal')" class="sumTotal" id="shipping"/></td>
                        </tr>
                        <tr>
                            <td>Tax Amount</td>
                            <td width="50%"><form:input path="baseTaxAmount" style="width:100%;text-align: right;" value="" tabindex="6" onblur="sumOrderTotal('prodGrandTotal')" class="sumTotal" id="tax"/></td>
                        </tr>
                        <tr>
                            <td>Grand Total</td>
                            <td width="50%"><form:input path="baseTotal" style="width:100%;text-align: right;" value="" tabindex="6" id="prodGrandTotal"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-lg-12 body-padding">
            <div class="row-fluid " style="text-align: center">
                <a href="${contextPath}/${role}/inventory/purchaseinvoice" class="btn btn-primary" id="newUser" >Cancel</a>
                <button class="btn btn-primary" type="submit">Save Bill</button>
            </div>
        </div>
    </form:form>
</div>
<script>
    $(function () {
        $("#purchasedDate").datepicker({dateFormat: "dd-mm-yy", maxDate: '+0d'});

        $("#supplier").change(function () {
            $.ajax({
                url: "${contextPath}/${role}/inventory/supplier/address/" + $(this).val(),
                success: function (resp) {
                    $('#supplierAddress').html(resp);
                }
            });
        });

    });
</script>