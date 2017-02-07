<%-- 
    Document   : list
    Created on : 24 Feb, 2016, 3:21:13 PM
    Author     : node
--%>
<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary" id="newcategory" onclick="doLoadForm(['add','myCategoryModal','New Category']);">New Category</a>
    </div>
</div>
<div class="col-lg-12">
    <div class="col-lg-6">
        <table class="table table-bordered table-striped ">
            <thead>
                <tr class="success">
                    <th> Category ID</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c1:forEach var="category" items="${categories}">
                    <tr>
                        <td class="col-lg-3"><c1:out value="${category.categoryId}"/></td>
                        <td><c1:out value="${category.name}"/></td>
                        <td class="col-lg-2" style="text-align: center;"><a onclick="doLoadForm(['edit/<c1:out value="${category.categoryId}"/>','myCategoryModal','Edit User']);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                            <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a></td>
                    </tr>

                </c1:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Modal -->
<div id="myCategoryModal" class="modal fade ">
  <div class="modal-dialog ">
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
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('addPost','categoryPostForm')">Save</button>
      </div>
    </div>

  </div>
</div>