package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class RequerimientoResponseEditar {

	private Integer id;
	private Calendar entryDate;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private Boolean isSequenced;
	private String typeSample;
	private Integer typeSampleId;
	private String specification;
	private Integer specificationId;
	private String observationRequirement;
	private String observationEntry;
	
	private String requerimentUser;
	private Integer requerimentUserId;
	
	private String receptionUser;
	private Integer receptionUserId;
	
	private List<RequerimientoDetallesResponseEditar> details;
	

}
