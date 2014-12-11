<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//default
//cupertino
//black
//bootstrap
//session.setAttribute("theme", "bootstrap");
%>

<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="common/style/gd.css" />
<link rel="stylesheet" type="text/css" href="common/plugins/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="common/plugins/easyui/themes/${theme }/easyui.css" />


<script type="text/javascript" src="common/plugins/jquery.min.js"></script>
<script type="text/javascript" src="common/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="common/plugins/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="common/script/gd.js"></script>