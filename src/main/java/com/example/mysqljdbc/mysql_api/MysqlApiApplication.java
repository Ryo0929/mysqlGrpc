package com.example.mysqljdbc.mysql_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class MysqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlApiApplication.class, args);
		//Server server = new Server(8000);
	}

}
