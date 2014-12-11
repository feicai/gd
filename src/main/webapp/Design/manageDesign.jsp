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
function option(title){
	if(title=="论文管理"){
		$('#design').attr("src","Design_inputForm.do?id=${design.id}");
	}else if(title=="指导教师审核信息"){
		$('#approve').attr("src","Design_approveInfoList.do?id=${design.id}");
	}
}
</script>
</head>
<body style="">
<div id="tt" class="easyui-tabs" data-options="tabPosition:'left',fit:true,onSelect:function(title){option(title)}" style="">  
	<div title="我的课题信息" style="padding:5px">  
		<iframe name="info" src="Design_viewSubject.do?id=${design.id}" width="100%" frameborder="0" scrolling="auto" height="98%" >
        	</iframe>
	</div>
	<div title="论文管理" style="padding:5px" data-opions="">  
		<iframe id="design" name="info" width="100%" frameborder="0" scrolling="auto" height="98%" >
        	</iframe>
	</div>
	<div title="指导教师审核信息" >  
	<iframe id="approve" name="info"  width="100%" frameborder="0" scrolling="auto" height="98%" >
        	</iframe>
	</div>
</div>
	
</body>
</html>
