package org.eappcat.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Created by yuebo on 2017/10/15.
 */
@Component
public class UrlUtils {
    @Autowired
    YoukuParser youkuParser;
    @Autowired
    QQParser qqParser;

    public String parseVideo(String url,boolean vip,Model model) throws Exception{
        if(url.startsWith("http://v.qq.com")||url.startsWith("https://v.qq.com")){
            return qqParser.parseVideo(url,vip,model);
        }else if(url.startsWith("http://v.youku.com")||url.startsWith("https://v.youku.com")){
            return youkuParser.parseVideo(url);
        }
        else
            throw new RuntimeException("url not support");

    }

    public JSONObject parseQQCover(String url) throws Exception{
        Connection con= Jsoup.connect(url);
        con.header("Accept", "text/html, application/xhtml+xml, */*");
        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
        Document document=con.get();

        String html=document.outerHtml();
        String next=html.substring(html.indexOf("var COVER_INFO =")+"var COVER_INFO =".length());
        String finalS=next.substring(0,next.indexOf("\n"));
        JSONObject object= JSON.parseObject(finalS);
        return object;
    }
}
