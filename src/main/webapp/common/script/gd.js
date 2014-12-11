//添加
function add(url){
	$('#addFrame').html('');
	$('#addFrame').attr("src",url);
	//$('#addWin').window('setTitle',"添加菜单");	
	$('#addWin').window('open');
	//$('#addWin').window('refresh', 'Menu_addInput.do');
}
//修改
function edit(url,id){
	var obj = getSelected(id);
	if(obj){
		$('#addFrame').html('');
		$('#addFrame').attr("src",url+obj.id);
		$('#addWin').window('setTitle',"修改");	
		$('#addWin').window('open');
	}
}
//删除方法
function del(url,id){
	var obj = getSelected(id);
	if(obj){
		$.messager.confirm('确认','确定要删除吗?',function(v){  
	        if(v){  
	            $.ajax({
				  url: url+obj.id,
				  type: 'POST',
				  cache: false,
				 // data: {'menu.id':obj.id},
				  dataType :'json',
				  success: function(data){
			        if(data.flag){
						$.messager.alert('提示',data.msg, 'info',function(r){
							reload(id);
			        	});
					}else{
						$.messager.alert('提示',data.msg, 'error');
					}
				  }
				});
	        }  
	     });
	  }
  }
function closeWin(winId){
	if(winId==null){
		winId="addWin";
	}
	$('#'+winId).window('close');
}

//重新加载数据
function reload(id){
	$('#'+id).datagrid('reload');
}
//取得数据
function getSelected(id){
	var rec = $('#'+id).datagrid('getSelected');
	if(rec){
		return rec;
	}else{
		$.messager.alert('提示',"请选择需要操作记录!", 'info');
		return null;
	}
}