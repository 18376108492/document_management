<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">磁盘主页</strong> / <small>本地磁盘</small></div>
</div>



<div class="am-g">
    <div class="am-u-md-12">
        <div class="am-panel am-panel-default">
            <div class="am-panel-hd am-cf" >资源盘<span class="am-icon-chevron-down am-fr" ></span></div>
           
               
    <!--树形节点-->
    <div class="description am-panel am-panel-default">
    文档系统使用说明：
  </div>


  <p id="sampleButtons">
  </p>

      <!--树形节点-->
      <div class="am-u-md-8">
          <div class="content_wrap">
              <ul id="treeDemo" class="ztree"></ul>
          </div>
      </div>
  <div>主节点: <span id="echoActive">-</span></div>
  <div>已选中节点列表: <span id="echoSelected">-</span></div>
  <div>当前选中节点: <span id="echoFocused">-</span></div>



        </div>
    </div>


    
</div>
<!--<![endif]-->
<script src="../../js/ztree_document.js"></script>
<script src="../../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../../base-component/layer/layer.js"></script>
<script>
    $('#refsh').on('click',function(){
        alert(window.location.href);
        window.location.href="http://blog.csdn.net/";
    });
</script>
</body>
</html>