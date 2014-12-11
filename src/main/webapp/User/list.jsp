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
		title:'用户列表',
		style:'width:100%',
		rownumbers:true,
	    url:'User_getUsers.do',  
		idField:'id',
		treeField:'name',
		singleSelect:true,
		pagination:true,
		onDblClickRow:function(){edit("User_addInput.do?id=","userList")},
	    columns:[[  
	        {checkbox:true},  
	        {field:'code',title:'编号',width:100},  
	        {field:'name',title:'姓名',width:100},  
	        {field:'birthday',title:'生日',width:150,align:'right'},        
	        {field:'email',title:'邮件',width:150,align:'right'}        
	    ]],
	    toolbar: [
	  		    {
					iconCls: 'icon-add',
					text:'添加用户',
					handler: function(){add("User_addInput.do")}
				},'-',{
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){reload("userList")}
				},'-',{
					iconCls: 'icon-edit',
					text:'修改',
					handler: function(){edit("User_addInput.do?id=","userList")}
				},'-',{
					iconCls: 'icon-cancel',
					text:'删除',
					handler: function(){del("User_delete.do?user.id=","userList")}
				},'-',{
					iconCls: 'icon-edit',text:'分配角色',
					handler: function(){
						setRole();
					}
			}]
	    	
	});
});
function setRole(){
	var obj = getSelected("userList");
	if(obj){
		$('#roleFrame').html('');
		$('#roleFrame').attr("src","UserRole_list.do?userId="+obj.id);
		$('#roleWin').window('open');
	}
}
</script>
</head>

<body style="padding: 5px">
	<table id="userList" ></table>
	<div id="addWin" class="easyui-window" title="添加用户" style="width:400px;height:400px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" ></iframe>
    </div>
	<div id="roleWin" class="easyui-window" title="分配角色" style="width:600px;height:400px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="roleFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" ></iframe>
    </div>
</body>
</html>
