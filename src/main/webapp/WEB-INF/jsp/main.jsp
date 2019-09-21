<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="../../base-component/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../../assets/css/admin.css">
    <!--<link rel="stylesheet" href="../css/demo.css" type="text/css">-->
	<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!--<script src="../base-component/jquery/jquery.min.js"></script>
    <script src="../assets/js/jquery-ui-1.10.4.min.js"></script>-->
      <script type="text/javascript" src="../../js/jquery-1.11.3.min.js"></script>
		 <script type="text/javascript" src="../../js/jquery-ui-1.10.4.min.js"></script>
         <script type="text/javascript" src="../../js/jquery.ztree.all.js"></script>
</head>
<body style="overflow-y: auto">
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>本地磁盘</small> <small><button onclick="javascript:loadOnclick()">初始磁盘</button></small></div>
</div>

<ul    class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
    <p id="load-disk"></p>>
</ul>


<div class="am-g">
    <div class="am-u-md-6">
        <div class="am-panel am-panel-default">
            <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">资源盘<span class="am-icon-chevron-down am-fr" ></span></div>
            <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
                <ul class="am-list admin-content-file">
                    <li>
                        <strong><span class="am-icon-upload"></span> Kong-cetian.Mp3</strong>
                        <p>3.3 of 5MB - 5 mins - 1MB/Sec</p>
                        <div class="am-progress am-progress-striped am-progress-sm am-active">
                            <div class="am-progress-bar am-progress-bar-success" style="width: 82%">82%</div>
                        </div>
                    </li>
                    <li>
                        <strong><span class="am-icon-check"></span> 好人-cetian.Mp3</strong>
                        <p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
                    </li>
                    <li>
                        <strong><span class="am-icon-check"></span> 其实都没有.Mp3</strong>
                        <p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
                    </li>
                </ul>
            </div>
        </div>
   
    </div>

    <div class="am-u-md-6">
        <div class="am-panel am-panel-default">
            <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-4'}">任务 task<span class="am-icon-chevron-down am-fr" ></span></div>
            <div id="collapse-panel-4" class="am-panel-bd am-collapse am-in">
                <ul class="am-list admin-content-task">
                    <li>
                        <div class="admin-task-meta"> Posted on 25/1/2120 by John Clark</div>
                        <div class="admin-task-bd">
                            The starting place for exploring business management; helping new managers get started and experienced managers get better.
                        </div>
                        <div class="am-cf">
                            <div class="am-btn-toolbar am-fl">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-check"></span></button>
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-pencil"></span></button>
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-times"></span></button>
                                </div>
                            </div>
                            <div class="am-fr">
                                <button type="button" class="am-btn am-btn-default am-btn-xs">删除</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="admin-task-meta"> Posted on 25/1/2120 by 呵呵呵</div>
                        <div class="admin-task-bd">
                            基兰和狗熊出现在不同阵营时。基兰会获得BUFF，“装甲熊憎恨者”。狗熊会获得BUFF，“时光老人憎恨者”。
                        </div>
                        <div class="am-cf">
                            <div class="am-btn-toolbar am-fl">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-check"></span></button>
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-pencil"></span></button>
                                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-times"></span></button>
                                </div>
                            </div>
                            <div class="am-fr">
                                <button type="button" class="am-btn am-btn-default am-btn-xs">删除</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

       
    </div>
    
    <!--树形节点-->
     <div class="am-u-md-8">
    	<div class="content_wrap">
				<ul id="treeDemo" class="ztree"></ul>
    </div>
    </div>
<!--<![endif]-->
 <script src="../../js/load_main.js" ></script>
<script src="../../js/ztree_document.js"></script>
<script src="../../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../../base-component/layer/layer.js"></script>
  <!--<script src="../js/ztree_document.js"></script>-->
<script>
    $('#refsh').on('click',function(){
        alert(window.location.href);
        window.location.href="http://blog.csdn.net/";
    });


</script>
</body>
</html>