<%-- 
    Document   : list_customer
    Created on : Feb 2, 2017, 7:46:36 PM
    Author     : node
--%>

<%@include file="../jspfunctions.jsp"%>

<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary fa fa-plus-circle" id="newPurchaseOrder" onclick="doLoadForm(['../add','myCustomerModal','New Customer Entry']);">  Customer Entry</a>
    </div>
</div>

<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Customer Name</th>
                <th>Company Name</th>
                <th>Contact no</th>
                <th>Email</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td>${customer.name}</td>
                    <td>${customer.comapnyName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.contactNumber}</td>
                    <td width="200">${customer.address}</td>
                    <td>
                        <a href="javascript:;" onclick="doLoadForm(['../edit/<c:out value="${util:encrypt(customer.id)}"/>','myCustomerModal','Edit Customer']);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
                        <a><span class="glyphicon glyphicon-trash" style="cursor: pointer;color: red"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</div>
<c:if test="${pageCount >1}">
    
    <div class="col-lg-12">
        <nav aria-label="Page navigation" style="text-align: center;">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li>
                        <a href="${util:encrypt(currentPage-1)}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="active"><a href="#"><c:out value="${i}" /> <span class="sr-only">(current)</span></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${util:encrypt(i)}"><c:out value="${i}" /></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < pageCount}">
                    <li>
                        <a href="${util:encrypt(currentPage+11)}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</c:if>

<!-- Modal -->
<div id="myCustomerModal" class="modal fade ">
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
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('../save','customerForm')">Save</button>
      </div>
    </div>

  </div>
</div>