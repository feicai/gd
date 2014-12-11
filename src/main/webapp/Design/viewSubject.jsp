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

	});
</script>
</head>
<body>
	    <div id="p" class="easyui-panel" title="我的课题"   
            style="width:600px;height:450px;padding:10px;background:#fafafa;"  
            data-options="iconCls:'icon-search',closable:true,  
                    collapsible:true,minimizable:true,maximizable:true">  
			<h2>您已经选择课题，正在审核中。。。。</h2>
			<hr>
			<table style="width: 100%">
				<tr>
					<td class="td1">选题名称</td>
					<td colspan="3">${design.subject.name }</td>
				</tr>
				<tr>
					<td class="td1">课题来源</td>
					<td>${design.subject.froms }</td>
					<td class="td1">题目类别</td>
					<td>${design.subject.type }</td>
				</tr>
				<tr>
					<td class="td1">课题预计难度</td>
					<td>${design.subject.diff }</td>
					<td class="td1">课题预计工作量</td>
					<td>${design.subject.workload }</td>
				</tr>
				<tr>
					<td class="td1">课题介绍</td>
					<td colspan="3">
						<p>${design.subject.description }</p></td>
				</tr>
				<tr>
					<td class="td1">课题要求</td>
					<td colspan="3">
						<p>${design.subject.req }</p></td>
				</tr>
				<tr>
					<td class="td1">审核意见</td>
					<td colspan="3">暂无</td>
				</tr>

			</table>
		</div>
</body>
</html>
