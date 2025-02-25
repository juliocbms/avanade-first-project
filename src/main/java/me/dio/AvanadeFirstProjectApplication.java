package me.dio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(servers  = { @Server(url = "/", description = "Defualt Server URL")})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "me.dio.domain.repository")
public class AvanadeFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvanadeFirstProjectApplication.class, args);
	}

}
