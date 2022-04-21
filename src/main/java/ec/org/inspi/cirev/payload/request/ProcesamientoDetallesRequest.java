package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class ProcesamientoDetallesRequest {
	
	private Integer id;
	private String processingResults;
	private String observationResults;
	
}
