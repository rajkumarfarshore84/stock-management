<%-- 
    Document   : add
    Created on : 17 Feb, 2016, 7:15:57 PM
    Author     : node
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form:form method="post" id="userPostForm" modelAttribute="defaultuser" commandName="editUser" cssClass="form-horizontal">
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
            <div class="form-group">
                <div class="col-lg-12">
                    <div class="col-lg-3">
                    <form:label path="username" cssClass="control-label ">Username :</form:label>
                    </div>
                    <div class="col-lg-5">
                    <form:input path="username" cssClass="form-control col-lg-3" tabindex="2"></form:input>
                    </div>
                    <div class="col-lg-4">
                        <div  class=" has-error"><span id="error-username" class="control-label"></span></div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${editing == 'edit'}">
                    
                    <c:set var="displayElemant" value="style='display:none'"/>
                    <c:set var="displaycheckBox" value=""/>
                </c:when>
                <c:otherwise>
                    <c:set var="displaycheckBox" value="style='display:none'"/>
                    <c:set var="displayElemant" value=""/>
                </c:otherwise>
            </c:choose>
            
            <form:hidden path="id" cssClass="form-control col-lg-3"></form:hidden>
            
            <div class="form-group" ${displaycheckBox}>
                <div class="col-lg-12">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-5">
                        <input type="checkbox" name="changePassword" value="Change Password" onclick="showPassword();" id="changePassword"/>
                        <label for="changePassword">Change Password</label>
                    </div>
                </div>
            </div>        
            <div class="form-group" ${displayElemant} id="passShow1">
                <div class="col-lg-12">
                    <div class="col-lg-3">
                        <form:label path="password" title="${defaultuser.getPassword()}" cssClass="control-label ">Password :</form:label>
                    </div>
                    <div class="col-lg-5">
                        <c:choose>
                           <c:when test="${editing == 'edit'}">
                               <form:input path="password" type="password" cssClass="form-control col-lg-3"></form:input>
                           </c:when>
                           <c:otherwise>
                               <form:password path="password" cssClass="form-control col-lg-3" tabindex="3"></form:password>
                           </c:otherwise>
                       </c:choose>   
                    </div>
                    <div class="col-lg-4">
                       <div  class=" has-error"><span id="error-password" class="control-label"></span></div>     
                    </div>
                </div>
            </div>
            <div class="form-group" ${displayElemant} id="passShow2">
                <div class="col-lg-12">
                    <div class="col-lg-3">
                    <form:label path="passwordconfirm" cssClass="control-label ">Confirm Password :</form:label>
                    </div>
                    <div class="col-lg-5">
                        <c:choose>
                           <c:when test="${editing == 'edit'}">
                               <form:input path="passwordconfirm" type="password" cssClass="form-control col-lg-3"></form:input>
                           </c:when>
                           <c:otherwise>
                               <form:password path="passwordconfirm" cssClass="form-control col-lg-3" tabindex="4"></form:password>
                           </c:otherwise>
                       </c:choose>  
                    </div>
                    <div class="col-lg-4">
                        <div  class=" has-error"><span id="error-passwordconfirm" class="control-label"></span></div>
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <div class="col-lg-12">
                    <div class="col-lg-3">
                    <form:label path="email" cssClass="control-label ">Email :</form:label>
                    </div>
                    <div class="col-lg-5">
                    <form:input path="email" cssClass="form-control col-lg-3" tabindex="5"></form:input>
                    </div>
                    <div class="col-lg-4">
                        <div  class=" has-error"><span id="error-email" class="control-label"></span></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-12">
                    <div class="col-lg-3">
                    <form:label path="roleId.id" cssClass="control-label ">Role :</form:label>
                    </div>
                    <div class="col-lg-5">
                    <form:select path="roleId.id" cssClass="form-control col-lg-3" tabindex="6">
                        <form:option value="0" label="--Select Role--" />
                        <form:options items="${roles}"/>
                    </form:select>
                    </div>
                    <div class="col-lg-4">
                        <div  class=" has-error"><span id="error-roleId" class="control-label"></span></div>
                    </div>
                </div>
            </div>

    </form:form> 
</div>
<script>
    $(document).ready(function(){
       changingEnterKeyToTab();
    });
</script>