package com.documentum.migrator.repository;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfServiceException;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@ToString
@Log4j
public class DocumentLoader {

	private final String user;
	private final String password;
	private final String repository;
	private final String host;
	private final String port;
	

	private IDfClient client;
	private IDfTypedObject config;
	private IDfSessionManager idfSessionManager;
	private IDfSession idfSession;

	@PostConstruct
	public void init() throws DfException, IOException {
		log.info("Obtenemos el cliente documentum");
		this.client = new DfClient();
		config = client.getClientConfig();
		config.setString("primary_host", host);
		config.setString("primary_port", port);
		log.info("Obtenemos el session manager");
		this.idfSessionManager = client.newSessionManager();
	}
	
	public IDfSession getSession() {
		IDfLoginInfo login = new DfLoginInfo();
		login.setUser(user);
		login.setPassword(password);

		try {
			this.idfSessionManager.setIdentity(repository, login);
			this.idfSession = this.idfSessionManager.getSession(repository);
			log.info("Conexion a " + repository + " realizada con exito");
		} catch (DfServiceException e) {
			log.error("Error conectando a  " + repository);
			log.error(e.getMessage());
		}

		return this.idfSession;
	}
	
	public IDfCollection executeQueryCollection(String queryEntrada) throws DfException {
		IDfQuery query = new DfQuery();
		query.setDQL(queryEntrada);
		return query.execute(this.idfSession, 1);
	}

	public void releaseSession() {
		this.idfSessionManager.release(this.idfSession);
	}
	
	public void getDocument(String category) {
		String query = "select r_object_id, codigo_expediente, object_name, archivo_fisico, agd_as_signaturaelectronica.sistema_custodia, agd_as_signaturaelectronica.id_custodia, firmado, a_category, r_creation_date, a_content_type from agd_do_generico where any i_folder_id in (SELECT r_object_id FROM agd_fd_expediente where a_category = '" + category + "')";
		try {
			IDfCollection collection = executeQueryCollection(query);
			
		} catch (DfException e) {
			e.printStackTrace();
		}
	}

}
