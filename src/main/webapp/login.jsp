<%--
  用户登录页面
  User: Yangcl
  Date: 13-10-29
  Time: 上午10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String base = request.getContextPath();
%>
<html>
<head>
    <title>用户登录</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="<%=base%>/extjs/resources/css/ext-all.css">

    <link rel="stylesheet" type="text/css" href="<%=base%>/appUtils/checkCode.css">

    <script type="text/javascript">
        var AppBase = '<%=base%>';
    </script>

    <script type="text/javascript" src="<%=base%>/extjs/ext-all-debug-w-comments.js"></script>
    <script type="text/javascript" src="<%=base%>/extjs/locale/ext-lang-zh_CN.js"></script>

    <script type="text/javascript" src="<%=base%>/app/util/md5.js"></script>

    <script type="text/javascript" src="<%=base%>/app/util/constant.js"></script>

    <script type="text/javascript" src="<%=base%>/app/login.js"></script>
</head>


<body id="login-body">


<script type="text/javascript">
//    指定Ext Framework 类加载路径
    Ext.Loader.setConfig
    ({
        enabled:true,
        paths:{
            'Hongbo':'app'
        }
    });

    Ext.onReady(function ()
    {
        Ext.Loader.require(['Hongbo.Login']);
        Ext.create('Hongbo.Login');
    });

</script>


<div id="banner">采购单demo</div>
<div id="banner_version">0.0.1</div>


</body>
</html>