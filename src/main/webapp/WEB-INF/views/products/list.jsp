<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary" id="newUser" onclick="doLoadForm(['add','myProductModal','New Product']);">New Product</a>
    </div>
</div>

<div class="col-lg-8">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Code</th>
                <th>Name</th>
                <th>Category</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c1:forEach var="product" items="${productList}">
                <tr>
                    <td><c1:out value="${product.code}"/></td>
                    <td><c1:out value="${product.name}"/></td>
                    <td><c1:out value="${product.categoryId.name}"/></td>
                    
                    <td width="10%" style="text-align: center;"><a onclick="doLoadForm(['edit/<c1:out value="${product.id}"/>','myProductModal','Edit Product']);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a></td>
                </tr>

            </c1:forEach>
        </tbody>
    </table>
</div>
<!-- Modal -->
<div id="myProductModal" class="modal fade ">
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
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('addPost','productPostForm')">Save</button>
      </div>
    </div>

  </div>
</div>

