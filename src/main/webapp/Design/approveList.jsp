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
	    url:'Design_getDesignsByTeacher.do',  
		idField:'id',
		singleSelect:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15],
	    columns:[[  
	        {checkbox:true},  
	        {field:'studentName',title:'选题学生',width:100},  
	        {field:'studentCode',title:'学生学号',width:100},  
	        {field:'subjectName',title:'课题名称',width:130},  
	        {field:'progress',title:'完成进度',width:60},  
	        {field:'updateTime',title:'提交日期',width:160},    
	        {field:'comment',title:'存在问题',width:160}    
	    ]],
	    toolbar: [
		    {
				iconCls: 'icon-add',
				text:'审核',
				handler: function(){edit("Design_approve.do?id=","roleList")}
			},'-',{
				iconCls: 'icon-reload',
				text:'刷新',
				handler: function(){reload("roleList")}
			}
		]
	});
});

</script>
</head>

<body style="padding: 5px">
	<table id="roleList" ></table>
	<div id="addWin" class="easyui-window" title="毕业设计审核" style="width:650px;height:450px"  
            data-options="iconCls:'icon-save',modal:true,closed:true"> 
      <iframe id="addFrame" name="addFrame" width="100%" frameborder="0" scrolling="auto" height="98%" >
        </iframe>
    </div>  
 
</body>
</html>
