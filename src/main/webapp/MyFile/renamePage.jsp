<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"MyFile_update.do",  
	    onSubmit: function(){  
	        return $('#addForm').form("validate");  
	    },  
	    success:function(data){  
		       $.messager.alert("提示","文件重命名成功","info",function(){
		       		parent.reload("roleList");
			        parent.closeWin();
		        });
	    	
	    }  
	});
	
});
function doSubmit(){
	$('#addForm').submit();
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:20px;">
			<form id="addForm"	action="MyFile_update.do" method="post">
			<input type="hidden" name="id" value="${file.id }">
			<h2>新文件名：</h2>
				<input type="text" name="name" class="easyui-validatebox" size="25" required="required">
				.${file.suffix }
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
					onclick="doSubmit()">提交</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				 href="javascript:void(0)" onclick="parent.closeWin();">取消</a>
		</div>
	</div>    
</body>
</html>
