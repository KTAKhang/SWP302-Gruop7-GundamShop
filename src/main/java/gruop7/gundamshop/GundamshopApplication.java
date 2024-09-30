package gruop7.gundamshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class GundamshopApplication {

	public static void main(String[] args) {
		// container
		ApplicationContext gruop7 = SpringApplication.run(GundamshopApplication.class, args);
		for (String s : gruop7.getBeanDefinitionNames()) {
			System.out.println(s);
		}
	}

}
