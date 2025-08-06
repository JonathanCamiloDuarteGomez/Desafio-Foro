package com.cdg.Foro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoApplication.class, args);
	}
	//java -jar .\target\Foro-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --DATASOURCE_URL=jdbc:mysql://localhost/foro --DATASOURCE_USERNAME=root --DATASOURCE_PASSWORD=root
}
