package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class ResultadoRequest {

	private Integer id;
	private String reportDate;
	
	private String reportResults;
	private String observationsReport;
	
	private Integer reportByUserId;
}
