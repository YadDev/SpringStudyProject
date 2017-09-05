<%--
  User: Trimax
  Date: 03/07/2017
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
<!--     <script type="text/javascript" src="/pages/jquery.js"></script> -->
<!--     <link rel="stylesheet" type="text/css" href="jquery.datetimepicker.css"/> -->
<!--     <script src="jquery.datetimepicker.full.js"></script> -->
<title>Payment Information</title>
<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.myrow-container {
	margin: 20px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#agentCDError").hide();
		$("#amountError").hide();
		$("#paymentModeError").hide();
	});

	function submitRidlrPayForm() {

		var flag = true;
		// getting the employee form values
		var name = $('#agentCd').val().trim();
		var mode = $('#modePay').val();
		var pay = $('#paymentAmount').val();
		// 		var regex = new RegExp("^\s*-?[1-9]\d*(\.0{1,2})?\s*$");	
		var regex = /^\d+$/;
// 		alert(pay + ' ' + regex.test(pay));

		if (name == 'NONE') {
			// 			alert('Please select Agent Code');
			$("#agentCDError").css("color", "red");
			$("#agentCDError").show();
// 			$('#agentCd').focus();
			flag = false;
		} else {
			$("#agentCDError").hide();
		}
		if (pay <= 0 || regex.test(pay) == false || pay == '') {
			// 			alert('Please enter proper amount');
// 			$('#paymentAmount').focus();
			$("#amountError").css("color", "red");
			$("#amountError").show();
			flag = false;
		} else {
			$("#amountError").hide();
		}

		if (mode == 'NONE') {
			// 			alert('Please select payment mode');
			$("#paymentModeError").css("color", "red");
			$("#paymentModeError").show();
// 			$('#paymentAmount').focus();
			flag = false;

		} else {
			$("#paymentModeError").hide();
		}

		return flag;
	}

	function agentCDErrorHide() {
		$("#agentCDError").hide();
	}
	function amountErrorrHide() {
		$("#amountError").hide();
	}
	function paymentModeErrorHide() {
		$("#paymentModeError").hide();
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
								<a	href="csvFileUpload">File Upload</a> <b>|</b>
								<a	href="logout">Logout</a>
						</div>
					</div>

					<!--                  <div align="left"><b> Ridlr Payment Details</b> </div> -->

					<!--                     <div align="right"><a href="getAllTrans">Back</a></div> -->
				</h3>
			</div>

			<div class="panel-body">
				<div align="center">
					<h4>
						<b>Add New Transaction</b>
					</h4>
				</div>
				<br> <br>
				<div id='errorDiv' align="center">
					<p id="agentCDError">#Please select the Agent Code</p>
					<p id="amountError">#Please enter valid amount or should not contain decimal value </p>
					<p id="paymentModeError">#Please select the payment mode Code</p>
				</div>
				<form:form id="ridlrPayDetail" cssClass="form-horizontal"
					modelAttribute="pagentAcc" method="post" action="saveRidlrPay">

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="agentCd">Agent Code</form:label>
						</div>
						<div class="col-xs-6">
							<%--                             <form:hidden path="id" value="${ridlrPayObject.id}"/> --%>

							<form:select cssClass="form-control" path="agentCd"
								onfocus="agentCDErrorHide();">
								<form:option value="NONE" label="--- Select ---" />

								<form:options items="${agentCode}" />
							</form:select>

							<%--                             <form:input cssClass="form-control" path="agentCd" value=""/> --%>
						</div>
					</div>

					<!--                     <div class="form-group"> -->
					<%--                         <div class="control-label col-xs-3"> <form:label path="dateTime" >DateTime </form:label> </div> --%>
					<!--                         <div class="col-xs-6"> -->
					<%--                             <form:hidden path="id" value="${ridlrPayObject.id}"/> --%>
					<%--                             <form:input  type="text" cssClass="form-control" path="dateTime"  /> --%>
					<!--                         </div> -->
					<!--                     </div> -->

					<div class="form-group">
						<form:label path="paymentAmount" cssClass="control-label col-xs-3">Pay Amount </form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="paymentAmount" value="" onfocus="amountErrorrHide();"/>
						</div>
					</div>

					<div class="form-group">
						<form:label path="modePay" cssClass="control-label col-xs-3">Mode Of Pay </form:label>
						<div class="col-xs-6">

							<form:select cssClass="form-control" path="modePay" onfocus="paymentModeErrorHide();">
								<form:option value="NONE" label="--- Select ---" />
								<form:option value="Cash" label="CASH" />
								<form:option value="check" label="CHECK" />
								<form:option value="dd" label="Demand Draft" />
								<form:option value="onBank" label="Online Banking" />

							</form:select>

							<%--                             <form:input cssClass="form-control" path="modePay" value=""/> --%>
						</div>
					</div>



					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-4">
								<input type="submit" id="saveRidlrPay" class="btn btn-primary"
									value="Save" onclick="return submitRidlrPayForm();" />
							</div>
							<div class="col-xs-4"></div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</body>
</html>