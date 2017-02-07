<%-- 
    Document   : placeOrder
    Created on : 3 Mar, 2016, 11:17:25 PM
    Author     : Rajkumar
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>
<div class="col-lg-12 body-padding">
    <form:form method="post" action="${contextPath}/${role}/inventory/placeorder/postOrder" id="purchaseOrderForm" modelAttribute="defaultorder" commandName="editOrder" cssClass="form-horizontal">
        <div class="form-group">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="form-group">
                            <div class="col-lg-5">
                                <label> Order Date: </label>
                            </div>
                            <div class="col-lg-7">
                                <jsp:useBean id="now" class="java.util.Date"/>    
                                <fmt:formatDate value="${now}" dateStyle="long"/>
                                <form:hidden path="entityId" style="width:80px;display:none" tabindex="-1"/>
                                <form:input path="orderPlacedBy.id" style="width:80px;display:none" tabindex="-1"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-5">
                                <form:label path="vendorId.vendorId" cssClass="control-label ">Supplier: :</form:label>
                                    <label>Supplier: </label>
                                </div>
                                <div class="col-lg-7">
                                <form:select path="vendorId.vendorId" id="supplier" cssClass="form-control col-lg-3 input" tabindex="1">
                                    <form:option value="0" label="--Select Supplier--" />
                                    <form:options items="${suppliers}"/>
                                </form:select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Product to order -->
        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="productSelected">
                <tbody>
                    <tr>
                        <td width="40%">
                            <form:select path="orderItemsList[0].productId.id" id="prodCode" style="width:100%" cssClass="form-control col-lg-3 input" tabindex="2">
                                <form:option value="0" label="--Select Code--" />
                                <form:options items="${productsListByRaw}"/>
                            </form:select>
                        </td>
                        <td style="width:15%">
                            <form:input path="orderItemsList[0].weight" style="width:100%" tabindex="3" id="prodWeight"/>
                        </td>
                        <td width="15%">
                            <form:select path="orderItemsList[0].weightIn" style="width:100%"  id="prodUnit" cssClass="form-control col-lg-3 input" tabindex="4">
                                <form:option value="0" label="--Units--" />
                                <form:options items="${weightUnit}"/>
                            </form:select>
                        </td>
                        <td width="20%">
                            <form:input path="orderItemsList[0].qty" style="width:100%" tabindex="5" id="prodQty"/>
                        </td>
                        <td width="20%">
                            <form:input path="orderItemsList[0].price" style="width:100%" tabindex="6" id="prodPrice" class="price"/>
                        </td>
                        <td width="5%"><button type="button" tabindex="6" class="add_row"> *</button></td>
                    </tr> 

                </tbody>
            </table>
        </div>
        <!-- end -->
        <div class="col-lg-12">
            <table class="table table-bordered table-striped" id="entryOrder">
                <thead>
                    <tr class="success">
                        <th width="40%">Name</th>
                        <th width="15%">Weight</th>
                        <th width="15%">Weight In</th>
                        <th width="20%">Qty</th>
                        <th width="20%">Price/unit</th>
                        <th width="5%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${(editOrder.orderItemsListSave != null) &&  (!empty editOrder.orderItemsListSave)}">
                        <c:forEach var="itemList" items="${editOrder.orderItemsListSave}" varStatus="itemCount">
                            <tr>
                                <td width="40%">
                                    <form:select path="orderItemsList[${itemCount.index+1}].productId.id" style="width:100%" value="${itemList.productId.name}" id="prodCode1" cssClass="form-control col-lg-3 input" tabindex="-1">
                                        <form:option value="0" label="--Select Code--" />
                                        <form:options items="${productsListCode}"/>
                                    </form:select>
                                </td>
                                <td width="15%"><form:input path="orderItemsList[${itemCount.index+1}].weight" style="width:100%" value="${itemList.weight}" tabindex="-1" id="prodWeight1"/></td>
                                <td width="15%">
                                    <form:select path="orderItemsList[${itemCount.index+1}].weightIn" style="width:100%" value="${weightUnit[itemList.weightIn]}"  id="prodUnit1" cssClass="form-control col-lg-3 input" tabindex="-1">
                                        <form:option value="0" label="--Units--" />
                                        <form:options items="${weightUnit}"/>
                                    </form:select>
                                </td>
                                <td width="20%"><form:input path="orderItemsList[${itemCount.index+1}].qty" style="width:100%" value="${itemList.qty}" tabindex="-1" id="prodQty1"/></td>
                                <td width="20%"><form:input path="orderItemsList[${itemCount.index+1}].price" style="width:100%" value="${itemList.price}" tabindex="-1" id="prodPrice1" class="price"/></td>
                                <td width="5%"><a href="javascript:void(0);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="col-lg-12">
            <div class="row-fluid pull-right" style="width:28%">
                <table class="table col-lg-8">
                    <tbody>
                        <tr>
                            <td>Subtotal</td>
                            <c:if test="${(editOrder != null) &&  (!empty editOrder)}">
                                <td width="70%"><form:input path="total" style="width:100%" value="${editOrder.total}" tabindex="6" id="prodTotal"/></td>
                            </c:if>

                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-lg-12 body-padding">
            <div class="row-fluid " style="text-align: center">
                <a href="${contextPath}/${role}/inventory/placeorder" class="btn btn-primary" id="newUser" >Cancel</a>
                <button class="btn btn-primary" type="submit">Place Order</button>
            </div>
        </div>
    </form:form>
</div>
<script>

    $(document).ready(function () {
        var i = $("#entryOrder tbody").length;
        if($("#entryOrder tbody").length>1){
            i++;
        }
        $('#supplier').focus();
        $("select[id^='prodCode']").change(function () {
            id = $(this).attr('id');
            var lastChar = id.substr(id.length - 1);
            //alert(lastChar);
            $("#prodName" + lastChar).val($(this).val());
        });
        $('.add_row').click(function () {
            var cloned = $("#productSelected tbody tr:last").clone(true);
            cloned.find("td:last").empty();
            cloned.find("select").each(function () {
                $(this).attr({
                    'id': function (_, id) {
                        return id + i
                    },
                    'name': function (_, name) {
                        return name.replace("[0]", "[" + i + "]");
                    },
                    'tabindex': '-1',
                    'value': ''
                });
            });
            cloned.find("input").each(function () {
                $(this).attr({
                    'id': function (_, id) {
                        return id + i
                    },
                    'name': function (_, name) {
                        return name.replace("[0]", "[" + i + "]")
                    },
                    'tabindex': '-1',
                    'value': ''
                });
            });
            $("#productSelected tbody tr:last").find("select").each(function () {
                cloned.find('select[id=' + this.id + i + ']').val(this.value);
            });
            $("#productSelected tbody tr:last").find("input").each(function () {
                valueIp = this.value;
                if(isNumber(this.value)){
                    valueIp = toDecimal(this.value);
                }
                cloned.find(':input[id=' + this.id + i + ']').val(valueIp);
            });
            $("#entryOrder tbody").append(cloned);
            i++;
            $("#productSelected tbody tr:last").find("select").each(function () {
                $(this).val(0);
            });
            $("#productSelected tbody tr:last").find("input").each(function () {
                $(this).val(0);
            });
            $("select[id^='prodCode']").change(function () {
                id = $(this).attr('id');
                var lastChar = id.substr(id.length - 1);
                $("#prodName" + lastChar).val($(this).val());
            });
            var sum = 0;
            var totalCol = Number($("#entryOrder input[id^='prodPrice']").length);
            
            for (j = 1; j <= totalCol; j++) {
                price = Number($('#prodPrice' + j).val());
                weight = Number($('#prodWeight' + j).val());
                sum += (price * weight);
            }

            $('#prodTotal').val(toDecimal(sum));
            $('#prodCode').focus();
        })



        function isNumber(n) {
            return !isNaN(parseFloat(n)) && isFinite(n);
        }
        function toDecimal(n) {
            return parseFloat(Math.round(n * 100) / 100).toFixed(4);
        }


    });
</script>