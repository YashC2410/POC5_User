package com.springbootapp.users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class);
		//To show up a message on screen
		log.info("Spring Application with User application has started sucesfully!!");
	}

}
