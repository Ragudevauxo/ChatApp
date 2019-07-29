package com.auxo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan({"com.auxo.main.controllers"})
@EntityScan("com.auxo.main.Dao")
@EnableJpaRepositories("com.auxo.main.repository")
public class ChatAppApplication 

{

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}

}
