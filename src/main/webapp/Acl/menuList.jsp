<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	$('#menuList').treegrid({ 
		title:'菜单列表',
		rownumbers:true,style:'width:100%',
	    url:'Acl_getRoleMenuMapping.do?roleId=${roleId}',  
		idField:'id',
		treeField:'name',
	    columns:[[  
	        {field:'name',title:'菜单名称',width:180},  
	        {field:'url',title:'菜单链接',width:180},   
	        {field:'aclState',title:'操作',width:100,
	        	formatter: function(value,row,index){
	        		if(row.parentId == 0){
	        			return "";
	        		}
	        		var img = 'cancel';
	        		if(value=='1'){
	        			img = 'ok';
	        		}
					return "<span onclick='auth("+row.id+")'><img id='img"+row.id+"' src='common/images/"+img+".png' ><span>";
				}
	        }  
	    ]]
	});
});
//分配权限
function auth(id){
	$.ajax({
		url:"Acl_auth.do?roleId=${param.roleId}&acl.resourceId="+id,
		success:function(data){
			var img = (data==1?"ok":"cancel");
			$("#img"+id).attr("src","common/images/"+img+".png");
		}
});
}
</script>
</head>

<body style="width: 96%;height: 95%;padding: 5px">
	<table id="menuList"></table>
	<div id="addWin" class="easyui-window" title="添加菜单" style="width:400px;height:400px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
        <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" >
        </iframe>  
    	
    </div>    
</body>
</html>
