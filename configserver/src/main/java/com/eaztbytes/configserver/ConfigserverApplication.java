package com.eaztbytes.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication // this annotation means it is main class
@EnableConfigServer // this annotation means, that this  a config server which will read all configuration from a centralized repository
// and exposed all properties through rest end points. We can read configurations from following three : - 
// 1. File system  (which is outside of your project, can be inside a path outside of your project, where you application will be installed 
// or cloud storage i.e. AWS S3 buckets)
// 2. Class path    (we did it like adding a config folder inside src/main/resources, and placed all nine environment specific properties there)
// 3. Github repository
public class ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
