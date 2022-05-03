package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class SecuenciacionDetallesRequest {
	
	private Integer id;
	private String primer;
	private String sequence;
	private String concentration;
	private Boolean isFasta;
	private Integer quality;
	private Integer identity;
	private String organism;
	
}
