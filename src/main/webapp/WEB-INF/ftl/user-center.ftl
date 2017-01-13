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
        <p>贡献值: <span class="num">${user.gongxianzhi}</span>,&nbsp;&nbsp;&nbsp;&nbsp;历史总贡献值: <span class="num">${user.total_gongxianzhi}</span>。</p>
        <p></p>
    </div>
</div>
<@mcf.footer />
</body>
</html>