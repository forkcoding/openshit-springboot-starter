package hello;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.net.URL;

/**
 * Created by yuebo on 2017/10/11.
 */
@Component
public class QQParser {

    @Autowired
    WebClient webClient;
    public String parseVideo(String url,boolean vip,Model model) throws Exception{
        WebRequest webRequest=new WebRequest(new URL("http://api.baiyug.cn/vip_vip/api.baiyug.cn.php?url="+url));
        webRequest.setAdditionalHeader("Referer","http://api.baiyug.cn/vip/index.php?url="+url);
        HtmlPage p=webClient.getPage(webRequest);
        webClient.waitForBackgroundJavaScript(10000);
        ScriptResult result=p.executeJavaScript("basea17kdv(ykyun)");
        String text=(String)result.getJavaScriptResult();
        return text;
    }

}
