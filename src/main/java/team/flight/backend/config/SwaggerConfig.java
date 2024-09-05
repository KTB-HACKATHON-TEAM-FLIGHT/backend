package team.flight.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Team-Flight API 명세서"
                , description = "서버 api 문서입니다.", version = "v1")
)
@Configuration
public class SwaggerConfig {

    @Value("${server.domain}")
    private String serverDomain;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(serverDomain).description("Server Domain"))
                .addServersItem(new Server().url("http://localhost:8080").description("Local Server"));
    }
}
