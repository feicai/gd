<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	$('#roleList').datagrid({ 
		title:'毕业设计审核列表',
		style:'width:90%',
		rownumbers:true,
	    url:'Design_getApproveList.do?approve.catelog=design&approve.duixiang=${design.id}',  
		idField:'id',
		singleSelect:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15],
	    columns:[[  
	        {checkbox:true},  
	        {field:'id',title:'ID',width:100},      
	        {field:'advice',title:'审核意见',width:200},  
	        {field:'common',title:'备注',width:160}    
	    ]]
	});
});

</script>
</head>

<body style="padding: 5px">
	<table id="roleList" ></table>
 
</body>
</html>
