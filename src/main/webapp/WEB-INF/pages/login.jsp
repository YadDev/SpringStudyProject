<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.myrow-container {
	margin: 20px;
}



</style>
<body>
	<div class="container"  align="center">
    <div class="row">
		<div class="span12">
			<form:form cssClass="form-horizontal" id="loginForm" modelAttribute="login" action="loginProcess" method="post">
			  <fieldset>
			  <br><br><br>
			    <div id="legend">
			      <legend class="">Login</legend>
			    </div>
			    <div class="control-group">
			      <!-- Username -->
			      <form:label class="control-label"  path="userName">Username</form:label>
			      <div class="controls">
			        <form:input type="text" id="username" name="userName" path="userName" placeholder="" class="input-xlarge"/>
			      </div>
			    </div>
			    <div class="control-group">
			      <!-- Password-->
			      <form:label class="control-label" path="password">Password</form:label>
			      <div class="controls">
			        <form:input type="password" id="password" name="password" path="password" placeholder="" class="input-xlarge"/>
			      </div>
			    </div>
			    <br>
			    <div class="control-group">
			      <!-- Button -->
			      <div class="controls">
			        <button class="btn btn-success">Login</button>
			      </div>
			    </div>
			  </fieldset>
			</form:form>
		</div>
	</div>
</div>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>
</body>
</html>