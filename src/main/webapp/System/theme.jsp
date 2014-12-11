<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp"%>
<style type="text/css">
	#p .d1{
		float: left;
		padding:5px;
		margin:10px;
		border: 1px blue solid;
	}
</style>
<script type="text/javascript">
	$(function() {
		$(".d1").click(function(){
			var id = $(this).attr("id");
			$.messager.confirm('确认',"你确定要选择主题“"+id+"”吗?",function(flag){
				if(flag){
		            $.ajax({
						  url: "System_setTheme.do?theme="+id,
						  type: 'POST',
						  cache: false,
						  dataType :'json',
						  success: function(data){
					        parent.window.location="main.jsp";
						  }
						});
				}
			});
		});
	});
</script>
</head>
<body style="width:100%">
	    <div id="p" class="easyui-panel" title="我的主题"   
            style="padding:10px;background:#fafafa;"  
            data-options="iconCls:'icon-edit',collapsible:true,fit:true">  
			<c:forEach items="${ts }" var="t">
				<div class="d1" id="${t }" title="点击更换为“${t }”主题">
					<div> 
						<img alt="${t }" width="450" height="170" src="common/images/theme/${t }.jpg">
					</div>
					<div align="center"><strong>${t }</strong></div>
				
				</div>
			
			</c:forEach>
			
			
		</div>
</body>
</html>
