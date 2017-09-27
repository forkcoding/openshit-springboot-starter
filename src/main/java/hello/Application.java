package hello;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class Application {
	@Autowired
	WebClient webClient;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Openshift World, enjoy yourself with Openshift.";
	}




	@GetMapping("/vip")
	public String vip(@RequestParam("url") String url, Model model) throws Exception{
		String parseURL=parseVideo(url);
		model.addAttribute("url",parseURL);

		URL Url=new URL(parseURL);
		HttpURLConnection connection=(HttpURLConnection) Url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(true);
		connection.connect();
		String contentType=connection.getContentType();

		if(contentType.toLowerCase().contains("application/x-mpegurl")){
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			IOUtils.copy(connection.getInputStream(),outputStream);
			String base64= Base64.getEncoder().encodeToString(outputStream.toByteArray());
//			model.addAttribute("url",String.format("data:%s;base64,%s", contentType,base64));
			model.addAttribute("type","m3u8");

		}else{
			model.addAttribute("type","mp4");

		}

		connection.disconnect();

		return "vip";

	}
	@GetMapping("/vip/api")
    @ResponseBody
    public Map<String,String> vipapi(@RequestParam("url") String url) throws Exception{
        String parseURL=parseVideo(url);
        HashMap<String,String> result=new HashMap();
        result.put("url",parseURL);
        return result;

    }

	private String parseVideo(String url) throws Exception{
		WebRequest webRequest=new WebRequest(new URL("http://api.baiyug.cn/vip_vip/index.php?url="+url));
		webRequest.setAdditionalHeader("Referer","http://api.baiyug.cn/vip/index.php?url="+url);
		HtmlPage p=webClient.getPage(webRequest);
		ScriptResult result=p.executeJavaScript("basea17kdv(ykyun)");
		String text=(String)result.getJavaScriptResult();
		return text;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public @Bean  WebClient getWebClient(){
		 WebClient webClient=new WebClient(BrowserVersion.CHROME);
         webClient.getOptions().setCssEnabled(false);
		 return webClient;
	}

}
