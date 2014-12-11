<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"Menu_add.do",  
	    onSubmit: function(){  
	        return true;  
	    },  
	    success:function(data){  
	        $.messager.alert("提示","添加菜单成功！","info",function(){
	       		parent.reload();
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
			<form id="addForm"	action="Menu_add.do" method="post">
				<c:if test="${not empty menu }">
					<input name="menu.id" type="hidden" value="${menu.id }">
				</c:if>
				<table>
					<tr>
						<td class="td1">菜单名称</td>
						<td><input name="menu.name" type="text" value="${menu.name }"></td>
					</tr>
					<tr>
						<td class="td1">菜单链接</td>
						<td><input name="menu.url" type="text" value="${menu.url }"></td>
					</tr>
					<tr>
						<td class="td1">父菜单</td>
						<td>
						<c:if test="${empty menu.parent }">
							<c:set value="0" var="parentId"></c:set>
						</c:if>
						<c:if test="${not empty menu.parent }">
							<c:set value="${menu.parent.id }" var="parentId"></c:set>
						</c:if>
						
							<input id="cc1" name="menu.parent.id" value="0" class="easyui-combobox" data-options="  
						        valueField: 'id',  
						        textField: 'name',  
						        url: 'Menu_getRootMenu.do?id=${parentId }',
						        panelHeight:'auto'"/> 
						</td>
					</tr>
					<tr>
						<td class="td1">排序号</td>
						<td>
						<input id="orderNumber" name="menu.orderNumber" class="easyui-numberspinner" style="width:80px;"  
        					required="required" value="${menu.orderNumber }" data-options="min:0,max:100,editable:true">
						</td>
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
