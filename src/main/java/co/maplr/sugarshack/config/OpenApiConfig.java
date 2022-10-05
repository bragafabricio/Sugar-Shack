package co.maplr.sugarshack.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Maple Syrup Management System API")
                        .description("This API is to expose endpoints of Maple Shack Management System. ")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Fabricio Braga").
                                url("http://github.com/bragafabricio").
                                email("frtbraga@gmail.com"))
                );
    }
}
