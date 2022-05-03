package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class SecuenciacionResponseEditar {

	private Integer id;
	private Calendar entryDate;
	private Calendar shippingReceptionDate;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private String typeSample;
	private Integer typeSampleId;
	
	private Boolean isShipping;
	private String observationSequence;
	
	private String processingUsers;
	private String processingUsersId;
	
	private List<SecuenciacionDetallesResponseEditar> details;
	

}
