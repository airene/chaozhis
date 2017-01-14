<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>个人中心</title>
<@mcf.head />
</head>
<body>
<@mcf.header />
<div class="cz-wrap">
    <@mcf.siderBar />
    <div class="pull-right cz-content">
        <h2>我的概览</h2>
        <#if user.status == 1>
            <div class="msg-warning">目前您还不能分享信息, 请等待您的邀请人审核或者客服来验证! please wait...</div>
        </#if>
        <p>当前贡献值: <span class="num">${user.gongxianzhi}</span>,&nbsp;&nbsp;&nbsp;历史总贡献值: <span class="num">${user.total_gongxianzhi}</span>,&nbsp;&nbsp;&nbsp;系统共产生贡献值: <span class="num">${user.total_gongxianzhi}</span>。</p>
        <p></p>
    </div>
</div>
<@mcf.footer />
</body>
</html>