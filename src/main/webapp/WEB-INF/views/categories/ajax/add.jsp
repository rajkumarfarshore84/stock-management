<%-- 
    Document   : add
    Created on : 24 Feb, 2016, 3:50:51 PM
    Author     : node
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form  id="categoryPostForm" modelAttribute="defaultCategory" action="${contextPath}/admin/categories/addPost" commandName="editCategory" method="POST" cssClass="form-horizontal">
    <form:hidden path="categoryId" cssClass="form-control col-lg-3"></form:hidden>
    <div class="form-group">
        <div class="col-lg-12">
            <div class="col-lg-3">
                <form:label path="name" cssClass="control-label">Name :</form:label>
            </div>
            <div class="col-lg-5">
            <form:input path="name" cssClass="form-control col-lg-3" tabindex="1"></form:input>
            </div>
            <div class="col-lg-4">
                <div  class=" has-error"><span id="error-name" class="control-label"></span></div>
            </div>
        </div>
    </div>
</form:form>

