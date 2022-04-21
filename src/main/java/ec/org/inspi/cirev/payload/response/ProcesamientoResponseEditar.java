package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class ProcesamientoResponseEditar {

	private Integer id;
	private Calendar entryDate;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private String typeSample;
	private Integer typeSampleId;
	private String specification;
	private Integer specificationId;
	
	private String technique;
	private Integer techniqueId;
	private String kitReagent;
	private Integer kitReagentId;
	
	private String processingUsers;
	private String processingUsersId;
	
	private List<ProcesamientoDetallesResponseEditar> details;
	

}
