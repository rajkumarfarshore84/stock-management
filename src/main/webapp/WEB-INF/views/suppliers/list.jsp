<%-- 
    Document   : list
    Created on : 3 Mar, 2016, 6:20:38 PM
    Author     : node
--%>
<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-lg-12 body-padding" >
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary" id="newSupplier" onclick="doLoadForm(['add','mySupplierModel','New Supplier']);">New User</a>
    </div>
</div>

<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Name</th>
                <th>Address</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c1:forEach var="vendor" items="${vendorsList}">
                <tr>
                    <td><c1:out value="${vendor.name}"/></td>
                    <td>
                        <c1:set var="address" value="${vendor.address1}"/>
                        <c1:if test="${vendor.address2 !=''}">
                            <c1:set var="address" value="${vendor.address1}, ${vendor.address2}"/>
                        </c1:if>
                        <c1:out value="${address}"/></td>
                    <td><c1:out value="${vendor.email}"/></td>
                    <td><c1:out value="${vendor.phone}"/></td>
                    <td width="10%" style="text-align: center;"><a onclick="doLoadForm(['edit/<c1:out value="${vendor.vendorId}"/>','mySupplierModel','Edit Supplier']);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a></td>
                </tr>

            </c1:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div id="mySupplierModel" class="modal fade ">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="modelHead">Modal Header</h4>
      </div>
      <div class="modal-body" id="modelCnt">
        <p>Some text in the modal.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('addPost','supplierPostForm')">Save</button>
      </div>
    </div>

  </div>
</div>