package com.ecommerce.ecommerce_multi_vende.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
      return new WebMvcConfigurer() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
             registry.addMapping("/api/v1/authenticate/login")
                     .allowedOrigins("http://localhost:62250")
                     .allowedMethods("PUT", "DELETE",
                             "GET", "POST")
                     .exposedHeaders("*");

             registry.addMapping("/api/v1/**")
                     .allowedOrigins("http://localhost:62250")
                     .allowedMethods("*");
          }
      };
    }
}
