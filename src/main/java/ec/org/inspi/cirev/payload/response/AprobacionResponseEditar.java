package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class AprobacionResponseEditar {

	private Integer id;
	private Calendar entryDate;
	private Calendar shippingDate;
	private Calendar receptionDate;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private Boolean isSequenced;
	private String typeSample;
	private Integer typeSampleId;
	private String specification;
	private Integer specificationId;
	
	private String technique;
	private Integer techniqueId;
	private String kitReagent;
	private Integer kitReagentId;
	
	private String observationShipping;
	private String observationReception;
	
	private String processingUsers;
	private String processingUsersId;
	
	private List<AprobacionDetallesResponseEditar> details;
	

}
