package br.edu.infnet.joao_figueredo_DR1_TP2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.edu.infnet")
public class JoaoFigueredoDr1Tp2Application {

	public static void main(String[] args) {
		SpringApplication.run(JoaoFigueredoDr1Tp2Application.class, args);
	}

}
