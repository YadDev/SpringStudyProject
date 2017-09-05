<%--
  User: Ranga Reddy
  Date: 09/05/2015
  Time: 6:52 PM
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Transaction List</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
   

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
     <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
   
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
   
    <script>
    
    
    $(document).ready(function() {
    	
//     	 var translist = new Array();
//          <c:forEach items="${tranList}" var="list" varStatus="status"> 
//              trans = new Object();
//              trans.id = '${list.id}';
//              trans.agentCd = '${list.agentCd}';
//              trans.paymentAmount = '${list.paymentAmount}';
//              trans.dateTime = '${list.dateTime}';
//              trans.closingBal = '${list.closingBal}';
//              translist.push(trans);
//          </c:forEach> 
         
         
//          translist=JSON.stringify(translist);
//          alert(translist)
    	
         $('#example').DataTable()({
        	 
        	 "sPaginationType": "full_numbers",
             "bJQueryUI": true
         });
    } );
    </script>
   

</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                <div align="left"><b>Payment List:${loginSession.userName}</b> </div>
                <div align="left">
							<a href="createTran">Add New Transaction</a> <b>|</b> <a
								href="summaryDetail">Summary Detail</a> <b>|</b> <a
								href="transactionDetails">Detail Report</a> <b>|</b> <a href="logout">Logout</a>
						</div>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty tranList}">
                There are no Transaction
            </c:if>
            <c:if test="${not empty tranList}">   
            
<!--                 <form action="searchEmployee"> -->
<!--                     <div class="row"> -->
<!--                       <div class="col-md-6"><div class="col-md-6">Search Transaction:</div><div class="col-md-6"> <input type="text" name="searchName" id="searchName"> </div></div> -->
<!--                       <div class="col-md-4"><input class="btn btn-success" type='submit' value='Search'/></div> -->
<!--                     </div> -->
<!--                 </form>              -->
                                
                <table class="table table-hover table-bordered" id="example">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Id</th>
                        <th>Agent Code</th>
                        <th>Last Payment</th>
                        <th>Payment DateTime</th>
                        <th>Total Balance</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tranList}" var="tran">
                        <tr>
                        	<th><c:out value="${tran.id}"/></th>
                        	<th><c:out value="${tran.agentCd}"/></th>
<%--                             <th><c:out value="${tran.agentCd}"/></th> --%>
                            <th><c:out value="${tran.paymentAmount}"/></th>
                            <th><c:out value="${tran.dateTime}"/></th>
                            <th><c:out value="${tran.closingBal}"/></th>
<%--                             <th><a href="editEmployee?id=<c:out value='${tran.agentCd}'/>">Edit</a></th> --%>
<%--                             <th><a href="deleteEmployee?id=<c:out value='${tran.agentCd}'/>">Delete</a></th>                           --%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
   
    
    
  
</body>
</html>