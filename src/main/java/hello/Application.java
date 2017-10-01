package hello;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
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
	WebClient webClient;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Openshift World, enjoy yourself with Openshift.";
	}




	@GetMapping("/vip")
	public String vip(@RequestParam("url") String url, @RequestParam(value = "vip",defaultValue = "2")String vip, Model model) throws Exception{
		String parseURL=parseVideo(url,vip.equalsIgnoreCase("7"),model);
		model.addAttribute("url",parseURL);
		model.addAttribute("refer",url);
		model.addAttribute("vip",vip.equalsIgnoreCase("7"));

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
    public Map<String,String> vipapi(@RequestParam("url") String url,@RequestParam(value = "vip",defaultValue = "2")String vip,Model model) throws Exception{
        String parseURL=parseVideo(url,vip.equalsIgnoreCase("7"),model);
        HashMap<String,String> result=new HashMap();
        result.put("url",parseURL);
        return result;

    }

	private String parseVideo(String url,boolean vip,Model model) throws Exception{
		if(!vip){
			return parseURL(url,model);
		}
		WebRequest webRequest=new WebRequest(new URL("http://api.baiyug.cn/vip_vip/index.php?url="+url));
		webRequest.setAdditionalHeader("Referer","http://api.baiyug.cn/vip/index.php?url="+url);
		HtmlPage p=webClient.getPage(webRequest);
		webClient.waitForBackgroundJavaScript(10000);
		ScriptResult result=p.executeJavaScript("basea17kdv(ykyun)");
		String text=(String)result.getJavaScriptResult();

		return text;
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


	private String parseURL(String url,Model model) throws Exception{
		Connection test= Jsoup.connect("http://api.baiyug.cn/vip_vip/index.php?url="+url);
		test.header("Referer","http://api.baiyug.cn/vip/index.php?url="+url);

		String html=test.get().html();

		String textResult=html.substring(html.indexOf("var adate=")+"var adate=".length());
		String jsonText=textResult.substring("var adate=".length()+1,textResult.indexOf(";")-1);
		JSONObject preInfo= JSON.parseObject(jsonText);

		String vid=preInfo.getString("vid");
		Long _rnd=preInfo.getLong("_rnd");
		String rmt=preInfo.getString("_qv_rmt");
		String rmt2=preInfo.getString("_qv_rmt2");
		String guid=preInfo.getString("guid");


//
//
//		String getVkey="http://h5vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&defaultfmt=auto&otype=json&guid="+guid+"&platform=10901&defnpayver=1&appVer=3.2.157&sdtfrom=v1010&host=v.qq.com&ehost="+preInfo.getString("url")+"&_rnd="+_rnd+"&defn=mp4&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=1&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random();
//
//		String getKey="http://h5vv.video.qq.com/getkey?callback=txplayerJsonpCallBack_getinfo_378876&charge=0&vid="+vid+"&filename="+vid+".mp4&format=2&otype=json&guid="+guid+"&platform=10901&defnpayver=0&appVer=3.2.157&vt=203&sdtfrom=v1010&_rnd="+_rnd+"&_qv_rmt="+rmt+"&_qv_rmt2="+rmt2+"&_="+Math.random();
//
//		Connection connection=Jsoup.connect(getVkey).ignoreContentType(true);
//		Document document=connection.get();
//		String text=document.text();
//		String json=text.substring("txplayerJsonpCallBack_getinfo_378876".length()+1,text.length()-1);
//
//		JSONObject jsonObject= JSON.parseObject(json);
//
//		JSONObject root=jsonObject.getJSONObject("vl").getJSONArray("vi").getJSONObject(0);
//		JSONObject ui=root.getJSONObject("ul").getJSONArray("ui").getJSONObject(1);
//		String videoUrl=ui.getString("url");
//		Integer vt=ui.getInteger("vt");
//
//
//		Connection connection2=Jsoup.connect(getKey).ignoreContentType(true);
//
//		Document document2=connection2.get();
//		String text2=document2.text();
//		String json2=text2.substring("txplayerJsonpCallBack_getinfo_378876".length()+1,text2.length()-1);
//
//		String vk= JSON.parseObject(json2).getString("key");
//		String filename= JSON.parseObject(json2).getString("filename");

		model.addAttribute("videoInfo",JSON.toJSONString(preInfo));

//		return String.format("%s%s?vkey=%s",videoUrl,filename,vk);
		return "";
	}

}
