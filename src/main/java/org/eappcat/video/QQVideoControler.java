package org.eappcat.video;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.eappcat.dao.CoverRepository;
import org.eappcat.entity.Cover;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("cover")
@Controller
public class QQVideoControler {
    @Autowired
    CoverRepository coverRepository;
    @Autowired
    UrlUtils urlUtils;

    @GetMapping("list")
    public String list(@RequestParam("url") String url, Model model) throws Exception{

        Pattern pattern=Pattern.compile("https://([a-zA-Z0-9\\.]+)/x/cover([/a-zA-Z0-9]*)/([a-zA-Z0-9]+)\\.html");
        Matcher matcher=pattern.matcher(url);
        if(matcher.matches()){
            String a= matcher.group(0);
            String b= matcher.group(1);
            String c= matcher.group(2);
            String d= matcher.group(3);
        }else {
            return "empty";
        }

        JSONObject object=urlUtils.parseQQCover(url);
        JSONArray array=object.getJSONArray("nomal_ids");
        model.addAttribute("baseUrl",url.substring(0,url.lastIndexOf("/")));
        model.addAttribute("videos",array.toArray());
        model.addAttribute("url",url);
        model.addAttribute("video",object);

        return "list";

    }
    @GetMapping("save")
    public String save(@RequestParam("url") String url, Model model) throws Exception{
        Cover cover=new Cover();
        JSONObject object=urlUtils.parseQQCover(url);
        String id=object.getString("id");

        cover.setCoverId(id);
        Cover dbCover=coverRepository.findByCoverId(id);
        if(dbCover!=null){
           cover=dbCover;
        }
        cover.setName(object.getString("title"));
        cover.setPic(object.getString("pic"));
        cover.setType("qq");
        cover.setUrl(url);
        coverRepository.save(cover);

        return "redirect:/cover/list?url="+url;

    }
}
