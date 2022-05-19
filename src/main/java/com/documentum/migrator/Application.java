/**
 * 
 */
package com.documentum.migrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.documentum.migrator.repository.DocumentLoader;

/**
 * Punto de entrada al migrador de documentos
 * 
 * @author jagarclo
 *
 */
@SpringBootApplication(scanBasePackages = {"com.documentum.migrator"})
public class Application implements CommandLineRunner {

	@Autowired
	private DocumentLoader documentLoader;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... args) throws Exception {
		System.out.println(documentLoader);
	}

}
