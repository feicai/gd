<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>login</title>

<link rel="stylesheet" type="text/css" href="common/plugins/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="common/plugins/bootstrap/css/bootstrap-responsive.css" />
<script type="text/javascript" src="common/plugins/jquery.min.js"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
	background: url("common/images/bg.gif") repeat-x;
}
.form-signin {
	max-width: 400px;
	text-align:center;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin: 10px;
	padding: 7px 9px;
}
span{
	font-size:18px;
   	color: #0178D0;
   	font-weight: bold;
  	text-align: center;
   	white-space: nowrap;
}
</style>
<script type="text/javascript">
	function closeAlert(){
		$("#alert").hide();
	}
</script>
</head>

<body>
	<div class="container">
		<form id="loginForm" class="form-signin" action="login.do" method="get">
		<fieldset>
		<legend>毕业设计管理系统</legend>
		<table>
			<tr>
				<td colspan="2" >
				<c:if test="${not empty errorMessage }">
					<div id="alert" class="alert alert-error" style="margin-bottom:0px">
					    <button type="button" class="close" data-dismiss="alert" onclick="closeAlert()">&times;</button>
					    <strong>错误!</strong> ${errorMessage }
				    </div>
				</c:if>
				</td>
			</tr>
			<tr>
				<td width="120px">
				<span>登录名</span></td>
				<td><input type="text" name="userCode" value="${userCode }" class="" placeholder="LoginName"></td>
			</tr>
			<tr>
				<td><span>密码</span></td>
				<td><input type="password" name="password" value="${password }" class="" placeholder="Password"></td>
			</tr>
		</table>
        
		<input class="btn btn-large  btn-info" type="reset" value="重&nbsp;&nbsp;置">
		<input class="btn btn-large btn-primary" type="submit" value="登&nbsp;&nbsp;录">
        </fieldset>
      </form>

	</div>
</body>
</html>