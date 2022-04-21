package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class ProcesamientoDetallesResponseEditar {

	private Integer id;
	private String placeCode;
	private String collectionDate;
	private String processingResults;
	private String observationResults;
	
}
