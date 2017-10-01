<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="referrer" content="no-referrer" />
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="//jackzhang1204.github.io/sewise/sewise_player/player/sewise.player.min.js"></script>
    <style>
        .vid-wrapper>div{
            width:100%;
            position:relative;
            padding-bottom:56.25%;    /*需要用padding来维持16:9比例,也就是9除以16*/
            height: 0;
            max-width: 800px;
            margin: 0 auto;
        }
        .vid-wrapper>div video,.vid-wrapper>div object{
            position: absolute;
            top:0;
            left: 0;
            width: 100%;
            height: 100%
        }
        .item{
            display: inline-block;width: 30px;height: 30px;text-align: center;line-height: 30px;
            margin: 5px;
        }
    </style>
    <title>网页播放器</title>
</head>
<body>
    <#--<video src="${url}" id="example-video" width="600" height="300" class="video-js vjs-default-skin" controls>-->
    <#--</video>-->
    <div id="player" class="vid-wrapper" style="margin: 0 auto">
    <script type="text/javascript">
        var letvURL = "${url}";
        SewisePlayer.setup({
            server: "vod",
            type: "${type}",
            videourl: letvURL,
            skin: "vodWhite",
            claritybutton: "disable",
            title: "播放器",
            logo: null,
            lang: 'zh_CN'
        },"player");
    </script>
    </div>
    <a href="/qq/list?url=${refer}">返回专辑</a>
</body>
</html>

