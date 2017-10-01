package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Openshift World, enjoy yourself with Openshift.";
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
