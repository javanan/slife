package com.slife;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chen
 */
@SpringBootApplication
@EnableAdminServer
public class SlifeBootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlifeBootAdminApplication.class, args);
	}
}
