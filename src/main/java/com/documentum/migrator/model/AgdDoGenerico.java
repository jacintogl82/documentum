package com.documentum.migrator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AgdDoGenerico {

	private String rObjectId; 
	private String codigoExpediente; 
	private String objectName; 
	private String archivoFisico; 
	private String sistemaCustodia; 
	private String idCustodia; 
	private String firmado; 
	private String aCategory; 
	private String rCreationDate; 
	private String aContentType;
	
}
