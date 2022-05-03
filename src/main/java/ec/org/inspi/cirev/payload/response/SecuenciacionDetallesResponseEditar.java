package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class SecuenciacionDetallesResponseEditar {

	private Integer id;
	private String placeCode;
	private String primer;
	private String sequence;
	private String concentration;
	private String isFasta;
	private Integer quality;
	private Integer identity;
	private String organism;
	
}
