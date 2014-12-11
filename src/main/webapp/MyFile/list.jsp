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
		title:'附件列表',
		style:'width:90%',
		rownumbers:true,
	    url:'MyFile_getFiles.do',  
		idField:'id',
		singleSelect:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15],
	    columns:[[  
	        {checkbox:true},  
	        {field:'id',title:'文件标识',width:100},  
	        {field:'name',title:'文件名称',width:180,formatter:function(value,data){
	        	return "<a href='MyFile_download.do?id="+data.id+"'>"+data.name+"."+data.suffix+"</a>"
	        }},  
	        {field:'type',title:'文件类型',width:100},  
	        {field:'size',title:'大小',width:100,formatter:function(value,data){
	        	if(value < 1024){
	        		return "小于1k";;
	        	}else{
	        		return Math.round(value/1024)+"k";
	        	}
	        }},  
	        {field:'createTime',title:'上传日期',width:160}    
	    ]],
	    toolbar: [
		    {
				iconCls: 'icon-add',
				text:'上传',
				handler: function(){add("MyFile_uploadPage.do")}
			},'-',{
				iconCls: 'icon-edit',
				text:'重命名',
				handler: function(){edit("MyFile_renamePage.do?id=","roleList")}
			},'-',{
				iconCls: 'icon-reload',
				text:'刷新',
				handler: function(){reload("roleList")}
			},'-',{
				iconCls: 'icon-cancel',
				text:'删除',
				handler: function(){del("MyFile_delete.do?file.id=","roleList")}
			}
		]
	});
});

</script>
</head>

<body style="padding: 5px">
	<table id="roleList" ></table>
	<div id="addWin" class="easyui-window" title="上传文件" style="width:500px;height:230px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" >
        </iframe>
    </div>  
 
</body>
</html>
