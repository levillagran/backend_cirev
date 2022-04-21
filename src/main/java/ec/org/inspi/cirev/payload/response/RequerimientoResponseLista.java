package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class RequerimientoResponseLista {

	private Integer id;
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
	
	public RequerimientoResponseLista(Integer id, String number, String entryDate, String areaProject, String analysis,
			Boolean isSequenced, String typeSample, Integer numberSamples, String requerimentUser, String receptionUser,
			Boolean evidence) {
		super();
		this.id = id;
		this.number = number;
		this.entryDate = entryDate;
		this.areaProject = areaProject;
		this.analysis = analysis;
		this.isSequenced = isSequenced;
		this.typeSample = typeSample;
		this.numberSamples = numberSamples;
		this.requerimentUser = requerimentUser;
		this.receptionUser = receptionUser;
		this.evidence = evidence;
	}

	
}
