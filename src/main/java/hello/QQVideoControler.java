package hello;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuebo on 2017/10/2.
 */
@RequestMapping("qq")
@Controller
public class QQVideoControler {

    @GetMapping("list")
    public String list(@RequestParam("url") String url, Model model) throws Exception{

        Pattern pattern=Pattern.compile("https://([a-zA-Z0-9\\.]+)/x/cover/([a-zA-Z0-9]+)/([a-zA-Z0-9]+)\\.html");
        Matcher matcher=pattern.matcher(url);
        if(matcher.matches()){
            String a= matcher.group(0);
            String b= matcher.group(1);
            String c= matcher.group(2);
            String d= matcher.group(3);
        }else {
            return "empty";
        }





//        for (int i = 0; i<100;i++)
//            System.out.println(parseVideo("https://v.qq.com/x/cover/vz351chxilgiopm/n002415cpjj.html"));

        Connection con= Jsoup.connect(url);
        con.header("Accept", "text/html, application/xhtml+xml, */*");
        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
        Document document=con.get();

        String html=document.outerHtml();
        String next=html.substring(html.indexOf("var COVER_INFO =")+"var COVER_INFO =".length());
        String finalS=next.substring(0,next.indexOf("\n"));
        JSONObject object= JSON.parseObject(finalS);

        JSONArray array=object.getJSONArray("nomal_ids");
        model.addAttribute("baseUrl",url.substring(0,url.lastIndexOf("/")));
        model.addAttribute("videos",array.toArray());
        model.addAttribute("url",url);
        model.addAttribute("video",object);

        return "list";

    }
}
