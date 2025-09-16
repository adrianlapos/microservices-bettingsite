package com.adrianlapos.bets;

import com.adrianlapos.bets.authentication.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.adrianlapos.bets",
                "com.amigoscode.amqp",
        }
)

@EnableFeignClients(
        basePackages = "com.adrianlapos.clients"
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class AuthApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(AuthApplication.class, args);
        AuthService asd  = (AuthService) applicationContext.getBean(AuthService.class);
        System.out.println("controller created" + asd.getClass().getName());
    }
}
