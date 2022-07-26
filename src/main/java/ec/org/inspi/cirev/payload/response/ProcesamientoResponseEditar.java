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
	
	private String technique01;
	private Integer technique01Id;
	private String kitReagent01;
	private Integer kitReagent01Id;
	private String technique02;
	private Integer technique02Id;
	private String kitReagent02;
	private Integer kitReagent02Id;
	private String technique03;
	private Integer technique03Id;
	private String kitReagent03;
	private Integer kitReagent03Id;
	
	private String processingUsers;
	private String processingUsersId;
	
	private List<ProcesamientoDetallesResponseEditar> details;
	

}
