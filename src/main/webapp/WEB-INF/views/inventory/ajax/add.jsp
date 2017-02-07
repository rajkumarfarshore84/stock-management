<%-- 
    Document   : add
    Created on : Jan 3, 2017, 3:40:56 PM
    Author     : node
--%>

<%@include file="../../jspfunctions.jsp"%>
<div class="body-padding">
    <form:form method="post" action="${contextPath}/${role}/manufacture/postStock" id="stockForm" modelAttribute="defaultstock" commandName="editStock" cssClass="form-horizontal">
            <div class="">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label> Manufactured Date: </label>
                            </div>
                            <div class="col-lg-6">
                                <jsp:useBean id="now" class="java.util.Date"/>    
                                <fmt:formatDate value="${now}" dateStyle="long"/>
                                <form:hidden path="id" cssClass="form-control col-lg-3"></form:hidden>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-lg-6">
                                    <label> Product: </label>
                                </div>
                                <div class="col-lg-6">
                                <form:select path="productId.id" id="prodCode" style="width:100%" cssClass="form-control col-lg-3 input" tabindex="2">
                                    <form:option value="0" label="--Select Code--" />
                                    <form:options items="${productsListByInventory}"/>
                                </form:select>
                                <div  class=" has-error"><span id="error-product" class="control-label"></span></div>
                            </div>
                        </div>
                    </div>        
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label> Batch: </label>
                            </div>
                            <div class="col-lg-6">
                                <form:input path="batchNo" cssClass="form-control" style="width:100%" tabindex="3" id="prodBatch"/>
                                <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label> Weight In: </label>
                            </div>
                            <div class="col-lg-6">
                                <form:select path="weightIn" cssClass="form-control" style="width:100%"  id="prodWeight" cssClass="form-control col-lg-3 input" tabindex="4">
                                    <form:option value="0" label="--Units--" />
                                    <form:options items="${weightUnit}"/>
                                </form:select>
                                <div  class=" has-error"><span id="error-weight" class="control-label"></span></div>
                            </div>
                        </div>
                    </div>        
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label> Qty: </label>
                            </div>
                            <div class="col-lg-6">
                                <form:input path="qty" cssClass="form-control" style="width:100%" tabindex="5" id="prodQty"/>
                                <div  class=" has-error"><span id="error-qty" class="control-label"></span></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label> Retailer Margin%: </label>
                            </div>
                            <div class="col-lg-6">
                                <form:input path="retailerMargin" cssClass="form-control" style="width:100%" tabindex="6" id="prodRtMargin" class="price"/>
                                <div  class=" has-error"><span id="error-rmargin" class="control-label"></span></div>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
    </form:form>
</div>



