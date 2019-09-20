<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>个人文档管理系统</title>
    <meta name="description" content="快乐管理资源">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--<link rel="icon" type="image/png" href="{{assets}}i/favicon.png">-->
    <!--<link rel="apple-touch-icon-precomposed" href="{{assets}}i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />-->
    <link rel="stylesheet" href="base-component/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/admin.css">
     
       
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong></strong> <small>&nbsp;&nbsp;个人文档管理</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 邮箱管理员 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content" >
                	  <li><a href="/admin/login"><span class="am-icon-user"></span> 登入</a></li>
                    <li><a href="/admin/data"><span class="am-icon-user"></span> 资料</a></li>
                    <li><a href="/admin/setUp"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="/admin/out"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list" id="admin-list-config">
                <!-- 左侧列表配置 -->
            </ul>
            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 便利贴</p>
                    <p>今天下午两点面试，面试地点为南山科技园</p>
                </div>
            </div>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-tag"></span> wiki</p>
                    <p>Welcome to the Document Management</p>
                </div>
            </div>
        </div>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="admin-tab-body-index" style="height: 100%;position: relative;">
                <ul class="am-tabs-nav am-nav am-nav-tabs admin-tab-show">

                    <div class="admin-tab-left am-icon-chevron-left"></div>
                    <div class="admin-tab-right am-icon-chevron-right"></div>
                    <div class="admin-tab-right admin-tab-right-close-all am-icon-trash-o" style="width: 40px"></div>
                    <!-- 分界线 -->
                    <span class="admin-tab-min-hr"></span>


                    <li class="am-active admin-main-tab">
                        <span class="am-tab-refresh am-icon-refresh" iframe-url="/disk/main" iframe-uuid="uuid-main"></span>
                        <a href="javascript: void(0)">首页</a>
                    </li>
                </ul>

                <div class="am-tabs-bd admin-tab-show-body">
                    <div class="am-tab-panel am-active" style="height: 100%">
                        <iframe src="/disk/main" class="admin-iframe-html" iframe-uuid="uuid-main" style="width: 100%;height: 100%;"></iframe>
                    </div>
                </div>
            </div>
        </div>

        <footer class="admin-content-footer">
            <p class="am-padding-left admin-content-footer-p"></p>
        </footer>
    </div>
    <!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="base-component/amazeui/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<script></script>
<script src="../../js/jquery.min.js"></script>
<script src="../../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../../base-component/layer/layer.js"></script>
<script src="../../assets/js/config.js"></script>
<script src="../../assets/js/app.js"></script>
<script>

</script>
</body>
</html>