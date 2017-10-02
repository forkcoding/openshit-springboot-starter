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
                var coverid=videoInfo.coverid;
                var _rnd=videoInfo._rnd;
                var rmt=videoInfo._qv_rmt;
                var rmt2=videoInfo._qv_rmt2;
                var guid=videoInfo.guid;
                var pid=videoInfo.pid;
                var atimes=videoInfo.atimes;
                var url=videoInfo.url;
                $.ajax({
                    url: "//h5vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&defaultfmt=auto&otype=json&guid="+guid+"&platform=10901&defnpayver=1&appVer=3.2.157&sdtfrom=v1010&host=v.qq.com&ehost="+videoInfo.url+"&_rnd="+_rnd+"&defn=mp4&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=1&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random(),
                    method: "get",
                    dataType : "jsonp",
                    jsonpCallback:"txplayerJsonpCallBack_getinfo_378876"
                }).success(function (data) {

                    var p_url=data.vl.vi[0].ul.ui[0].url;
                    var p_fvkey=data.vl.vi[0].fvkey;
                    var reportUrls = Array();
                    reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + vid + "&coverid=" + coverid + "&pid=" + pid + "_10901&guid=" + guid + "&vt=&type=&url=" + url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=&iformat=&hh_ref=" + url + "&v_idx=0&rcd_info=&vurl=&step=3&val=1&idx=0&isfocustab=1&isvisible=1");

                    reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + vid + "&coverid=" + coverid + "&pid=" + pid + "_10901&guid=" + guid + "&vt=&type=&url=" + url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=" + p_fvkey + "&iformat=&hh_ref=" + url + "&v_idx=0&rcd_info=&vurl=" + encodeURIComponent(p_url) + "&step=1011&val1=1&val2=0&val=5" + Math.ceil(100 * Math.random()));

                    reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + vid + "&coverid=" + coverid + "&pid=" + pid + "_10901&guid=" + guid + "&vt=&type=&url=" + url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=" + p_fvkey + "&iformat=&hh_ref=" + url + "&v_idx=0&rcd_info=&vurl=&step=3&val=1&idx=0&isfocustab=1&isvisible=1");

                    for (var i = 0; i < reportUrls.length; i++) {
                        var r1 = document.createElement("img");
                        r1.src = reportUrls[i];

                    }




                    $.ajax({
                        url: "//h5vv.video.qq.com/getkey?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&filename="+vid+".mp4&format=2&otype=json&guid="+guid+"&platform=10901&defnpayver=0&appVer=3.2.157&vt=203&sdtfrom=v1010&_rnd="+_rnd+"&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random(),
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

