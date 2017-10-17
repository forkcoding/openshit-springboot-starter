package org.eappcat.video;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@EnableJpaRepositories(basePackages = "org.eappcat.dao")
@EntityScan("org.eappcat.entity")
public class Application {
	@Autowired
	UrlUtils urlUtils;
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Openshift World, enjoy yourself with Openshift.";
	}

//	@GetMapping("/vip")
//	public String vip(@RequestParam("url") String url, @RequestParam(value = "vip",defaultValue = "2")String vip, Model model) throws Exception{
//		model.addAttribute("vip",true);
//		String parseURL=urlUtils.parseVideo(url,vip.equalsIgnoreCase("7"),model);
//		model.addAttribute("url",parseURL);
//		model.addAttribute("refer",url);
//
//
//		if(StringUtils.isEmpty(parseURL)){
//			model.addAttribute("type","mp4");
//			return "vip";
//		}
//
//		URL Url=new URL(parseURL);
//		HttpURLConnection connection=(HttpURLConnection) Url.openConnection();
//		connection.setDoInput(true);
//		connection.setDoOutput(true);
//		connection.setInstanceFollowRedirects(true);
//		connection.connect();
//		String contentType=connection.getContentType();
//
//		if(contentType.toLowerCase().contains("application/x-mpegurl")||contentType.toLowerCase().contains("application/vnd.apple.mpegurl")){
//			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
//			IOUtils.copy(connection.getInputStream(),outputStream);
//			String base64= Base64.getEncoder().encodeToString(outputStream.toByteArray());
////			model.addAttribute("url",String.format("data:%s;base64,%s", contentType,base64));
//			model.addAttribute("type","m3u8");
//
//		}else{
//			model.addAttribute("type","mp4");
//
//		}
//
//		connection.disconnect();
//
//
//		return "vip";
//
//	}




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
