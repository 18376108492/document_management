<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>搜索结果</title>
    <link rel="stylesheet" href="../base-component/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../assets/css/admin.css">
    
    <!--<script src="../base-component/jquery/jquery.min.js"></script>
    <script src="../assets/js/jquery-ui-1.10.4.min.js"></script>-->
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
		 <script type="text/javascript" src="../js/jquery-ui-1.10.4.min.js"></script>
  
    
</head>
<body style="overflow-y: auto">


<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">文档查询结果</strong> </div>
</div>

<!--<ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
    <li><a href="#" class="am-text-success"><span id="refsh" class="am-icon-btn am-icon-file-text"></span><br/>新增页面<br/>2300</a></li>
    <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>成交订单<br/>308</a></li>
    <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>昨日访问<br/>80082</a></li>
    <li><a href="#" class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>在线用户<br/>3000</a></li>
</ul>-->

<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-bd am-table-striped admin-content-table">
            <thead>
            <tr>
                <th>节点编号</th><th>节点名</th><th>节点路径</th><th>父节点</th><th>磁盘</th>
            </tr>
            <!--管理功能类似于window系统的垃圾桶，还原，清空，备份-->
            <!--记得加分页功能-->
            </thead>
            <tbody>
            <c:if test="${nodeList}==null">
                 <span>搜索结果为空</span>
            </c:if>
            <c:forEach items="${nodeList}" var="node" >
            <tr><td>${node.id}</td>
                <td>${node.name}</td>
                <td><a href="#">${node.path}</a></td>
                <td>${node.pId}</td>
                <td><a href="#">${node.diskName}</a></td>
                <td>
                    <div class="am-dropdown" data-am-dropdown>
                        <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                        <ul class="am-dropdown-content">
                            <li><a href="#">1. 编辑</a></li>
                            <li><a href="#">2. 下载</a></li>
                            <li><a href="#">3. 删除</a></li>
                        </ul>
                    </div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<!--<![endif]-->
<script src="../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../base-component/layer/layer.js"></script>
</body>
</html>