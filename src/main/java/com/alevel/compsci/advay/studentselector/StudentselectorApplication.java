package com.alevel.compsci.advay.studentselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class to launch SpringBoot application
 * Includes configuration options
 */
@SpringBootApplication
public class StudentselectorApplication {

    /**
     * Main method for when the application is launched/started
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(StudentselectorApplication.class, args);
    }

    /**
     * CORS(Cross-origin resource sharing ) allow clients
     * running on localhost:9000 access to restricted resources
     * defined below
     * @return
     */
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
                registry.addMapping("/subscriptionWeightingsByEventID/*").allowedOrigins("http://localhost:9000");
                registry.addMapping("/updateWeight").allowedOrigins("http://localhost:9000");
                registry.addMapping("/userById/*").allowedOrigins("http://localhost:9000");
            }
        };
    }
}
