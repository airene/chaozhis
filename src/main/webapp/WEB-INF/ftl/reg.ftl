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
    <#if !phone>
    <div class="msg-warning">
        这是一个相对封闭的圈子,<strong>推荐人</strong>很重要:<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、 有推荐人, 您可能<strong>为TA带来收益</strong>;<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、 没有推荐人, 您就发布不了您的分享;<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、 没有推荐人, 您可能会接到我们的一个电话;<br>
    </div>
    </#if>
    <form id="form1" name="form1" action="<@mcf.ctx />/reg" method="post">
    <div>
        <ul class="cz-form">
            <li><label for="phone">手机号</label><input id="phone" name="phone"></li>
            <li>
                <label for="valicode">验证码</label><input id="valicode" name="valicode" style="width: 100px;">
                <button id="valicode-btn" class="valicode js-code" type="button">获取验证码</button>
                <span><#if flag != null><strong> * 验证码错误</strong></#if></span>
            </li>
            <li><label for="password">密码</label><input id="password" name="password" type="password" minlength="6"
                                                       maxlength="10"><span> * 请输入6-10位密码</span></li>
            <li><label for="nickname">昵称</label><input id="nickname" name="nickname"><span> * 朋友们一般称呼您?</span></li>
            <#if !phone>
                <li><label for="refphone">推荐人手机</label><input id="refphone" name="refphone"><span> * 十分重要</span></li>
            <#else>
                <input type="hidden" id="refphone" name="refphone" value="${phone}">
            </#if>
            <li><label></label><button id="submit" name="submit" type="submit">注册</button></li>
        </ul>
    </div>
    </form>
</div>
<@mcf.footer />
<script>
    $(function () {
        var validCode = true;
        $(".js-code").click(function () {
            var phone = $("#phone");
            if (phone.val().length != 11) {
                phone.val("");
                phone.attr("placeholder", "请输入正确的手机号");
                phone.focus();
                return false;
            }
            var time = 90;
            var code = $(this);
            if (validCode) {
                validCode = false;
                //code.addClass("btn-disable");
                var param = {};
                param["phone"] = phone.val();
                $.post("<@mcf.ctx />/sms/send", param, function () {
                });
                var t = setInterval(function () {
                    time--;
                    code.html(time + "秒");
                    if (time == 0) {
                        clearInterval(t);
                        code.html("获取验证码");
                        validCode = true;
                        //code.removeClass("btn-disable");
                    }
                }, 1000)
            }
        });
    });
</script>
</body>
</html>