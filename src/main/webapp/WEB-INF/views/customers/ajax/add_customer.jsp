<%-- 
    Document   : add_customer
    Created on : Feb 3, 2017, 3:13:37 PM
    Author     : node
--%>

<%@include file="../../jspfunctions.jsp"%>
<div class="body-padding">
    <form:form method="post" action="${contextPath}/${role}/customer/save" id="customerForm" odelAttribute="editCustomer" commandName="editCustomer" cssClass="form-horizontal">
        <div class="">
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-5">
                            <label> Name:<span>*</span> </label>
                        </div>
                        <div class="col-lg-7">
                            <form:hidden path="id" cssClass="form-control col-lg-3"></form:hidden>
                            <form:input path="name" cssClass="form-control" style="width:100%" tabindex="3" id="name"/>
                            <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-5">
                            <label> Email : </label>
                        </div>
                        <div class="col-lg-7">
                            <form:input path="email" cssClass="form-control" style="width:100%" tabindex="3" id="email"/>
                            <div  class=" has-error"><span id="error-email" class="control-label"></span></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-5">
                            <label> Company Name:<span>*</span> </label>
                        </div>
                        <div class="col-lg-7">
                            <form:input path="comapnyName" cssClass="form-control" style="width:100%" tabindex="3" id="company_name"/>
                            <div  class=" has-error"><span id="error-comapnyName" class="control-label"></span></div>
                        </div>
                    </div>
                </div>            
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-5">
                            <label> Contact no:<span>*</span> </label>
                        </div>
                        <div class="col-lg-7">
                            <form:input path="contactNumber" cssClass="form-control" style="width:100%" tabindex="3" id="cnt_number"/>
                            <div  class=" has-error"><span id="error-contactNumber" class="control-label"></span></div>
                        </div>
                    </div>
                </div>           
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-lg-12">
                            <label> Communication Address:<span>*</span> </label>
                        </div>
                        <div class="col-lg-12">
                            <form:textarea path="address" cssClass="form-control" style="width:100%" tabindex="3" id="address"/>
                            <div  class=" has-error"><span id="error-address" class="control-label"></span></div>
                        </div>
                    </div>
                </div>  
            </div>
        </div>
    </form:form>
</div>
