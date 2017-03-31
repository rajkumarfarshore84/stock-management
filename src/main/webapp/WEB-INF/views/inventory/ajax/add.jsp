<%-- 
    Document   : add
    Created on : Jan 3, 2017, 3:40:56 PM
    Author     : node
--%>

<%@include file="../../jspfunctions.jsp"%>
<div class="body-padding">
    <form:form method="post" action="${contextPath}/${role}/manufacture/postStock" id="stockForm" modelAttribute="defaultstock" commandName="editStock" cssClass="form-horizontal">
        <fieldset class="">
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
                            <form:select path="manufId.productId.id" id="prodCode" style="width:100%" cssClass="form-control col-lg-3 input" tabindex="1">
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
                            <form:input path="manufId.batchNo" cssClass="form-control" style="width:100%" tabindex="2" id="prodBatch"/>
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
                            <form:select path="manufId.weightIn" style="width:100%"  cssClass="form-control col-lg-3 input" tabindex="3">
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
                            <form:input path="manufId.qty" cssClass="form-control" style="width:100%" tabindex="4" id="prodQty"/>
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
                            <form:input path="manufId.retailerMargin" cssClass="form-control" style="width:100%" tabindex="5" id="prodRtMargin" class="price"/>
                            <div  class=" has-error"><span id="error-rmargin" class="control-label"></span></div>
                        </div>
                    </div>
                </div>  
            </div>
        </fieldset>
        <fieldset >
            <legend> MRP Calculation</legend>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Labor Charge: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="labor" cssClass="form-control" style="width:100%" tabindex="6" id="mrp_labor"/>
                            <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Transport Charge: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="transport" cssClass="form-control" style="width:100%" tabindex="7" id="mrp_trans"/>
                            <div  class=" has-error"><span id="error-weight" class="control-label"></span></div>
                        </div>
                    </div>
                </div>        
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> EB Charge </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="eb" cssClass="form-control" style="width:100%" tabindex="8" id="mrp_eb"/>
                            <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Margin: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="margin" cssClass="form-control" style="width:100%" tabindex="9" id="mrp_margin"/>
                            <div  class=" has-error"><span id="error-weight" class="control-label"></span></div>
                        </div>
                    </div>
                </div>        
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Material Price: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="rmetrialPrice" cssClass="form-control" style="width:100%" tabindex="10" id="mrp_mprice"/>
                            <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Material Weight: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="metrialWeight" cssClass="form-control" style="width:100%" tabindex="11" id="mrp_mweight"/>
                            <div  class=" has-error"><span id="error-weight" class="control-label"></span></div>
                        </div>
                    </div>
                </div>        
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> Tax: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:select path="taxId.id"  id="mrp_tax" style="width:100%" onblur="getMrp()" cssClass="form-control col-lg-3 input" tabindex="12">
                                <form:option value="0" label="--Select Tax--" />
                                <form:options items="${taxList}"/>
                            </form:select>
                            <!-- form:input path="taxAmount" cssClass="form-control" onblur="getMrp()" style="width:100%" tabindex="12" id="mrp_tax" /-->
                            <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <label> MRP: </label>
                        </div>
                        <div class="col-lg-6">
                            <form:input path="manufId.mrp" cssClass="form-control" style="width:100%" tabindex="13" id="totalmrp"/>
                            <div  class=" has-error"><span id="error-batchno" class="control-label"></span></div>
                        </div>
                    </div>
                </div>            
            </div>
        </fieldset>
    </form:form>
</div>



