package microservices.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotoappApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappApiUsersApplication.class, args);
	}

}
