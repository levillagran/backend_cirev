package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class AprobacionDetallesResponseEditar {

	private Integer id;
	private String placeCode;
	private String collectionDate;
	private String processingResults01;
	private String observationResults01;
	private String dateResults01;
	private String processingResults02;
	private String observationResults02;
	private String dateResults02;
	private String processingResults03;
	private String observationResults03;
	private String dateResults03;
	private String primer;
	private String sequence;
	private String concentration;
	private String isFasta;
	private Integer quality;
	private Integer identity;
	private String organism;
	
}
