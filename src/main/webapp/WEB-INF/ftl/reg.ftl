<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>用户注册</title>
<@mcf.head />
</head>
<body>
<@mcf.header />
<div class="cz-wrap">
    <h2>用户注册</h2>
    <div class="msg-warning">
        这是一个相对封闭的圈子,<strong>推荐人</strong>很重要:<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、 有推荐人, 您可能<strong>为TA带来收益</strong>;<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、 没有推荐人, 您就发布不了尖货信息;<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、 没有推荐人, 您可能会接到我们的一个电话;<br>
    </div>
    <div>
        <ul class="cz-form">
            <li><label for="username">手机号</label><input id="username" name="username"></li>
            <li><label for="password">密码</label><input id="password" name="password" type="password" minlength="6" maxlength="10"><span> * 请输入6-10位密码</span></li>
            <li><label for="nickname">昵称</label><input id="nickname" name="nickname"><span> * 您的朋友一般称呼您?</span></li>
            <li><label for="refphone">推荐人手机</label><input id="refphone" name="refphone"><span> * 重要</span></li>
            <li><label></label><button id="submit" name="submit" type="submit" class="btn btn-sm btn-success">注册</button></li>
        </ul>
    </div>
</div>
<@mcf.footer />
</body>
</html>