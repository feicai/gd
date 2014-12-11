<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	
	<c:if test="${not empty design}">
		window.location="Design_viewSubject.do?id=${design.id}";
		return;
	</c:if>
	
	var s = {"0":"作废","1":"暂存","2":"提交待审","3":"审核通过","4":"已归档","5":"已选择"};
	$('#subjectList').datagrid({ 
		title:'选题列表',
		style:'width:100%',
		rownumbers:true,
	    url:'Subject_getSubjects.do?subject.status=4',  
		idField:'id',
		treeField:'name',
		singleSelect:true,
		pagination:true,
		fitColumns:true,
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
					text:'选择课题',
					handler: function(){select();}
				},'-',{
					iconCls: 'icon-reload',
					text:'刷新',
					handler: function(){reload("subjectList")}
				}
			]
	    	
	});
});
function select(){
	var obj = getSelected("subjectList");
	if(obj){
		$.messager.confirm('确认','你确定要选择课题《'+obj.name+'》吗?',function(flag){
			if(flag){
	            $.ajax({
					  url: "Design_add.do?design.subject.id="+obj.id,
					  type: 'POST',
					  cache: false,
					 // data: {'menu.id':obj.id},
					  dataType :'json',
					  success: function(data){
				        if(data.flag){
							$.messager.alert('提示',data.msg, 'info',function(r){
								parent.reload("subjectList");
						        parent.closeWin();
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
</script>
</head>

<body style="padding: 5px">
	<table id="subjectList" ></table>
	<div id="addWin" class="easyui-window" title="毕业设计选题审核" style="width:500px;height:300px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" ></iframe>
    </div>
</body>
</html>
