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
		title:'角色列表',
		style:'width:100%',
		rownumbers:true,
	    url:'UserRole_getUserRoleMapping.do?user.id=${param.userId}',  
		idField:'id',
		treeField:'name',
		singleSelect:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15],
	    columns:[[  
	        {checkbox:true},  
	        {field:'ename',title:'角色标志',width:100},  
	        {field:'cname',title:'角色名称',width:100},  
	        {field:'description',title:'角色描述',width:150,align:'left'},    
	        {field:'flag',title:'操作',width:150,align:'left',
	        	formatter: function(value,row,index){
	        		var img = 'cancel';
	        		if(value){
	        			img = 'ok';
	        		}
					return "<span onclick='setRole("+row.roleId+")'><img id='img"+row.roleId+"' src='common/images/"+img+".png' ><span>";
				}	
	        },    
	    ]]
	});
});
function setRole(roleId){
	$.ajax({
			url:"UserRole_setRole.do?userRole.user.id=${param.userId}&userRole.role.id="+roleId,
			success:function(data){
				var img = (data==1?"ok":"cancel");
				$("#img"+roleId).attr("src","common/images/"+img+".png");
			}
	});
}
</script>
</head>

<body style="padding: 5px">
	<table id="roleList" ></table>
</body>
</html>
