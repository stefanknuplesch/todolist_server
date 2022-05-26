package com.campus02.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.campus02.todolist")
public class SpringServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}
}
