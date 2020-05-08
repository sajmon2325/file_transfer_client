package com.opensourcedev.fileTransferClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FileTransferClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileTransferClientApplication.class, args);
	}

}
