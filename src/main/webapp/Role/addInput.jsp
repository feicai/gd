<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"Role_add.do",  
	    onSubmit: function(){  
	        return true;  
	    },  
	    success:function(data){  
		       $.messager.alert("提示","添加角色成功","info",function(){
		       		parent.reload("roleList");
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
				<c:if test="${not empty role }">
					<input name="role.id" type="hidden" value="${role.id }" >
				</c:if>
				<table>
					<tr>
						<td class="td1">角色标志</td>
						<td><input name="role.ename" class="easyui-validatebox" required="required" type="text" value="${role.ename }"></td>
					</tr>
					<tr>
						<td class="td1">角色名称</td>
						<td><input name="role.cname"  class="easyui-validatebox" required="required" type="text" value="${role.cname }"></td>
					</tr>
					<tr>
						<td class="td1">角色描述</td>
						<td><input name="role.description" class="easyui-validatebox" required="required" type="text" value="${role.description }"></td>
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
