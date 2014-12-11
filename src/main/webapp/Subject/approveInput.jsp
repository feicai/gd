<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<style type="text/css">
	.td1{
		background-color: silver;
	}
</style>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"Subject_approveSubmit.do",  
	    onSubmit: function(){  
	        return $('#addForm').form("validate");  
	    },  
	    success:function(data){  
	        $.messager.alert("提示","审批成功！","info",function(){
	       		parent.reload("subjectList");
		        parent.closeWin();
	        });
	    }  
	});
	$("#froms").combobox("setValue","${subject.froms}");
	$("#workload").combobox("setValue","${subject.workload}");
	$("#diff").combobox("setValue","${subject.diff}");
	$("#type").combobox("setValue","${subject.type}");
});
function doSubmit(result){
	$("#result").val(result);
	$('#addForm').submit();
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:10px;">
		<h2>课题审核</h2>
		<hr>
			<form id="addForm"	action="Subject_approveSubmit.do" method="post">
				<input name="approve.duixiang" type="hidden" value="${subject.id }" >
				<input name="approve.catelog" type="hidden" value="subject" >
				<input name="approve.result" type="hidden" id="result">

				<table style="width: 100%">
					<tr>
						<td class="td1">选题名称</td>
						<td colspan="3">
						 ${subject.name }
						 </td>
					</tr>
					<tr>
						<td class="td1">课题介绍 </td>
						<td  colspan="3">
							<p>${subject.description }</p>
						</td>
					</tr>
					<tr>
						<td class="td1">课题要求</td>
						<td  colspan="3">
							<p>${subject.req }</p>
						</td>
					</tr>
					<tr>
						<td class="td1">审核意见</td>
						<td  colspan="3">
							<textarea name="approve.advice" rows="3" cols="30"></textarea>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				 href="javascript:void(0)" onclick="parent.closeWin();">取消</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				 href="javascript:void(0)" onclick="doSubmit(0);">不通过</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				 href="javascript:void(0)" onclick="doSubmit(1);">通过</a>
		</div>
	</div>    
</body>
</html>
