<%-- 
    Document   : add
    Created on : 3 Mar, 2016, 6:22:35 PM
    Author     : node
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form method="post" id="supplierPostForm" modelAttribute="defaultSupplier" commandName="editSupplier" cssClass="form-horizontal">
    <form:hidden path="vendorId" cssClass="form-control col-lg-3"></form:hidden>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
            <form:label path="name" cssClass="control-label">Supplier Name :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="name" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
            <form:label path="address1" cssClass="control-label">Address1 :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="address1" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-address1" class="control-label"></span></div>
            </div>
        </div>
    </div>  
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
            <form:label path="address2" cssClass="control-label">Address2 :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="address2" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-address2" class="control-label"></span></div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
            <form:label path="email" cssClass="control-label">Email :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="email" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-email" class="control-label"></span></div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
            <form:label path="phone" cssClass="control-label">Contact no :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="phone" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-phone" class="control-label"></span></div>
            </div>
        </div>
    </div>
</form:form>
