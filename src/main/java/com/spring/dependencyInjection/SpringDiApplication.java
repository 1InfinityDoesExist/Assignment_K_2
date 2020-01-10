package com.spring.dependencyInjection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringDiApplication.class);

	static {
		System.out.println("************************I am inside Demo Class Logger***************");
	}

	public static void display() {
		System.out.println("**************I am inside display()**********************************");
	}

	public static void main(String[] args) {
		// PropertyConfigurator.configure("log4j.properties");
		ApplicationContext applicationConext = SpringApplication.run(SpringDiApplication.class, args);
		System.out.println("*************I am inside Main Method**********************************");
		logger.info("Application Context:- " + applicationConext);

	}
}
