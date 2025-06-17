package com.montelongo.FindBook;


import com.montelongo.FindBook.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindBookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FindBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();
	}
}
