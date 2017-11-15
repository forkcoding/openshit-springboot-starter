package org.eappcat.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.net.URL;

/**
 * Created by yuebo on 2017/10/11.
 */
@Component
public class QQParser {
    private static Logger logger= LoggerFactory.getLogger(QQParser.class);

    @Autowired
    WebClient webClient;
    public String parseVideo(String url,boolean vip,Model model) throws Exception {
        model.addAttribute("vip",vip);
        if (vip) {
            return parseVipUrl(url,model);
        }else{
            try {
                return parseURL(url,model);
            }catch (Exception e){
                logger.error("",e);
                model.addAttribute("vip",true);
                return parseVipUrl(url,model);
            }

        }
    }
    private String parseVipUrl(String url,Model model) throws Exception{
        WebRequest webRequest = new WebRequest(new URL("http://api.baiyug.cn/vip_vip2/sapi.php?url=" + url));
        webRequest.setAdditionalHeader("Referer", "http://api.baiyug.cn/vip/index.php?url=" + url);
        HtmlPage p = webClient.getPage(webRequest);
        webClient.waitForBackgroundJavaScript(10000);
        ScriptResult result = p.executeJavaScript("basea17kdv(ykyun)");
        String text = (String) result.getJavaScriptResult();
        return text;
    }
    private String parseURL(String url,Model model) throws Exception{
        Connection test= Jsoup.connect("http://api.baiyug.cn/vip_vip2/sapi.php?url="+url);
        test.header("Referer","http://api.baiyug.cn/vip/index.php?url="+url);

        String html=test.get().html();

        String textResult=html.substring(html.indexOf("var adate=")+"var adate=".length());
        String jsonText=textResult.substring("var adate=".length()+1,textResult.indexOf(";")-1);
        JSONObject preInfo= JSON.parseObject(jsonText);

//        String vid=preInfo.getString("vid");
//        Long _rnd=preInfo.getLong("_rnd");
//        String rmt=preInfo.getString("_qv_rmt");
//        String rmt2=preInfo.getString("_qv_rmt2");
//        String guid=preInfo.getString("guid");
//
//
//
//
//        String getVkey="http://h5vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&defaultfmt=auto&otype=json&guid="+guid+"&platform=10901&defnpayver=1&appVer=3.2.157&sdtfrom=v1010&host=v.qq.com&ehost="+preInfo.getString("url")+"&_rnd="+_rnd+"&defn=mp4&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=1&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random();
//
//        String getKey="http://h5vv.video.qq.com/getkey?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&filename="+vid+".mp4&format=2&otype=json&guid="+guid+"&platform=10901&defnpayver=0&appVer=3.2.157&vt=203&sdtfrom=v1010&_rnd="+_rnd+"&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random();
//
//        Connection connection=Jsoup.connect(getVkey).ignoreContentType(true);
//        Document document=connection.get();
//        String text=document.text();
//        String json=text.substring("txplayerJsonpCallBack_getinfo_378876".length()+1,text.length()-1);
//
//        JSONObject jsonObject= JSON.parseObject(json);
//
//        JSONObject root=jsonObject.getJSONObject("vl").getJSONArray("vi").getJSONObject(0);
//        JSONObject ui=root.getJSONObject("ul").getJSONArray("ui").getJSONObject(1);
//        String videoUrl=ui.getString("url");
//        Integer vt=ui.getInteger("vt");
//
//
//        Connection connection2=Jsoup.connect(getKey).ignoreContentType(true);
//
//        Document document2=connection2.get();
//        String text2=document2.text();
//        String json2=text2.substring("txplayerJsonpCallBack_getinfo_378876".length()+1,text2.length()-1);
//
//        String vk= JSON.parseObject(json2).getString("key");
//        String filename= JSON.parseObject(json2).getString("filename");

        model.addAttribute("videoInfo",JSON.toJSONString(preInfo));

//		return String.format("%s%s?vkey=%s",videoUrl,filename,vk);
        return "";
    }


}
