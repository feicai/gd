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
	    url:'Role_getRoles.do',  
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
	        {field:'description',title:'角色描述',width:150,align:'left'}    
	    ]],
	    onDblClickRow:function(){edit("Role_addInput.do?id=","roleList")},
	    toolbar: [
		    {
				iconCls: 'icon-add',
				text:'添加角色',
				handler: function(){add("Role_addInput.do")}
			},'-',{
				iconCls: 'icon-reload',
				text:'刷新',
				handler: function(){reload("roleList")}
			},'-',{
				iconCls: 'icon-edit',
				text:'修改',
				handler: function(){edit("Role_addInput.do?id=","roleList")}
			},'-',{
				iconCls: 'icon-cancel',
				text:'删除',
				handler: function(){del("Role_delete.do?role.id=","roleList")}
			},'-',{
				iconCls: 'icon-edit',
				text:'分配权限',
				handler: function(){auth()}
		}]
	});
});
function auth(){
	var obj = getSelected("roleList");
	if(obj){
		$('#authFrame').html('');
		$('#authFrame').attr("src","Acl_menuList.do?roleId="+obj.id);
		$('#authWin').window('open');
	}
}
</script>
</head>

<body style="padding: 5px">
	<table id="roleList" ></table>
	<div id="addWin" class="easyui-window" title="添加角色" style="width:400px;height:400px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" >
        </iframe>
    </div>  
    <div id="authWin" class="easyui-window" title="分配权限" style="width:650px;height:450px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="authFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" ></iframe>
    </div>  
</body>
</html>
