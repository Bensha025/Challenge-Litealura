package com.alura.litelalura;

import com.alura.litelalura.Principal.Principal;
import com.alura.litelalura.Repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitelaluraApplication implements CommandLineRunner {
	@Autowired
	private LibroRepositorio repository;
	public static void main(String[] args) {
		SpringApplication.run(LitelaluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.mostrarMenu();
	}
}

