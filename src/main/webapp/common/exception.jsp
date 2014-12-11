<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE>
<html>
	<head>
		<title>系统异常</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="style/formstyle.css">
	</head>

	<body>
		<fieldset>
			<legend>
				错误信息
			</legend>
			<div>
				<div class="enter">
					
						<table>
							<tr>
								<td>
									<img src="images/ico15.gif">
								</td>
								<td style="font-size: 20px; color: red">
									${exception.message }
								</td>
							</tr>
						</table>
				
					<input onclick="window.close()" type="button" class="buttom"
						value="关闭窗口" />
				</div>
			</div>
		</fieldset>
	</body>
</html>
