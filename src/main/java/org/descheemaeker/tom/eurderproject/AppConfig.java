package org.descheemaeker.tom.eurderproject;

import org.descheemaeker.tom.eurderproject.repositories.UserRepository;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.descheemaeker.tom.eurderproject")
public class AppConfig {

    @Bean
    public UserService getUserService() {
        return new UserService(getUserRepository());
    }

    @Bean
    public UserRepository getUserRepository() {
        return new UserRepository();
    }

}
