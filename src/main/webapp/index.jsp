<%--
  Created by IntelliJ IDEA.
  User: aliuselly
  Date: 2020/10/29
  Time: 下午4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>同学录</title>

<%--
三种方式
<link rel="icon" href="/***/my.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/***/my.ico" type="image/x-icon" />
<link rel="bookmark" href="/***/my.ico" type="image/x-icon" />
--%>
<%--    这个是修改图标的，不过呢，要是针对每个页面的修改的，有一个好的方法，不过不适合多项目的
        就是去到，tomcat\webapps\ROOT\an
        里面直接替换这个 favicon.ico 文件即可，不过，现在这个 tomcat 的，我删除了而已
--%>
    <link rel="shortcut icon" href="image/h7.ico">

<%--    可以看下这个--%>
<%--    https://www.cnblogs.com/sllzhj/p/7000719.html--%>

<%--    禁止游览器从本地计算机的缓存中访问页面内容--%>
    <meta http-equiv="Pragma" content="no-cache">
<%--    控制 HTTP 缓存--%>
    <meta http-equiv="Cache-Control" content="no-cache">
<%--    设置网页的到期时间--%>
    <meta http-equiv="Expires" content="0">
<%--
注意，通常上面两个是一起设置的，是关闭游览器缓存的
虽然上面三个HTTP响应消息头字段都可以关闭浏览器缓存。
但并不是所有的浏览器都支持这三个响应消息头字段，
因此，最好同时使用上面这三个响应消息头字段来关闭浏览器的缓存。
--%>

<%--    网页关键字，关键字：给搜索引擎用的--%>
    <meta http-equiv="keywords" content="alumni, jquery, easyui, ssm, mysql">
<%--    网页内容，即网页描述--%>
    <meta http-equiv="description" content="A Simpe Alumni">

<%--    easyui-js--%>
    <script type="text/javascript" src="static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<%--    easyui-css--%>
    <link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
    <link id="themeLink" rel="stylesheet" type="text/css" href="static/easyui/themes/metro/easyui.css">
</head>
<body class="easyui-layout">

<%--    页面顶部--%>
    <div data-options="region:'north',split:true" style="height:30px;">
<%--        切换页面主题功能--%>
        <div id="themesDiv" style="position: absolute; right: 20px; top: 1px;">
            <a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#Themesmeus',iconCls:'icon-reload'">
                切换页面主题
            </a>
            <div id="Themesmeus" style="width: 150px;">
                <div>metro</div>
                <div>material</div>
                <div>gray</div>
                <div>black</div>
                <div>bootstrap</div>
                <div>default</div>
            </div>
        </div>
    </div>

<%--    系统菜单--%>
    <div data-options="region:'west',title:'系统菜单',split:true" style="width: 200px;">
        <div id="aa" class="easyui-accordion" style="width: 193px;" data-options="border:0,multiple:true">
            <div title="备忘录" data-options="iconCls:'icon-edit',selected:true" style="padding: 10px;">
                <ul>
                    <li>
                        <a style="color: #8A2BE2" href="javascript:void(0)" pageUrl="friendInfo.jsp">好友列表</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

<%--    页面中间：项目介绍页面--%>
    <div data-options="region:'center'" style="padding: 5px;">
        <div id="tt" class="easyui-tabs" style="width: 500px; height: 250px;" data-options="fit:true">
            <div title="欢迎页面" style="padding: 20px; overflow: hidden; color: #8A2BE2">
                <p style="font-size: 25px; line-height: 30px; height: 30px;">欢迎使用同学录</p>
                <p>编写员：aliuselly</p>
                <p>联系方式-Email：aliuselly@gmail.com</p>
                <p>联系方式-QQ：1574626055</p>
                <p>编写周期：2020/10/29 —— 2020/11/1</p>
                <hr/>
                <p align="center">项目编写环境介绍</p>
                <p>操作系统：Linux Debian 10</p>
                <p>编写工具：IDEA 2020.2</p>
                <p>Java 版本：JDK 14.0.2</p>
                <p>服务器：Tomcat 9.0.34</p>
                <p>数据库：MySQL</p>
                <p>采用技术：JSP+Spring+Spring MVC+MyBatis+jQuery+Ajax+EasyUI+Maven</p>
                <p>技术难点：EasyUI</p>
            </div>
        </div>
    </div>

<%--    页面事件--%>
    <script type="text/javascript">
        $(function () {
           // 给 a 标签绑定时间   选取带有 pageUrl 属性的 a 标签
           $("a[pageUrl]").click(function () {
               // 1.获取 pageUrl 属性值(需要跳转的页面地址)
               var pageUrl = $(this).attr("pageUrl");
               var title = $(this).text();  // 获取 a 标签的内容：标题

               // 2.判断是否存在指定标题的选项卡
               if ($("#tt").tabs("exists", title))
               {
                   // 3.如果存在。则选项该选项卡
                   $("#tt").tabs("select", title);
               }
               else
               {
                   // 4.如果不存在，则添加选项卡
                   $("#tt").tabs("add", {
                       title: title,
                       content: "<iframe src='" + pageUrl + "' width='100%' height='100%' frameborder='0'><iframe>",
                       closable: true
                   });
               }
           });

           // 切换页面主题事件
           $("#Themesmeus").menu({
               onClick: function (item) {
                   // 1.获取需要改变的模块名称
                   var themeName = item.text;
                   // 2.获取 link 标签的 href 属性
                   var href = $("#themeLink").attr("href");
                   // 3.更改 href 属性值
                   href = href.substring(0, href.indexOf("themes")) + "themes/" + themeName + "/easyui.css";
                   // 4.更新 link 的 href 属性
                   $("#themeLink").attr("href", href);
               }
           });
        });
    </script>

<%--    页面底部--%>
    <div data-options="region:'south',split:true" style="height: 30px; color: rebeccapurple">
        <div id="copyrighDiv" style="text-align: center; font: 11px 'Lucida Console';">
            Copyright @2020/10/29 aliuselly rights reserved | 本项目地址：https://github.com/aliuselly/ssmFriend
        </div>
    </div>
</body>
</html>
