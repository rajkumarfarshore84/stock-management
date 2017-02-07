<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/WEB-INF/tld/function.tld" prefix="util" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<section class="sidebar">
    <security:authorize access="isAuthenticated()">
        <c:set var="role" value="${fn:toLowerCase(udetail.roleId.roleName)}"/>
        <div class="user-panel">
            <div class="pull-left image">
                <img alt="User Image" class="img-circle" src="${pageContext.servletContext.contextPath}/resources/themes/img/avatar.png">
            </div>
            <div class="pull-left info">
                <p>${udetail.name}</p>
                <!-- Status -->
                <a href="javascript:void(0)"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <ul class="sidebar-menu">
            
            <li class="header">MAIN NAVIGATION</li>
            <li class=" treeview" id="dashboardMenu" onclick="menuActivate()">
                <a href="${contextPath}/admin">
                    <i class="fa fa-dashboard active"></i> <span>Dashboard</span></i>
                </a>
            </li> 
            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                <li class="active" id="serviceMenu" >
                    <a href="javascript:void(0)" onclick="menuActivate()">
                        <i class="fa fa-edit"></i> <span>Services</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="active" id="usersMenu"><a href="${contextPath}/${role}/users/list"><i class="fa fa-circle-o"></i> Employees</a></li>
                        <li class="active" id="suppliersMenu"><a href="${contextPath}/${role}/suppliers/list"><i class="fa fa-circle-o"></i> Suppliers</a></li>
                        <li id="categoriesMenu"><a href="${contextPath}/${role}/categories/list"><i class="fa fa-circle-o"></i> Categories</a></li>
                        <li id="productsMenu"><a href="${contextPath}/${role}/products/list"><i class="fa fa-circle-o"></i> Products</a></li>
                        <li id="customerMenu"><a href="${contextPath}/${role}/customer/list/${util:encrypt(0)}"><i class="fa fa-circle-o"></i> Customer</a></li>
                        <li id="taxMenu"><a href="${contextPath}/${role}/tax/list/${util:encrypt(0)}"><i class="fa fa-circle-o"></i> Tax</a></li>
                    </ul>
                </li>
            </security:authorize>
            <li class="active" id="inventoryMenu" >
                <a href="javascript:void(0)" onclick="menuActivate()">
                    <i class="fa fa-product-hunt"></i> <span>Inventory</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="active" id="placeorderMenu"><a href="${contextPath}/${role}/inventory/placeorder"><i class="fa fa-shopping-cart"></i> Place Order</a></li>
                    <li id="purchaseinvoiceMenu"><a href="${contextPath}/${role}/inventory/purchaseinvoice/${util:encrypt(0)}"><i class="fa fa-shopping-bag"></i> Purchase Invoice</a></li>
                </ul>
            </li>
            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                <li class="active" id="maunfactureMenu" >
                    <a href="javascript:void(0)" onclick="menuActivate()">
                        <i class="fa fa-edit"></i> <span>Manufactured</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li  id="oilstockMenu"><a href="${contextPath}/${role}/manufacture/oilstock/${util:encrypt(0)}"><i class="fa  fa-industry"></i> Oil Stock</a></li>
                        <li  id="packingMenu"><a href="${contextPath}/${role}/manufacture/packing/${util:encrypt(0)}"><i class="fa fa-truck"></i> Packing</a></li>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                <li class="active" id="salesMenu" >
                    <a href="javascript:void(0)" onclick="menuActivate()">
                        <i class="fa fa-edit"></i> <span>Sales</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li  id="invoiceMenu"><a href="${contextPath}/${role}/sales/invoice/${util:encrypt(0)}"><i class="fa fa-cart-arrow-down"></i> Invoice</a></li>
                    </ul>
                </li>
            </security:authorize>
            <li class="active" id="reportMenu" >
                <a href="javascript:void(0)" onclick="menuActivate()">
                    <i class="fa fa-files-o"></i> <span>Reports</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <security:authorize access="hasAnyRole('ADMIN')">
                        <li class="active" id="purchaseReportMenu"><a href="${contextPath}/${role}/report/purchase"><i class="fa fa-circle-o"></i> Purchase</a></li>
                        <li id="salesReportMenu"><a href="${contextPath}/${role}/report/sales"><i class="fa fa-circle-o"></i> Sales</a></li>
                    </security:authorize>
                    <li id="stockReportMenu"><a href="${contextPath}/${role}/report/stock"><i class="fa fa-circle-o"></i> Stock</a></li>
                </ul>
            </li>
            <security:authorize access="hasAnyRole('ADMIN','ACCOUNTANT')">
            <li class="active" id="accountMenu" >
                <a href="javascript:void(0)" onclick="menuActivate()">
                    <i class="fa fa-book"></i> <span>Accounts</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="active" id="purchaseReportMenu"><a href="${contextPath}/${role}/account/ledger"><i class="fa fa-circle-o"></i> Ledger</a></li>
                    <li id="salesReportMenu"><a href="${contextPath}/${role}/account/receipt"><i class="fa fa-circle-o"></i> Receipt</a></li>
                    <li id="stockReportMenu"><a href="${contextPath}/${role}/account/bookkeep"><i class="fa fa-circle-o"></i> Book Keeping</a></li>
                </ul>
            </li>
            </security:authorize>
        </ul>
    </security:authorize>
</section>