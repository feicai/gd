<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"User_add.do",  
	    onSubmit: function(){  
	        return true;  
	    },  
	    success:function(data){  
	        $.messager.alert("提示","添加用户成功！","info",function(){
	       		parent.reload("userList");
		        parent.closeWin();
	        });
	    }  
	});
	
});
function doSubmit(){
	$('#addForm').submit();
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:10px;">
			<form id="addForm"	action="User_add.do" method="post">
				<c:if test="${not empty user }">
					<input name="user.id" type="hidden" value="${user.id }" >
				</c:if>
				<table>
					<tr>
						<td class="td1">编号</td>
						<td><input name="user.code" class="easyui-validatebox" required="required" type="text" value="${user.code }"></td>
					</tr>
					<tr>
						<td class="td1">姓名</td>
						<td><input name="user.name"  class="easyui-validatebox" required="required" type="text" value="${user.name }"></td>
					</tr>
					<tr>
						<td class="td1">密码</td>
						<td><input name="user.password" class="easyui-validatebox" required="required" type="text" value="${user.password }"></td>
					</tr>
					<tr>
						<td class="td1">出生年月</td>
						<td><input name="user.birthday" class="easyui-datebox" required="required" type="text" value="${user.birthday }"></td>
					</tr>
					<tr>
						<td class="td1">邮件</td>
						<td><input name="user.email" class="easyui-validatebox" data-options="required:true,validType:'email'" type="text" value="${user.email }"></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
					onclick="doSubmit()">提交</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				 href="javascript:void(0)" onclick="parent.closeWin();">取消</a>
		</div>
	</div>    
</body>
</html>
