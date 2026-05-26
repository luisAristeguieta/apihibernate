package apihibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.krakdev")
@EnableJpaRepositories(basePackages = "com.krakdev")
@EntityScan(basePackages = "com.krakdev")

public class ApihibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApihibernateApplication.class, args);
	}

}
