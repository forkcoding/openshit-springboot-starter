package org.eappcat.video;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by yuebo on 2017/10/11.
 */
@Component
public class YoukuParser {
    @Autowired
    WebClient webClient;


    public String parseVideo(String url) throws IOException {


        String md5=getMd5(url);

        Connection connection=Jsoup.connect("http://api.baiyug.cn/vip_p_0bc6/url.php");
        connection.header("Referer","http://api.baiyug.cn");
        connection.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        connection.header("X-Requested-With","XMLHttpRequest");
        connection.header("Content-Type","application/x-www-form-urlencoded");

        connection.data("id",url);
        connection.data("siteuser","");
        connection.data("type","auto");
        connection.data("md5",md5);
        String text=connection.post().body().text();

        JSONObject object=JSONObject.parseObject(text);
        if(object.containsKey("url")){
            return URLDecoder.decode(object.getString("url"),"utf-8");
        }else{
            throw new RuntimeException("error for url");
        }

    }

    private String getMd5(String url) throws IOException {
//        Connection connection=Jsoup.connect("http://api.baiyug.cn/vip_p_0bc6/index.php?url="+url);
//        connection.header("Referer","http://api.baiyug.cn/vip/index.php?url="+url);
//        connection.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//        connection.header("Accept", "text/html, application/xhtml+xml, */*");
//
//        Document document=connection.get();
//
//        String value=document.getElementById("hdMd5").attr("value");
        WebRequest webRequest=new WebRequest(new URL("http://api.baiyug.cn/vip_p_0bc6/index.php?url="+url));
        webRequest.setAdditionalHeader("Referer","http://api.baiyug.cn/vip/index.php?url"+url);
        HtmlPage p=webClient.getPage(webRequest);
        webClient.waitForBackgroundJavaScript(10000);
        ScriptResult result=p.executeJavaScript("$('#hdMd5').val()");
        String text=(String)result.getJavaScriptResult();

        return text;


    }
}
