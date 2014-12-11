<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<style type="text/css">
.td1 {
	background-color: silver;
}
</style>
<script type="text/javascript">

	$(function() {
		$('#addForm').form({  
		    url:"Design_submit.do",  
		    onSubmit: function(){  
		        return $('#addForm').form("validate");  
		    },  
		    success:function(data){  
		       $.messager.alert("提示","提交成功","info",function(){

		        });
		    }  
		});
		$('#roleList').datagrid({ 
			style:'width:90%',
			rownumbers:true,
		    url:'MyFile_getFiles.do?file.duixiang=${design.id}&page=1&rows=1',  
			idField:'id',
			singleSelect:true,
			//pagination:true,
			//pageSize:5,
			//pageList:[5,10,15],
		    columns:[[  
		        {checkbox:true},  
		        {field:'id',title:'文件标识',width:60},  
		        {field:'name',title:'文件名称',width:180,formatter:function(value,data){
		        	return "<a href='MyFile_download.do?id="+data.id+"'>"+data.name+"."+data.suffix+"</a>"
		        }},  
		        {field:'size',title:'大小',width:80,formatter:function(value,data){
		        	if(value < 1024){
		        		return "小于1k";;
		        	}else{
		        		return Math.round(value/1024)+"k";
		        	}
		        }},  
		        {field:'createTime',title:'上传日期',width:160}    
		    ]]
		});
	});
	function doSubmit(state){
		$("#state").val(state);
		$('#addForm').submit();
	}
</script>
</head>
<body>
	<div id="p" class="easyui-panel" title="我的毕业设计管理"
		style="width:700px;height:550px;padding:10px;background:#fafafa; "
		data-options="iconCls:'icon-search',closable:true,  
                    collapsible:true,minimizable:true,maximizable:true">
        <form id="addForm" method="post" action="Design_submit.do">
       	<input type="hidden" name="id" value="${design.id }">
       	<input type="hidden" name="state" id="state">
		<table width="100%">
			<tr>
				<td class="td1">进度</td>
				<td colspan="3">
				<input id="progress" name="progress"
					class="easyui-numberspinner" style="width:100px;"
					required="required" value="${design.progress }"
					data-options="min:0,increment:10,max:100,editable:true"> <strong>%</strong>
				</td>
			</tr>
			<tr>
				<td class="td1" colspan="4">我的毕业论文 
					<a class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" href="javascript:void(0)"
						onclick="add('MyFile_uploadPage.do?catelog=design&duixiang=${design.id}')">上传</a>
					<a class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
						onclick="del('MyFile_delete.do?file.id=','roleList')">删除</a>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="roleList"></table></td>
			</tr>
			<tr>
				<td class="td1">存在的问题</td>
				<td colspan="3"><textarea name="comment" rows="3" cols="30">${design.comment}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><a class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" href="javascript:void(0)"
					onclick="doSubmit(1)">暂存</a> <a class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" href="javascript:void(0)"
					onclick="doSubmit(2)">提交</a></td>
			</tr>
		</table>
		 </form>
	</div>
	<div id="addWin" class="easyui-window" title="上传文件"
		style="width:500px;height:230px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		<iframe id="addFrame" name="addFrame" width="100%" frameborder="0"
			scrolling="auto" height="98%"> </iframe>
	</div>
</body>
</html>
