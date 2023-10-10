package med.voll.api.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Value("${server.backend.url.local}")
    private String serverLocal;

    @Value("${server.backend.url.production}")
    private String serverProduction;

    @Bean
    public OpenAPI customized() {
        return new OpenAPI()
                .servers(getServers())
                .info(getInfo())
                .components(getComponents());
    }

    private List<Server> getServers() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url(serverLocal).description("local"));
        servers.add(new Server().url(serverProduction).description("production"));
        return servers;
    }

    private Info getInfo() {
        return new Info()
                .title("clinic-voll")
                .description("clinic-voll's backend")
                .version("1.0.0")
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/license/mit/");
    }

    private Components getComponents() {
        Components components = new Components();
        components.addSecuritySchemes("jwt", getJWTSchema());
        return components;
    }

    private SecurityScheme getJWTSchema() {
        return new SecurityScheme()
                .name("jwt")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer");
    }
}
