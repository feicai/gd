<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	$('#userList').datagrid({ 
		title:'流程定义列表',
		style:'width:100%',
		rownumbers:true,
	    url:'Process_getProcessDefinitionList.do',  
		idField:'id',
		treeField:'key',
		singleSelect:true,
		fitColumns:true,
		pagination:true,
	    columns:[[  
	        {checkbox:true},  
	        {field:'id',title:'编号',width:100},  
	        {field:'key',title:'流程定义主键',width:100},  
	        {field:'name',title:'名称',width:150},        
	        {field:'version',title:'版本',width:50},      
	        {field:'category',title:'模块',width:150},      
	        {field:'status',title:'状态',width:150}      
	    ]],
	    toolbar: [
	  		   {
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){reload("userList")}
				}
		]
	    	
	});
});

</script>
</head>

<body style="padding: 5px">
	<table id="userList" ></table>
</body>
</html>
