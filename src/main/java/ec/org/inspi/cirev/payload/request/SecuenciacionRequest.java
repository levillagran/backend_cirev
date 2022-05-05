package ec.org.inspi.cirev.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class SecuenciacionRequest {

	private Integer id;
	private String shippingDate;
	private String receptionDate;
	private String observationShipping;
	private String observationReception;
	
	private String processingUsersId;
	
	private List<SecuenciacionDetallesRequest> details;
}
