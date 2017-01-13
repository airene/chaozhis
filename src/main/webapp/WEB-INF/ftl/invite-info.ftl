<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>我邀请的人</title>
<@mcf.head />
</head>
<body>
<@mcf.header />
<div class="cz-wrap">
    <@mcf.siderBar />
    <div class="pull-right cz-content">
        <h2>我邀请的人<span class="pull-right">为了维护圈子健康,不认识的人请不要审核通过!</span></h2>
        <table width="100%" class="pro table-hover" border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>昵称</th>
                <th>手机号</th>
                <th>注册时间</th>
                <th>操作</th>
            </tr>
            </thead>
        <#list inviteList as item>
            <tr>
                <td align="center">${item_index+1}</td>
                <td align="left">${item.nickname}</td>
                <td align="center">${item.phone}</td>
                <td align="right">${item.reg_time}</td>
                <td align="left">
                    <a href="<@mcf.ctx/>/" class="cz-btn btn-success">通过</a><a href="<@mcf.ctx/>/" class="cz-btn">不认识</a>
                </td>
            </tr>
        </#list>
        </table>
    </div>
</div>
<@mcf.footer />
</body>
</html>