package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class ResultadoResponseEditar {

	private Integer id;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private Boolean isSequenced;
	private String typeSample;
	private Integer typeSampleId;
		
	private Calendar reportDate;
	private String reportResults;
	private String observationsReport;
	private Integer numberSamples;
	private Integer numberProcessedSamples;

	private List<ResultadoDetallesResponseEditar> details;
	

}
