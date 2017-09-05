<%--
  User: Jay Prakash
  Date: 17/08/2017
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ranga Reddy">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment Information</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.myrow-container {
	margin: 20px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>	
<script	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.4.0/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.4.0/js/buttons.html5.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link href="https://cdn.datatables.net/buttons/1.4.0/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<script type="text/javascript">
	$(document).ready(function() {
		$("#agentCDError").hide();
		$("#dataForError").hide();
		$("#startDateError").hide();
		$("#endDateError").hide();
		$("#dateCompareError").hide();
		
	});
	$(function() {
		$('#startDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$('#endDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});

	function uploadRidlrData() {
		var table;
		var flag=true;
		var agentCD = $('#agentCd').val().trim();
		var dataFor = $('#dataFor').val().trim();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		alert("Hiiiiii");
		var startDate1 = new Date(startDate);
		var endDate1 = new Date(endDate);
		// 		alert(startDate1 + '   ' + endDate1);
		// 		alert(startDate1 < endDate1);

		if (agentCD == 'NONE') {
			$("#agentCDError").css("color", "red");
			$("#agentCDError").show();
			flag = false;
		} else {
			$("#agentCDError").hide();
		}
		if (dataFor == 'NONE') {
			$("#dataForError").css("color", "red");
			$("#dataForError").show();
			flag = false;
		} else {
			$("#dataForError").hide();
		}
		if (startDate == '') {
			$("#startDateError").css("color", "red");
			$("#startDateError").show();
			flag = false;
		} else {
			$("#startDateError").hide();
		}
		if (endDate == '') {
			$("#endDateError").css("color", "red");
			$("#endDateError").show();
			flag = false;
		} else {
			$("#endDateError").hide();
		}
		if (startDate1 > endDate1) {
			$("#dateCompareError").css("color", "red");
			$("#dateCompareError").show();
			flag = false;
		} else {
			$("#dateCompareError").hide();
		}

		if (flag) {
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/ajax/dataUploadAjaxCall',
				data : 'agentCD=' + agentCD + '&startDate=' + startDate
						+ '&endDate=' + endDate+ '&dataFor=' + dataFor,
				success : function(response) { 
					console.log("SUCCESS: ", response);
					var obj = JSON.parse(response);
					var testsTable = $('#agentData').DataTable({
						"bProcessing" : true,
						"aaData" : obj,
						"bPaginate": false,
						"sScrollX": "100%",
						"sScrollXInner": "100%",
						"sPaginationType": "full_numbers",
						"bDestroy" : true,
						"aoColumns" : [ 
						                {"mData" : "agentCD"},
						                {"mData" : "match"},
						                {"mData" : "tktAmount"},
// 						                {"mData" : "guid"},
						                {"mData" : "depotName"},
						                {"mData" : "regionName"},
						                {"mData" : "fullTicketCount"},
						                {"mData" : "halfTicketCount"},
						                {"mData" : "etimAmount"},
						                {"mData" : "fromName"},
						                {"mData" : "tillName"},
						                {"mData" : "transactionDate"},
// 						                {"mData" : "transactionTime"},
						                {"mData" : "concessionShortName"}						                
						                ],
		                "paging" : true,
						"pageLength" : 10,
						"scrollY" : 350,
						"ordering" : true,
						dom: 'Bfrtip',
				        buttons: [
				            'excelHtml5',
				            'csvHtml5',
				            'pageLength'
				        ]
					});
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert("ERROR..." + e);
					//display(e);
				},
				done : function(e) {
					console.log("DONE");
					alert("DONE...");
				}

			});

			// 		alert(table);
		}
	}

	
	function agentCDErrorHide() {
		$("#agentCDError").hide();
	}
	function dataForErrorHide() {
		$("#dataForError").hide();
	}
	function startDateErrorHide() {
		$("#startDateError").hide();
		$("#dateCompareError").hide();
	}
	function endDateErrorHide() {
		$("#endDateError").hide();
		$("#dateCompareError").hide();
	}
</script>
</head>
<body class=".container-fluid">
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">
					<div class="panel-heading">

						<div align="left">
							<b>User Name : ${loginSession.userName}</b>
						</div>
						<br>
						<div align="left">
							<c:if test="${loginSession.role== 'admin'}">
								<a href="createTran">Add New Transaction</a>
								<b>|</b>
							</c:if>
							<a href="summaryDetail">Summary Detail</a> <b>|</b> <a
								href="transactionDetails">Detail Report</a> <b>|</b><a
								href="paymentTransactionDetails">Transaction Details</a> <b>|</b>
							<a	href="paymentReconciliationDetails">Reconciliation Details</a> <b>|</b>
							<a	href="csvFileUpload">Upload Data</a> <b>|</b>
							<a	href="logout">Logout</a>
						</div>
					</div>
				</h3>
			</div>

			<div class="panel-body">
				<div align="center">
					<h4>
						<b>Upload Data</b>
					</h4>
				</div>
				<br> <br>
				<div id='errorDiv' align="center">
					<p id="agentCDError">#Please select the Agent Code</p>
					<p id="dataForError">#Please select the Data For</p>
					<p id="startDateError">#Please select the start date</p>
					<p id="endDateError">#Please select the end date</p>
					<p id="dateCompareError">#Please start date should be less than
						end date</p>
				</div>
				<form:form id="ridlrPayDetail" cssClass="form-horizontal"
					modelAttribute="pagentAcc" method="post" action="">
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="agentCd">Agent Code</form:label>
						</div>
						<div class="col-xs-6">
							<%--                             <form:hidden path="id" value="${ridlrPayObject.id}"/> --%>

							<form:select cssClass="form-control" path="agentCd" onfocus="agentCDErrorHide();">
								<form:option value="NONE" label="--- Select ---" />

								<form:options items="${agentCode}" />
							</form:select>

							<%--                             <form:input cssClass="form-control" path="agentCd" value=""/> --%>
						</div>
					</div>
					<div class="form-group" align="center">
						<b>Start Date : </b><input type="text" id="startDate" name="startDate" value="" readonly='true' onfocus="startDateErrorHide();"/>
						<b>End Date : </b><input  type="text" id="endDate" name="endDate" value="" readonly='true' onfocus="endDateErrorHide();"/>
					</div>
					<div class="form-group">
						<div class="row">
								<div class="col-xs-4"></div>
							<div class="col-xs-4" align="center">
								<input type="button" id="uploadRidlrData"
									class="btn btn-primary" value="Upload"
									onclick="uploadRidlrData()" />
							</div>
							<div class="col-xs-4"></div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
		<div>
			<table id="agentData"
				class="table table-striped table-bordered table-hover table-header-fixed">
		       <thead style="background-color: #bce8f1;">
                    <tr>
						<th>Agent CD</th>
						<th>Tkt Amount</th>
						<th>Transaction code</th>
						<th>Reason for failure</th>                        
                    </tr>
                    </thead>
			</table>
		</div>

	</div>


</body>
</html>