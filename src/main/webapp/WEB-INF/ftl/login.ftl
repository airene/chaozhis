<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>用户登录</title>
<@mcf.head />
</head>
<body>
<@mcf.header />
<div class="cz-wrap">
    <h2>用户登录</h2>
    <form id="form1" name="form1" action="<@mcf.ctx />/login" method="post">
        <div>
            <ul class="cz-form">
                <li><label for="username">手机号</label><input id="username" name="username"></li>
                <li><label for="password">密码</label><input id="password" name="password" type="password"></li>
                <li><label></label><button id="submit" name="submit" type="submit" class="btn btn-sm btn-success">登录</button>
                </li>
            </ul>
        </div>
    </form>
</div>
<@mcf.footer />
</body>
</html>