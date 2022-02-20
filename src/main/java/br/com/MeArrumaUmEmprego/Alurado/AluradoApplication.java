package br.com.MeArrumaUmEmprego.Alurado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AluradoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluradoApplication.class, args);
	}

}
