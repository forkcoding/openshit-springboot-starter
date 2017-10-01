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
        function load_video(url){
            var letvURL = url;
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
        }
        $(document).ready(function () {
            <#if vip>
                load_video("${url}")
            </#if>
            <#if !vip>
                var videoInfo=${videoInfo};

                var vid=videoInfo.vid;
                var _rnd=videoInfo._rnd;
                var rmt=videoInfo._qv_rmt;
                var rmt2=videoInfo._qv_rmt2;
                var guid=videoInfo.guid;
                $.ajax({
                    url: "http://h5vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&defaultfmt=auto&otype=json&guid="+guid+"&platform=10901&defnpayver=1&appVer=3.2.157&sdtfrom=v1010&host=v.qq.com&ehost="+videoInfo.url+"&_rnd="+_rnd+"&defn=mp4&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=1&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random(),
                    method: "get",
                    dataType : "jsonp",
                    jsonpCallback:"txplayerJsonpCallBack_getinfo_378876"
                }).success(function (data) {
                    $.ajax({
                        url: "http://h5vv.video.qq.com/getkey?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&filename="+vid+".mp4&format=2&otype=json&guid="+guid+"&platform=10901&defnpayver=0&appVer=3.2.157&vt=203&sdtfrom=v1010&_rnd="+_rnd+"&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random(),
                        method: "get",
                        dataType : "jsonp",
                        jsonpCallback:"txplayerJsonpCallBack_getinfo_378876"
                    }).success(function (data2) {
                        load_video(data.vl.vi[0].ul.ui[0].url+data2.filename+"?vkey="+data2.key);
                    })
                })

            </#if>


        })
    </script>
    </div>
    <a href="/qq/list?url=${refer}">返回专辑</a>
</body>
</html>

