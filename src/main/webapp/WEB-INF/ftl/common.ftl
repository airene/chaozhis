<#macro ctx>${request.getContextPath()}</#macro>

<#macro head>
<meta name="description" content="">
<meta name="author" content="">
<link href="<@mcf.ctx />/static/css/style.css?THEVERSIONSTRING" type="text/css" rel="stylesheet">
</#macro>

<#macro header>
<div class="header">
    <div class="cz-wrap">
        <div class="cz-logo pull-left"><a class="top" href="<@mcf.ctx />/">乐分享</a></div>
        <div class="cz-logo-r pull-right">
            <#if user??>
                欢迎您,  <a class="user" href="<@mcf.ctx />/u/user-center">${user.nickname}</a>
                <#if user.status == 2>&nbsp;&nbsp;<a class="top" href="<@mcf.ctx />/u/post-add">分享一个</a></#if>
                &nbsp;&nbsp;<a class="top" href="<@mcf.ctx />/u/user-center">玩转攻略</a>
                &nbsp;&nbsp;<a class="top" href="<@mcf.ctx />/u/login-out">退出</a>
            <#else>
                <a class="top" href="<@mcf.ctx />/reg">玩转攻略</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="top" href="<@mcf.ctx />/reg">注册</a>&nbsp;&nbsp;
                <a class="top" href="<@mcf.ctx />/login">登录</a>
            </#if>
        </div>
    </div>
</div>
</#macro>

<#macro siderBar>
    <div class="pull-left cz-sidebar">
        <h2>个人中心</h2>
        <ul>
            <li><a href="<@mcf.ctx />/u/user-center">我的概览</a></li>
            <li><a href="<@mcf.ctx />/u/invite-info">我邀请的人</a></li>
            <li><a href="<@mcf.ctx />/u/post-list">审核分享</a></li>
        </ul>
    </div>
</#macro>

<#macro footer>
<div class="clear"></div>
<div class="footer">
    <div class="cz-wrap" style="border-top: 1px #dbcee7 solid;padding-top: 6px;">
        <div class="pull-left">
            <ul class="cz-footer">
                <li>加入我们</li>
                <li>违规处理</li>
                <li>github开源</li>
                <li>贡献一下</li>
                <li>项目情况</li>
            </ul>
        </div>
        <div class="pull-right">版权所有 &copy; 2017 乐分享</div>
    </div>
</div>
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</#macro>

<#macro headMobile>
<meta name="description" content="">
<meta name="author" content="">
<link href="<@mcf.ctx />/static/css/mobile/mobilestyle.css?THEVERSIONSTRING" type="text/css" rel="stylesheet">
<meta name="viewport"
      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</#macro>

<#macro headerMobile>
<div class="an-header">
    <div class="an-logo">Chaozhis<span>pack</span></div>
</div>
</#macro>

<#macro footerMobile>
<div class="clear"></div>
<div class="an-wrap">
    <div class="footer">&copy; 2017 Power By 乐分享</div>
</div>
</#macro>