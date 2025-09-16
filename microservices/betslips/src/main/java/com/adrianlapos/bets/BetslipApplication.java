package com.adrianlapos.bets;


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
public class BetslipApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(BetslipApplication.class, args);
    }
}
