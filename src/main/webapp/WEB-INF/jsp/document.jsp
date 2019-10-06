<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>磁盘主页</title>
    <link rel="stylesheet" href="../../base-component/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../../assets/css/admin.css">
    <link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-ui-1.10.4.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.ztree.all.js"></script>

</head>
<body style="overflow-y: auto">
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">磁盘主页</strong> /
        <small>本地磁盘</small>
    </div>
</div>


<div class="am-g">
    <div class="am-u-md-12">
        <div class="am-panel am-panel-default">
            <div class="am-panel-hd am-cf">资源盘 <span class="am-icon-chevron-down am-fr"></span>
                <span><button class="button" id="load_button">初始磁盘</button></span>
            </div>


            <!--树形节点-->
            <div class="description am-panel am-panel-default">
                文档系统使用说明：如果不显示数据或数据出错，请点击初始化按钮(初始化数据的时间较长，所以请勿乱点击)。
            </div>


            <!--树形节点-->
            <div class="am-u-md-8">
                <div class="content_wrap ">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <!--操作日志-->
            <div class="am-u-md-4">
                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="callbackTrigger" checked/> 拖拽节点时自动展开父节点<br/>
                    操作日志:<br/>
                    <ul id="log" class="log" style="background-color: silver"></ul>
            </div>
        </div>
    </div>


</div>
<!--<![endif]-->
<script src="../../js/ztree_document.js"></script>
<script src="../../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../../base-component/layer/layer.js"></script>
<script>
    $('#refsh').on('click', function () {
        alert(window.location.href);
        window.location.href = "http://blog.csdn.net/";
    });

    //点击初始化磁盘数据
    $("#load_button").on('click', function () {
        alert("磁盘初始化时间较长，是否选择等待.");
        $.ajax({
            type: "GET",
            url: "/disk/initDisk",
            dataType: "json",
            async: true,
            data: {},
            success: function (data) {
                var msg = data.msg;
                alert(msg);
            }

        })
    });


</script>
</body>
</html>