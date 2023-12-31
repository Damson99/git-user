package com.gituser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableMongoRepositories
public class GitUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitUserApplication.class, args);
	}
}
