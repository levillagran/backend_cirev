package ec.org.inspi.cirev.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class ProcesamientoRequest {

	private Integer id;
	private Integer techniqueId;
	private Integer kitReagentId;
	
	private String processingUsersId;
	
	private List<ProcesamientoDetallesRequest> details;
}
