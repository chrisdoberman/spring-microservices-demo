package microservices.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotoappApiAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappApiAccountManagementApplication.class, args);
	}

}
