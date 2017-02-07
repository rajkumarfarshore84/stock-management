<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form method="post" id="productPostForm" modelAttribute="defaultProduct" commandName="editProduct" cssClass="form-horizontal">
    <form:hidden path="id" cssClass="form-control col-lg-3"></form:hidden>
        <div class="form-group">
            <div class="col-lg-12">
                <div class="col-lg-3">
                <form:label path="name" cssClass="control-label">Product Name :</form:label>
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
                <form:label path="categoryId.categoryId" cssClass="control-label ">Category :</form:label>
                </div>
                <div class="col-lg-5">
                <form:select path="categoryId.categoryId" cssClass="form-control col-lg-3" tabindex="6">
                    <form:option value="0" label="--Select Category--" />
                    <form:options items="${categories}"/>
                </form:select>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-categotyId" class="control-label"></span></div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
                <form:label path="code" cssClass="control-label ">Product Code :</form:label>
                </div>
                <div class="col-lg-5">
                <form:input path="code" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-code" class="control-label"></span></div>
            </div>
        </div>
    </div>
</form:form>
