package com.montelongo.FindBook;


import com.montelongo.FindBook.principal.Principal;
import com.montelongo.FindBook.repository.IAutorRepository;
import com.montelongo.FindBook.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindBookApplication implements CommandLineRunner {

	@Autowired
	private IAutorRepository autorRepository;
	@Autowired
	private ILibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(FindBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository, libroRepository);
		principal.mostrarMenu();
	}
}
