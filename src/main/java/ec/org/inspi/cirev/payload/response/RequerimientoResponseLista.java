package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class RequerimientoResponseLista {

	private Integer id;
	private String status;
	private String number;
	private String entryDate;
	
	private String areaProject;
	private String analysis;
	private Boolean isSequenced;
	private String typeSample;
	private Integer numberSamples;
	
	private String requerimentUser;
	
	private String receptionUser;
	private Boolean evidence;


	
}
