package org.descheemaeker.tom.eurderproject;

import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class EurderProjectApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		User.initiateBasicAccounts(applicationContext.getBean(UserService.class));

		SpringApplication.run(EurderProjectApplication.class);

	}
}
