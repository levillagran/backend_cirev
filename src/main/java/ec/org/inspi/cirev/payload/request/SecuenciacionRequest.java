package ec.org.inspi.cirev.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class SecuenciacionRequest {

	private Integer id;
	private String shippingReceptionDate;
	private Boolean isShipping;
	private String observationSequence;
	
	private String processingUsersId;
	
	private List<SecuenciacionDetallesRequest> details;
}
