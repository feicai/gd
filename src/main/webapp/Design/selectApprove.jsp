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
		    url:"Design_doApprove.do",  
		    onSubmit: function(){  
		        return $('#addForm').form("validate");  
		    },  
		    success:function(data){  
		       $.messager.alert("提示","提交成功","info",function(){

		        });
		    }  
		});
	});
	function doSubmit(result) {
		$("#result").val(result);
		$("#addForm").submit();
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:10px;">
			<h2>毕业设计审核</h2>
			<form id="addForm" action="Subject_approveSubmit.do" method="post">
				<input name="id" type="hidden" value="${design.id }">
				<input name="type" type="hidden" value="select">
				<input name="approve.duixiang" type="hidden" value="${design.id }">
				<input name="approve.catelog" type="hidden" value="design">
				<input name="approve.result" type="hidden" id="result">
				<table style="width: 100%">
					<tr>
						<td class="td1">选题名称</td>
						<td colspan="3">${design.subject.name }</td>
					</tr>
					<tr>
						<td class="td1">选题学生</td>
						<td>【${design.user.code }】${design.user.name }</td>
					</tr>
					<tr>
						<td class="td1">审核意见</td>
						<td colspan="3"><textarea name="approve.advice" rows="3"
								cols="30"></textarea></td>
					</tr>

				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align:center;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="parent.closeWin();">取消</a> 
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				href="javascript:void(0)" onclick="doSubmit(0);">驳回申请</a> 
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="doSubmit(1);">审核通过</a>
		</div>
	</div>
</body>
</html>
