<#macro ctx>${request.getContextPath()}</#macro>

<#macro head>
<meta name="description" content="">
    <meta name="author" content="">
    <link href="<@mcf.ctx />/static/css/style.css?THEVERSIONSTRING" type="text/css" rel="stylesheet">
</#macro>

<#macro header>
    <div class="an-wrap">
        <div class="an-logo pull-left">Chaozhis<span>pack</span></div>
    </div>
</#macro>

<#macro footer>
    <div class="clear"></div>
    <div class="footer"></div>
    <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</#macro>

<#macro adminHead>
<head>
    <title>Admin control</title>
    <link href="<@mcf.ctx />/static/css/adminstyle.css" rel="stylesheet"/>
</head>
</#macro>

<#macro adminLeft>
</#macro>

<#macro headMobile>
<meta name="description" content="">
    <meta name="author" content="">
    <link href="<@mcf.ctx />/static/css/mobile/mobilestyle.css?THEVERSIONSTRING" type="text/css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</#macro>

<#macro headerMobile>
    <div class="an-header"><div class="an-logo">Chaozhis<span>pack</span></div></div>
</#macro>

<#macro footerMobile>
    <div class="clear"></div>
    <div class="an-wrap">
        <div class="footer">&copy; 2017 Power by Chaozhis.com</div>
    </div>
</#macro>