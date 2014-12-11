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
	    url:'Menu_getMenus.do',  
		idField:'id',
		treeField:'name',
	    columns:[[  
	        {field:'name',title:'菜单名称',width:180},  
	        {field:'url',title:'菜单链接',width:180},  
	        {field:'orderNumber',title:'排序号',width:100,align:'right'},  
	        {field:'parentId',title:'父ID',width:100,align:'right'}  
	    ]],
	    onDblClickRow:function(){edit()},
	    toolbar: [
		    {
				iconCls: 'icon-add',
				text:'添加菜单',
				handler: function(){add()}
			},'-',{
				iconCls: 'icon-reload',
				text:'刷新',
				handler: function(){reload()}
			},'-',{
				iconCls: 'icon-edit',
				text:'修改',
				handler: function(){edit()}
			},'-',{
				iconCls: 'icon-cancel',
				text:'删除',
				handler: function(){del()}
			},'-',{
				iconCls: 'icon-help',
				handler: function(){alert('help')}
		}]
	});
	
});
//添加
function add(){
	$('#addFrame').html('');
	var url = 'Menu_addInput.do';
	$('#addFrame').attr("src",url);
	$('#addWin').window('setTitle',"添加菜单");	
	$('#addWin').window('open');
	//$('#addWin').window('refresh', 'Menu_addInput.do');
}
//修改
function edit(){
	var obj = getSelected();
	if(obj){
		$('#addFrame').html('');
		var url = 'Menu_addInput.do?menu.id='+obj.id;
		$('#addFrame').attr("src",url);
		$('#addWin').window('setTitle',"修改菜单");	
		$('#addWin').window('open');
	}
}
//删除方法
function del(){
	var obj = getSelected();
	if(obj){
		$.messager.confirm('确认','确定要删除吗?',function(v){  
	        if(v){  
	            $.ajax({
				  url: "Menu_delete.do",
				  type: 'POST',
				  cache: false,
				  data: {'menu.id':obj.id},
				  dataType :'json',
				  success: function(data){
			        if(data.flag){
						$.messager.alert('提示',data.msg, 'info',function(r){
							reload2(obj.parentId);
			        	});
					}else{
						$.messager.alert('提示',data.msg, 'error');
					}
				  }
				});
	        }  
	     });
	  }
  }
function closeWin(){
	$('#addWin').window('close');
}

//重新加载数据
function reload(){
	$('#menuList').treegrid('reload');
}
//取得数据
function getSelected(){
	var rec = $('#menuList').treegrid('getSelected');
	if(rec){
		return rec;
	}else{
		$.messager.alert('提示',"请选择需要操作记录!", 'info');
		return null;
	}
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
