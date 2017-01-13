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
        <h2>我邀请的人</h2>
        <table width="100%" class="pro table-hover">
        <thead>
        <tr>
            <th>序号</th>
            <th>昵称</th>
            <th>手机号</th>
            <th>注册时间</th>
            <th>来源</th>
            <th>操作</th>
        </tr>
        </thead>
    <#list inviteList as item>
        <tr>
            <td align="center">${item_index+1}</td>
            <td align="right">${item.nickname}</td>
            <td align="right">${item.phone}</td>
            <td align="right">${item.reg_time}%</td>
            <td align="right">PC</td>
            <td align="left">
                <a href="<@mcf.ctx/>/u/business-detail" class="btn btn-default btn-xs">审核通过</a><a href="<@mcf.ctx/>/u/business-detail" class="btn btn-default btn-xs">我不认识他</a>
            </td>
        </tr>
    </#list>
    </table>
    </div>
</div>
<@mcf.footer />
</body>
</html>