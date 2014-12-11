<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>MENU</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function() {
	$('#menuList').datagrid({ 
		title:'待办任务',
		rownumbers:true,
		style:'width:100%',
	    url:'Process_taskListData.do',  
		idField:'id',
		treeField:'name',
	    columns:[[  
	        {field:'id',title:'任务Id',width:100},  
	        {field:'name',title:'任务名称',width:180},  
	        {field:'desc',title:'描述',width:200},  
	        {field:'formKey',title:'操作',width:150,
	        	formatter:function(value,data){
	        		var returnValue;
	        		if(data.type == "candidate"){
	        			returnValue = "<button onclick=\"claim('"+data.id+"')\">签收</button>"
	        		}else{
		        		returnValue = "<button onclick=\"add('"+value+"?id="+data.businesskey+"')\">办理</button>"
	        		}
	        		returnValue = returnValue + 
	        		"<button onclick=\"add('Process_viewProcessImage.do?processDefinitionId="+data.processDefinitionId+
	        				"&processInstanceId="+data.processInstanceId+"')\">"
	        		+"查看流程图</button>";
	        		return returnValue;
	        	}
	        }  
	    ]]
	});
	
});
function claim(taskId){
	$.ajax({
		   type: "POST",
		   url: "Process_claim.do",
		   data: "taskId="+taskId,
		   success: function(msg){
			 $.messager.alert('提示',"签收成功！", 'info',function(){
			     reload("menuList");
			 });
		   }
		});
}
</script>
</head>

<body style="width: 96%;height: 95%;padding: 5px">
	<table id="menuList"></table>
	<div id="addWin" class="easyui-window" title="任务处理" style="top:10px;width:700px;height:480px"  
            data-options="iconCls:'icon-save',modal:true,closed:true,onClose:function(){reload('menuList')}"> 
        <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" >
        </iframe>  
    	
    </div>    
</body>
</html>
