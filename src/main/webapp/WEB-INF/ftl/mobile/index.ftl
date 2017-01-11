<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title></title>
    <@mcf.headMobile />
</head>
<body>
<@mcf.headerMobile />
    <div class="an-wrap">
        <div class="contentshadow">
            <h2></h2>
            <div class="block content">
                <ul>
                <#list list as twitter>
                    <li>${twitter.t_content}</li>
                </#list>
                </ul>
            </div>
        </div>
    </div>
<@mcf.footerMobile />
</body>
</html>