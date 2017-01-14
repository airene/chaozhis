<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>分享一个信息</title>
    <link href="<@mcf.ctx />/static/plugins/simditor/simditor.min.css" rel="stylesheet">
    <@mcf.head />
    <style>
.simditor {
    width:730px;
    margin-left: 160px;
    margin-top: -20px;
    min-height: 100px;
}
        .simditor .simditor-toolbar>ul>li>.toolbar-item {
            width: 36px;height: 30px;line-height: 30px;
        }
        .simditor .simditor-toolbar>ul>li {
            margin: -10px 0 0 0;
        }
    </style>
</head>
<body>
<@mcf.header />
<div class="cz-wrap">
    <h2>分享一个信息</h2>

    <form id="form1" name="form1" action="<@mcf.ctx />/u/post-add" method="post">
    <div>
        <ul class="cz-post">
            <li><label for="phone">标题</label><input id="phone" name="phone"></li>
            <li>
                <label for="valicode">主题</label><em class="current">"退烧"良品</em><em>非常超值</em><em>限时优惠</em>
            </li>
            <li>
                <label for="valicode">分类</label><em class="current">音乐</em><em>外设</em><em>服饰</em><em>生活用品</em><em>育儿</em><em>美食</em>
            </li>
            <li><label for="nickname">标签</label><input id="nickname" name="nickname"></li>
            <li><label for="refphone">购买链接</label><input id="refphone" name="refphone"></li>
            <li><label for="refphone">折扣方法</label><textarea id="refphone" name="refphone" style="vertical-align: top"></textarea></li>
            <li><label for="cz_content">内容</label><textarea id="cz_content" name="cz_content" placeholder="请输入内容..." style="transform:scale(0.5,0.5);"></textarea></li>
            <li><label></label><button id="submit" name="submit" type="submit">提交</button></li>
        </ul>
    </div>
    </form>
</div>
<@mcf.footer />
<script type="text/javascript" src="<@mcf.ctx />/static/plugins/simditor/module.min.js"></script>
<script type="text/javascript" src="<@mcf.ctx />/static/plugins/simditor/hotkeys.min.js"></script>
<script type="text/javascript" src="<@mcf.ctx />/static/plugins/simditor/uploader.min.js"></script>
<script type="text/javascript" src="<@mcf.ctx />/static/plugins/simditor/simditor.min.js"></script>
<script>
    Simditor.locale = 'zh-CN';
    var toolbar = ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'];
    editor = new Simditor({
        textarea: $('#cz_content'),
        toolbar: toolbar,
        toolbarFloat :false,
        defaultImage: '<@mcf.ctx />/static/img/logo.png',
        upload: {
            url: '<@mcf.ctx/>/u/article-upload-image',
            params: {
                "business_code" : "upgg"
            },
            fileKey: "image",
            connectionCount: 1,
            leaveConfirm: "正在上传,确定要取消上传文件吗?"
        }
    });
</script>
</body>
</html>