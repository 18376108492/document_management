<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>备份文档</title>
    <link rel="stylesheet" href="../base-component/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../assets/css/admin.css">
    
    <!--<script src="../base-component/jquery/jquery.min.js"></script>
    <script src="../assets/js/jquery-ui-1.10.4.min.js"></script>-->
    
         <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
		 <script type="text/javascript" src="../js/jquery-ui-1.10.4.min.js"></script>

  
    
</head>
<body style="overflow-y: auto">


<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">备份文档记录</strong> </div>
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
                <th>编号</th><th>文件名</th><th>操作时间</th><th>操作人</th><th>管理</th>
            </tr>
            <!--管理功能类似于window系统的垃圾桶，还原，清空，备份-->
            <!--记得加分页功能-->
            </thead>
            <tbody>
            <tr><td>1</td><td>John Clark</td><td><a href="#">Business management</a></td> <td><span class="am-badge am-badge-success">+20</span></td>
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
            <tr><td>2</td><td>风清扬</td><td><a href="#">公司LOGO设计</a> </td><td><span class="am-badge am-badge-danger">+2</span></td>
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
            <tr><td>3</td><td>詹姆斯</td><td><a href="#">开发一款业务数据软件</a></td><td><span class="am-badge am-badge-warning">+10</span></td>
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
            <tr><td>4</td><td>云适配</td><td><a href="#">适配所有网站</a></td><td><span class="am-badge am-badge-secondary">+50</span></td>
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

            <tr>
                <td>5</td><td>呵呵呵</td>
                <td><a href="#">基兰会获得BUFF</a></td>
                <td><span class="am-badge">+22</span></td>
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
            </tbody>
        </table>
    </div>
</div>



<!--<![endif]-->
<script src="../base-component/amazeui/js/amazeui.min.js"></script>
<script src="../base-component/layer/layer.js"></script>
<script>
    $('#refsh').on('click',function(){
        alert(window.location.href);
        window.location.href="http://blog.csdn.net/";
    });
</script>
</body>
</html>