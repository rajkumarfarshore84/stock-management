<%-- 
    Document   : login
    Created on : 16 Feb, 2016, 7:07:58 PM
    Author     : node
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="mainWrapper" class="ng-cloak" ng-controller="HomeController as home">
    <div class="col-lg-5 col-lg-offset-3">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                <c:url var="loginUrl" value="/login" />
                    <form action="${loginUrl}" method="post" class="form-horizontal">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p>Invalid username and password.</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p>You have been logged out successfully.</p>
                            </div>
                        </c:if>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                        <div class="form-actions">
                            <button type="submit" class="btn btn-block btn-primary btn-default col-lg-1 fa fa-sign-in"> <span> Login</span></button>
                            
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
