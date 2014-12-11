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
		title:'流程实例列表',
		style:'width:100%',
		rownumbers:true,
	    url:'Process_getProcessInstanceList.do',  
		idField:'id',
		singleSelect:true,
		pagination:true,
		fitColumns:true,
		onDblClickRow:function(){edit("User_addInput.do?id=","userList")},
	    columns:[[  
	        {checkbox:true},  
	        {field:'id',title:'编号',width:100},  
	        {field:'businessKey',title:'业务主键',width:100},  
	        {field:'processDefinitionId',title:'流程ID',width:150},        
	        {field:'status',title:'状态',width:150}        
	    ]],
	    toolbar: [
	  		   {
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){reload("userList")}
				}]
	    	
	});
});

</script>
</head>

<body style="padding: 5px">
	<table id="userList" ></table>
</body>
</html>
