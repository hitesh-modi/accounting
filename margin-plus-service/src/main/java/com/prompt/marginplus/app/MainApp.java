package com.prompt.marginplus.app;

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

import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {QuartzAutoConfiguration.class})
@ComponentScan(basePackages={"com.prompt.marginplus"})
@EntityScan(basePackages={"com.prompt.marginplus.entities"})
@EnableJpaRepositories(basePackages={"com.prompt.marginplus.repositories"})
@Import(ShiroConfig.class)
public class MainApp extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
