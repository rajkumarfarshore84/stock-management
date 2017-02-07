<%-- 
    Document   : list_tax
    Created on : Feb 2, 2017, 7:49:16 PM
    Author     : node
--%>
<%@include file="../jspfunctions.jsp"%>

<div class="col-lg-12 body-padding">
    <div class="row-fluid pull-right">
        <a href="javascript:;" class="btn btn-primary fa fa-plus-circle" id="newPurchaseOrder" onclick="doLoadForm(['../add','myTaxModal','New Tax Entry']);">  Tax Entry</a>
    </div>
</div>
<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="success">
                <th>Country</th>
                <th>State</th>
                <th>Tax Type</th>
                <th>Tax %</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tax" items="${taxList}">
                <tr>
                    <td>${tax.statesId.countryId.name}</td>
                    <td>${tax.statesId.name}</td>
                    <td>${tax.type}</td>
                    <td>${tax.value}</td>
                    <td>
                        <a href="javascript:;" onclick="doLoadForm(['../edit/<c:out value="${util:encrypt(customer.id)}"/>','myTaxModal','Edit Tax Entry']);"><span class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></a> &nbsp; &nbsp;
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
<div id="myTaxModal" class="modal fade ">
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
        <button type="button" id="loadform" class="btn btn-primary" onclick="doAjaxUserPost('../save','taxForm')">Save</button>
      </div>
    </div>

  </div>
</div>