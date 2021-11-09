package br.com.bscpaz.sgt;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
	}

	@PostConstruct
	public static void importFile() {
		System.out.println("hello!");
	}

}
