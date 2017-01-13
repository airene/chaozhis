<#macro ctx>${request.getContextPath()}</#macro>

<#macro head>
<meta name="description" content="">
<meta name="author" content="">
<link href="<@mcf.ctx />/static/css/style.css?THEVERSIONSTRING" type="text/css" rel="stylesheet">
</#macro>

<#macro header>
<div class="header">
    <div class="cz-wrap">
        <div class="cz-logo pull-left">CHAOZHIS</div>
        <div class="cz-logo-r pull-right">
            <#if user??>
                欢迎 <span class="cz-logo">${user.nickname}</span>,
                &nbsp;&nbsp;<a class="top" href="<@mcf.ctx />/u/usercenter">个人中心</a>
                &nbsp;&nbsp;<a class="top" href="<@mcf.ctx />/u/login-out">退出</a>
            <#else>
                <a class="top" href="<@mcf.ctx />/reg">注册</a>&nbsp;&nbsp; <a class="top"
                                                                             href="<@mcf.ctx />/login">登录</a>
            </#if>
        </div>
    </div>
</div>
</#macro>

<#macro footer>
<div class="clear"></div>
<div class="footer">
    <div class="cz-wrap" style="border-top: 1px #ddd solid;padding-top: 6px;">
        <div class="pull-left">
            <ul class="cz-footer">
                <li>加入我们</li>
                <li>违规处理</li>
                <li>github开源</li>
                <li>贡献一下</li>
                <li>项目情况</li>
            </ul>
        </div>
        <div class="pull-right">版权所有 &copy; 2017 CHAOZHIS</div>
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
    <div class="footer">&copy; 2017 Power by Chaozhis.com</div>
</div>
</#macro>