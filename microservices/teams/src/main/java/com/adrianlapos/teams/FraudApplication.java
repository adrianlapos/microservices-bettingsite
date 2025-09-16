package com.adrianlapos.teams;

import com.adrianlapos.teams.service.TeamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class FraudApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(FraudApplication.class, args);
        TeamService asd  = (TeamService) applicationContext.getBean(TeamService.class);
        System.out.println("controller created" + asd.getClass().getName());
    }
}
