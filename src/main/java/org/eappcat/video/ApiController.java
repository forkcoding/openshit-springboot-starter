package org.eappcat.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.eappcat.dao.CoverRepository;
import org.eappcat.entity.Cover;
import org.eappcat.model.ResponseVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuebo on 2017/10/15.
 */
@RequestMapping("api")
@RestController
public class ApiController {
    @Autowired
    UrlUtils urlUtils;

    @Autowired
    CoverRepository coverRepository;
    @GetMapping("url")
    @ResponseBody
    public Map<String,String> vipapi(@RequestParam("url") String url, @RequestParam(value = "vip",defaultValue = "2")String vip, Model model) throws Exception{
        String parseURL=urlUtils.parseVideo(url,vip.equalsIgnoreCase("7"),model);
        HashMap<String,String> result=new HashMap();
        result.put("url",parseURL);
        return result;
    }
    @GetMapping("list")
    public Map list(@RequestParam("url") String url, Model model,@RequestParam(value = "type",defaultValue = "qq") String type) throws Exception{
        Map<String,Object> result=new HashMap();

        if("qq".equalsIgnoreCase(type)){
            Pattern pattern=Pattern.compile("https://([a-zA-Z0-9\\.]+)/x/cover([/a-zA-Z0-9]*)/([a-zA-Z0-9]+)\\.html");
            Matcher matcher=pattern.matcher(url);
            if(matcher.matches()){
                String a= matcher.group(0);
                String b= matcher.group(1);
                String c= matcher.group(2);
                String d= matcher.group(3);
            }else {
                result.put("status",500);
                return result;
            }

            Connection con= Jsoup.connect(url);
            con.header("Accept", "text/html, application/xhtml+xml, */*");
            con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
            Document document=con.get();

            String html=document.outerHtml();
            String next=html.substring(html.indexOf("var COVER_INFO =")+"var COVER_INFO =".length());
            String finalS=next.substring(0,next.indexOf("\n"));
            JSONObject object= JSON.parseObject(finalS);

            JSONArray array=object.getJSONArray("nomal_ids");
            result.put("baseUrl",url.substring(0,url.lastIndexOf("/")));
            result.put("videos",array.toArray());
            result.put("url",url);
            result.put("video",object);
            result.put("status",0);
        }else{
            result.put("status",500);
        }

        return result;
    }
    @GetMapping("covers")
    public ResponseVO<List<Cover>> listCovers(){
        ResponseVO<List<Cover>> responseVO=new ResponseVO();
        responseVO.setData(coverRepository.findAll());
        return responseVO;
    }


}
