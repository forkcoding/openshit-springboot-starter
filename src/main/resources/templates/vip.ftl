<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="referrer" content="no-referrer" />
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="http://jackzhang1204.github.io/sewise/sewise_player/player/sewise.player.min.js"></script>

    <title>Document</title>
</head>
<body>
    <#--<video src="${url}" id="example-video" width="600" height="300" class="video-js vjs-default-skin" controls>-->
    <#--</video>-->
    <div id="player" style="width: 600px;height: 400px;">
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
</body>
</html>

