package com.github.guilhermenicolini.sampleapispringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class SampleApiSpringBootApplication {

	private static final Logger logger = LoggerFactory.getLogger(SampleApiSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SampleApiSpringBootApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
		logger.info("Spring boot application running in UTC timezone : {}", new Date());   // It will print UTC timezone
	}
}