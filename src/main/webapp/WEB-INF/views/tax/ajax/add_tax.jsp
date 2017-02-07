<%-- 
    Document   : add_tax
    Created on : Feb 6, 2017, 7:21:50 PM
    Author     : Rajkumar Ramasamy
--%>
<%@include file="../../jspfunctions.jsp"%>
<div class="body-padding">
    <form:form method="post" action="${contextPath}/${role}/tax/save" id="taxForm" odelAttribute="editTax" commandName="editTax" cssClass="form-horizontal">
        <div class="row">
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-lg-5">
                        <label> Country<span>*</span> </label>
                    </div>
                    <div class="col-lg-7">
                        <form:hidden path="id" cssClass="form-control col-lg-3"></form:hidden>
                        <form:select path="statesId.countryId.id" id="country" onchange="getStates()" cssClass="form-control col-lg-3 input" tabindex="1">
                            <form:option value="0" label="--Select Country--" />
                            <form:options items="${countryList}"/>
                        </form:select>
                        <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-lg-5">
                        <label> State<span>*</span> </label>
                    </div>
                    <div class="col-lg-7">
                        <form:select path="statesId.id" id="stateLists" cssClass="form-control col-lg-3 input" tabindex="1">
                            <form:option value="0" label="--Select State--" />
                        </form:select>
                        
                        <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-lg-5">
                        <label> Type<span>*</span> </label>
                    </div>
                    <div class="col-lg-7">
                        <form:input path="type" cssClass="form-control" style="width:100%" tabindex="3" id="state"/>
                        <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-lg-5">
                        <label> Value in %<span>*</span> </label>
                    </div>
                    <div class="col-lg-7">
                        <form:input path="value" cssClass="form-control" style="width:100%" tabindex="3" id="state"/>
                        <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>