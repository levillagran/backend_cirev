package ec.org.inspi.cirev.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class RequerimientoRequest {

	private Integer id;
	private String entryDate;
	private Integer areaProjectId;
	private Integer analysisId;
	private Integer specificationId;
	private Integer typeSampleId;
	private Boolean isSequenced;
	
	private String observationRequirement;
	private String observationEntry;
	
	private Integer requerimentUserId;	
	private Integer receptionUserId;
	
	private List<RequerimientoDetallesRequest> details;
}
