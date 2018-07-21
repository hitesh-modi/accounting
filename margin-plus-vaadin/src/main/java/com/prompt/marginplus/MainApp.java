package com.prompt.marginplus;

import com.prompt.marginplus.app.ShiroConfig;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {QuartzAutoConfiguration.class})
@ComponentScan(basePackages={"com.prompt.marginplus"})
@EntityScan(basePackages={"com.prompt.marginplus.entities"})
@EnableJpaRepositories(basePackages={"com.prompt.marginplus.repositories"})
@EnableWebMvc
@Import(ShiroConfig.class)
@EnableVaadin
public class MainApp extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
