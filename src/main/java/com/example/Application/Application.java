package com.example.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

	ApplicationLogic applicationLogic;

	@Autowired
	public Application(ApplicationLogic applicationLogic) {
		this.applicationLogic = applicationLogic;
	}

	public static void main(String[] args) {
		ApplicationLogic applicationLogic = new ApplicationLogic();
		applicationLogic.runApplication();
	}

}
