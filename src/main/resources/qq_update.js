(function () {
        var params = window.json_date.preInfo;

        params.appver = '3.2.157';
        var p_fvkey = '';


        var getinfo = "//h5vv.video.qq.com/getinfo?callback=?&charge=0&vid=" + params.vid + "&defaultfmt=auto&otype=json&guid=" + params.guid + "&platform=10901&defnpayver=1&appVer=3.2.157&sdtfrom=v1010&host=v.qq.com&ehost=" + params.url + "&_rnd=" + params._rnd + "&defn=mp4&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=1&_qv_rmt=" + params._qv_rmt + "&_qv_rmt2=" + params._qv_rmt2;
        $.ajax({
            url: getinfo,
            type: "GET",
            dataType: "jsonp",
            async: false,
            complete: function () {
                var reportUrls = Array();
                reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + params.vid + "&coverid=" + params.coverid + "&pid=" + params.pid + "_10901&guid=" + params.guid + "&vt=&type=&url=" + params.url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + params.atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=&iformat=&hh_ref=" + params.url + "&v_idx=0&rcd_info=&vurl=&step=3&val=1&idx=0&isfocustab=1&isvisible=1");

                reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + params.vid + "&coverid=" + params.coverid + "&pid=" + params.pid + "_10901&guid=" + params.guid + "&vt=&type=&url=" + params.url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + params.atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=" + p_fvkey + "&iformat=&hh_ref=" + params.url + "&v_idx=0&rcd_info=&vurl=" + encodeURIComponent(p_url) + "&step=1011&val1=1&val2=0&val=5" + Math.ceil(100 * Math.random()));

                reportUrls.push("//btrace.video.qq.com/kvcollect?BossId=4298&Pwd=686148428&uin=&vid=" + params.vid + "&coverid=" + params.coverid + "&pid=" + params.pid + "_10901&guid=" + params.guid + "&vt=&type=&url=" + params.url + "&bi=&bt=&version=3.2.157&platform=10901&format=&defn=&ctime=" + params.atimes + "&ptag=&isvip=0&tpid=2&pversion=html5hd&hc_uin=&hc_main_login=&hc_vuserid=&hc_openid=&hc_appid=&hc_pvid=&hc_ssid=&hc_qq=&hh_ua=Mozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%208_0%20like%20Mac%20OS%20X)%20AppleWebKit%2F600.1.3%20(KHTML%2C%20like%20Gecko)%20Version%2F8.0%20Mobile%2F12A4345d%20Safari%2F600.1.4&ckey=" + p_fvkey + "&iformat=&hh_ref=" + params.url + "&v_idx=0&rcd_info=&vurl=&step=3&val=1&idx=0&isfocustab=1&isvisible=1");

                for (var i = 0; i < reportUrls.length; i++) {
                    var r1 = document.createElement("img");
                    r1.src = reportUrls[i];

                }//_rnd
                window._fvkey = p_fvkey;

            },
            jsonpCallback: "txplayerJsonpCallBack_getinfo_" + parseInt(Math.random() * 800000 + 80000),
            success: function (g) {
                p_fvkey = g.vl.vi[0].fvkey;
                p_url = g.vl.vi[0].ul.ui[0].url;


                var mp4_do = "//h5vv.video.qq.com/getkey?callback=?&charge=0&vid=" + params.vid + "&filename=" + params.vid + ".mp4&format=2&otype=json&guid=" + params.guid + "&platform=10901&defnpayver=0&appVer=3.2.157&vt=203&sdtfrom=v1010&_rnd=" + params._rnd + "&_qv_rmt=" + params._qv_rmt + "&_qv_rmt2=" + params._qv_rmt2
                $.ajax({
                    url: mp4_do,
                    type: "GET",
                    dataType: "jsonp",
                    jsonpCallback: "txplayerJsonpCallBack_getinfo_" + parseInt(Math.random() * 800000 + 80000),
                    success: function (date) {
                        var adate = 110;
                        var params = {
                            bgcolor: '#FFF',
                            allowFullScreen: true,
                            allowScriptAccess: 'always',
                            wmode: 'transparent',
                            update: adate
                        };
                        var video = [p_url + date.filename + "?vkey=" + date.key];
                        window.title = p_url + date.filename + "?vkey=" + date.key;
                        var flashvars = {
                            f: p_url + date.filename + "?vkey=" + date.key,
                            s: 0,
                            c: 0,
                            p: 1,
                            deff: '',
                            er: '鍔犺浇鍑洪敊銆�'
                        };
                        CKobject.embed('/vip_vip/ckplay/ckplayer.swf?v=20150316', 'player', 'ckplayer_a1', '100%', '99.9%', false, flashvars, video, params);

                    }
                });


            }
        });
    })();
