<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#addForm').form({  
	    url:"Subject_add.do",  
	    onSubmit: function(){  
	        return $('#addForm').form("validate");  
	    },  
	    success:function(data){  
	        $.messager.alert("提示","添加课题成功！","info",function(){
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
function doSubmit(v){
	$("#status").val(v);
	$('#addForm').submit();
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:10px;">
		<h2>课题填报</h2>
		<hr>
			<form id="addForm"	action="Subject_add.do" method="post">
				<c:if test="${not empty subject }">
					<input name="subject.id" type="hidden" value="${subject.id }" >
				</c:if>
				<input id="status" name="subject.status" type="hidden">
				<table style="width: 100%">
					<tr>
						<td class="td1">选题名称</td>
						<td colspan="3">
						<input name="subject.name" class="easyui-validatebox" size="45" required="required" type="text" value="${subject.name }"></td>
					</tr>
					<tr>
						<td class="td1">课题来源 </td>
						<td>
							<select id=froms class="easyui-combobox" name="subject.froms" data-options="panelHeight:'auto',required:true" style="width:120px;">  
						        <option></option>
								<option value="科研题目">科研题目</option>
								<option value="自拟题目">自拟题目</option> 
						    </select>
						</td>
						<td class="td1">题目类别</td>
						<td>
							<select id="type" class="easyui-combobox" name="subject.type" data-options="panelHeight:'auto',required:true" style="width:120px;">  
						        <option></option>
								<option value="理论研究">理论研究</option>
								<option value="应用研究">应用研究</option> 
						    </select>
						</td>
					</tr>
					<tr>
						<td class="td1">课题预计难度 </td>
						<td>
							<select id="diff" class="easyui-combobox" name="subject.diff" data-options="panelHeight:'auto',required:true" style="width:120px;">  
						        <option></option>
								<option value="难">难</option>
								<option value="一般">一般</option>
								<option value="易">易</option> 
						    </select>
						</td>
						<td class="td1">课题预计工作量</td>
						<td>
							<select id="workload" class="easyui-combobox" name="subject.workload" data-options="panelHeight:'auto',required:true" style="width:120px;">  
						        <option></option>
								<option value="大">大</option>
								<option value="适中">适中</option>
								<option value="小">小</option> 
						    </select>
						</td>
					</tr>
					<tr>
						<td class="td1">课题介绍 </td>
						<td  colspan="3">
							<textarea rows="3" cols="40"  name="subject.description"  class="easyui-validatebox" required="required">${subject.description }</textarea>
						</td>
					</tr>
					<tr>
						<td class="td1">课题要求</td>
						<td  colspan="3">
							<textarea rows="3" cols="40" name="subject.req" class="easyui-validatebox" required="required">${subject.req }</textarea>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				 href="javascript:void(0)" onclick="parent.closeWin();">取消</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				 href="javascript:void(0)" onclick="doSubmit(1)">暂存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
					onclick="doSubmit(2)">提交</a>
		</div>
	</div>    
</body>
</html>
