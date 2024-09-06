package team.flight.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000", "https://www.chatppt.site")
                .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With", "Origin", "X-CSRF-Token")
                .maxAge(3600L)
                .allowCredentials(true);
    }
}
