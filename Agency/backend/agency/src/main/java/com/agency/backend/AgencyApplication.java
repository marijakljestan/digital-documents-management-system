package com.agency.backend;

import com.agency.backend.storage.StorageProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AgencyApplication {

	public static final org.slf4j.Logger LOGGER_INFO = LoggerFactory.getLogger("info");
	public static final org.slf4j.Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
	public static final org.slf4j.Logger LOGGER_WARNING = LoggerFactory.getLogger("warning");

	public static void main(String[] args) {
		SpringApplication.run(AgencyApplication.class, args);
	}

}
