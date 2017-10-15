package org.eappcat.video;

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
}
