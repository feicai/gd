<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>毕业设计管理系统</title>

<%@ include file="common/common.jsp"%>
<style type="text/css">
	.menuClass{
		border: 1px silver outset;
		font-size: 12px;
		color:blue;
		font-family: 'inherit';
		height: 25px;
		padding-left: 18px;
		padding-top:5px;
		background:white;
		cursor: pointer;
	}
	.menuClass:HOVER {
		border: 1px #b3d9ff inset;
		background:#ebebeb;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	$(function(){
		$('#myTab').tabs({  
		    border:true, 
		    fit:true,
		    onSelect:function(title){  
		        //alert(title+' is selected');  
		    }  
		});
	});
	function addTab(title,url){
		var flag = $('#myTab').tabs('exists',title);
		if(flag){
			$('#myTab').tabs('select',title);
		}else{
			$('#myTab').tabs('add',{
				title: title,
				selected: true,
				closable: true,
				content:'<iframe width="100%" frameborder=0 scrolling=auto height="98%" src="'+url+'" ></iframe>'
			});
		}
	}
</script>

</head>

<body class="easyui-layout">
	<!-- 北 -->
	<div data-options="region:'north',split:true"
		style="height:100px;background: url('common/images/bg_top2.png') ;background-repeat: repeat-x;margin: 0px;padding: 0px">
		<div style="float: left;height:90px;width:500px;background: url('common/images/title.jpg')"></div>

		<div style="float: right;padding-top: 60px;padding-right: 10px">
			<a id="btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true">
			<strong>【${LOGIN_USER.code }】${LOGIN_USER.name }</strong>
			</a> 
			<a id="btn" href="logout.do" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">
			<strong>退出系统</strong>
			</a> 
		</div>
	
	</div>

	<!-- 南 -->
	<%--<div data-options="region:'south',title:'South Title',split:true"
		style="height:100px;"></div>
	--%><!-- 东 -->
	<%--<div
		data-options="region:'east',iconCls:'icon-reload',title:'East',split:true"
		style="width:100px;"></div>
	--%><!-- 西 -->
	<div data-options="region:'west',title:'目录',split:true"
		style="width:200px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<c:forEach items="${menus }" var="m">
				<c:if test="${m.flag }">
					<div title="${m.name }" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:0px;">
						<c:forEach items="${m.subMenu}" var="sm">
							<c:if test="${m.flag }">
								<div class="menuClass" onclick="addTab('${sm.name }','${sm.url }')">${sm.name }</div>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</c:forEach>
			<%--
			<div title="系统管理" data-options="iconCls:'icon-save',selected:true"
				style="overflow:auto;padding:0px;">
				<div class="menuClass" onclick="addTab('用户管理','User_list.do')">用户管理</div>
				<div class="menuClass"	onclick="addTab('菜单管理','Menu_list.do')">菜单管理</div>
				<div class="menuClass"	onclick="addTab('角色管理','Role_list.do')">角色管理</div>
				<div class="menuClass">权限管理</div>
			</div>
			<div title="流程管理"
				data-options="iconCls:'icon-reload'"
				style="padding:0px;">
				<div class="menuClass">用户管理</div>
				<div class="menuClass">角色管理</div>
				<div class="menuClass">权限管理</div>
			</div>
			<div title="信息管理">
				<div class="menuClass">用户管理</div>
				<div class="menuClass">角色管理</div>
				<div class="menuClass">权限管理</div>
			</div>
			--%>
		</div>
	</div>
	<!-- 中 -->
	<div data-options="region:'center'"
		style="padding:0px;background:#eee;">
			<div id="myTab" class="easyui-tabs" style="width:500px;height:250px;">  
			    <div title="主页" data-options="iconCls:'icon-reload',closable:false"
			     style="padding:5px;">  
			        <iframe width="100%" frameborder="0" scrolling="auto" height="98%" src="Process_taskList.do" >
			        	主页
			        </iframe>
			    </div>  
			</div> 
	</div>
	
</body>
</html>
