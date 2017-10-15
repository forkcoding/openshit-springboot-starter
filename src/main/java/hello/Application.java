package hello;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
	YoukuParser youkuParser;
	@Autowired
	QQParser qqParser;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Openshift World, enjoy yourself with Openshift.";
	}




	@GetMapping("/vip")
	public String vip(@RequestParam("url") String url, @RequestParam(value = "vip",defaultValue = "2")String vip, Model model) throws Exception{
		model.addAttribute("vip",true);
		String parseURL=parseVideo(url,vip.equalsIgnoreCase("7"),model);
		model.addAttribute("url",parseURL);
		model.addAttribute("refer",url);


		if(StringUtils.isEmpty(parseURL)){
			model.addAttribute("type","mp4");
			return "vip";
		}

		URL Url=new URL(parseURL);
		HttpURLConnection connection=(HttpURLConnection) Url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(true);
		connection.connect();
		String contentType=connection.getContentType();

		if(contentType.toLowerCase().contains("application/x-mpegurl")||contentType.toLowerCase().contains("application/vnd.apple.mpegurl")){
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
    public Map<String,String> vipapi(@RequestParam("url") String url,@RequestParam(value = "vip",defaultValue = "2")String vip,Model model) throws Exception{
        String parseURL=parseVideo(url,vip.equalsIgnoreCase("7"),model);
        HashMap<String,String> result=new HashMap();
        result.put("url",parseURL);
        return result;

    }

	private String parseVideo(String url,boolean vip,Model model) throws Exception{
//		if(!vip){
//			return parseURL(url,model);
//		}
		if(url.startsWith("http://v.qq.com")||url.startsWith("https://v.qq.com")){
			return qqParser.parseVideo(url,vip,model);
		}else if(url.startsWith("http://v.youku.com")||url.startsWith("https://v.youku.com")){
			return youkuParser.parseVideo(url);
		}
		else
			throw new RuntimeException("url not support");

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public @Bean  WebClient getWebClient(){
		WebClient webClient=new WebClient(BrowserVersion.FIREFOX_52);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常

		return webClient;
	}

//	@GetMapping("/crossdomain.xml")
//    public String crossdomain(){
//	    return "redirect:/static/crossdomain.xml";
//    }
}
