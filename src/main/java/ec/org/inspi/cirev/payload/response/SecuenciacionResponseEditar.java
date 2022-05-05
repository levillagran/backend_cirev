package ec.org.inspi.cirev.payload.response;

import java.util.List;

import java.util.Calendar;

import lombok.Data;

@Data
public class SecuenciacionResponseEditar {

	private Integer id;
	private Calendar entryDate;
	private Calendar shippingDate;
	private Calendar receptionDate;
	private String areaProject;
	private Integer areaProjectId;
	private String analysis;
	private Integer analysisId;
	private String typeSample;
	private Integer typeSampleId;
	
	private String observationShipping;
	private String observationReception;
	
	private String processingUsers;
	private String processingUsersId;
	
	private List<SecuenciacionDetallesResponseEditar> details;
	

}
