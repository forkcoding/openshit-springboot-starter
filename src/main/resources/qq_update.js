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












var c = i.hexToString(i.ha(e + t + n + i._Seed + s + a + r.charAt(0) + o)) // e是platfrom, t是vid, n是时间戳, s和a观察下来是空字符串, r观察下来就是个字符串'1', o是sdtfrom
    ,p = i.urlenc(i.tempcalc(c, i._Seed), r.charAt(0), n)
    ,u = i.u1(p, 0) // 这个值是_qv_rmt
var h = i.u1(p, 1)  // 这个值是_qv_rmt2

i.ha = function(e) {
    function t(e, t) {
        return ((e >> 1) + (t >> 1) << 1) + (1 & e) + (1 & t)
    }
    for (var i = [], o = 0; o < 64; )
        i[o] = 0 | 4294967296 * Math.abs(Math.sin(++o));
    var r = function(o) {
        for (var r, n, s, a, d = [], c = window.unescape(encodeURI(o)), p = c.length, l = [r = 1732584193, n = -271733879, ~r, ~n], u = 0; u <= p; )
            d[u >> 2] |= (c.charCodeAt(u) || 128) << 8 * (u++ % 4);
        for (d[o = (p + 8 >> 6) * e + 14] = 8 * p,
                 u = 0; u < o; u += e) {
            for (p = l,
                     a = 0; a < 64; )
                p = [s = p[3], t(r = p[1], (s = t(t(p[0], [r & (n = p[2]) | ~r & s, s & r | ~s & n, r ^ n ^ s, n ^ (r | ~s)][p = a >> 4]), t(i[a], d[[a, 5 * a + 1, 3 * a + 5, 7 * a][p] % e + u]))) << (p = [7, 12, 17, 22, 5, 9, 14, 20, 4, 11, e, 23, 6, 10, 15, 21][4 * p + a++ % 4]) | s >>> 32 - p), r, n];
            for (a = 4; a; )
                l[--a] = t(l[a], p[a])
        }
        for (o = ""; a < 32; )
            o += (l[a >> 3] >> 4 * (1 ^ 7 & a++) & 15).toString(e);
        return o
    };
    return r
}(16)

i.hexToString = function(e) {
    for (var t = "", i = "0x" == e.substr(0, 2) ? 2 : 0; i < e.length; i += 2)
        t += String.fromCharCode(parseInt(e.substr(i, 2), 16));
    return t
}

i._Seed = "#$#@#*ad",

    i._urlStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
    i.urlenc = function(e, t, o) {
        for (var r, n, s, a, d, c, p, l = "", u = 0; u < e.length; )
            r = e.charCodeAt(u++),
                n = e.charCodeAt(u++),
                s = e.charCodeAt(u++),
            15 == u && (l += "A",
                l += t,
                l += o),
                a = r >> 2,
                d = (3 & r) << 4 | n >> 4,
                c = (15 & n) << 2 | s >> 6,
                p = 63 & s,
                isNaN(n) ? c = p = 64 : isNaN(s) && (p = 64),
                l = l + i._urlStr.charAt(a) + i._urlStr.charAt(d) + i._urlStr.charAt(c) + i._urlStr.charAt(p);
        return l
    }
i.u1 = function(e, t) {
    for (var i = "", o = t; o < e.length; o += 2)
        i += e.charAt(o);
    return i
}
