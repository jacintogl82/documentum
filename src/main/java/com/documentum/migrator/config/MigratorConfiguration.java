package com.documentum.migrator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.documentum.migrator.repository.DocumentLoader;

@PropertySource(value = "migrator.properties")
@Configuration
public class MigratorConfiguration {

	@Value(value = "${migrator.userOrigen}")
	private String user;
	@Value(value = "${migrator.passOrigen}")
	private String password;
	@Value(value = "${migrator.repoOrigen}")
	private String repository;
	@Value(value = "${migrator.dfcHostOrigen}")
	private String dfc_host;
	@Value(value = "${migrator.dfcPortOrigen}")
	private String dfc_port;
	
	@Bean(name = "documentLoaderSource")
	public DocumentLoader documentLoaderSource() {
		return new DocumentLoader(user, password, repository, dfc_host, dfc_port);
	}
}
