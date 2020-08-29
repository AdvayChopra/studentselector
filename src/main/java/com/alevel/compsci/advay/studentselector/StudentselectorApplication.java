package com.alevel.compsci.advay.studentselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StudentselectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentselectorApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/selector/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/eventByUserID/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/login").allowedOrigins("http://localhost:9000");
                registry.addMapping("/eventBySubscriptionID/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/selectedUsersByEventID/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/eventByOrganiserID/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/subscribedUsersByEventID/*").allowedOrigins("http://localhost:9000");
                //registry.addMapping("/updateWeight").allowedOrigins("http://localhost:9000").allowedMethods("PUT");
                registry.addMapping("/updateWeight").allowedOrigins("http://localhost:9000");
            }
        };
    }
}
