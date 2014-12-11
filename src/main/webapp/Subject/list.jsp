<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	//状态
	var s = {"0":"作废","1":"暂存","2":"提交待审","3":"审核通过","4":"已归档","5":"已选择"};
	
	$('#subjectList').datagrid({ 
		title:'用户列表',
		style:'width:100%',
		rownumbers:true,
	    url:'Subject_getSubjects.do',  
		idField:'id',
		singleSelect:true,
		pagination:true,
		fitColumns:true,
		onDblClickRow:function(){edit("Subject_addInput.do?id=","subjectList")},
	    columns:[[  
	        {checkbox:true},  
	        {field:'id',title:'编号',width:50,hidden:true},  
	        {field:'name',title:'课题名称',width:180},  
	        {field:'type',title:'类型',width:80},        
	        {field:'froms',title:'课题来源',width:80},        
	        {field:'workload',title:'工作量',width:80},        
	        {field:'diff',title:'难度',width:80},        
	        {field:'description',title:'课题描述',width:150},        
	        {field:'req',title:'课题要求',width:150},        
	        {field:'status',title:'课题状态',width:80,formatter:function(value){
	        	return s[value];
	        }}        
	    ]],
	    toolbar: [
	  		    {
					iconCls: 'icon-add',
					text:'添加选题',
					handler: function(){add("Subject_addInput.do")}
				},'-',{
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){reload("subjectList")}
				},'-',{
					iconCls: 'icon-edit',
					text:'修改',
					handler: function(){edit("Subject_addInput.do?id=","subjectList")}
				},'-',{
					iconCls: 'icon-cancel',
					text:'删除',
					handler: function(){del("Subject_delete.do?user.id=","subjectList")}
				}
			]
	    	
	});
});
</script>
</head>

<body style="padding: 5px">
	<table id="subjectList" ></table>
	<div id="addWin" class="easyui-window" title="添加毕业设计选题" style="width:600px;height:500px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" ></iframe>
    </div>
</body>
</html>
