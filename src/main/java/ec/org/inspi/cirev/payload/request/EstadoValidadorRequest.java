package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class EstadoValidadorRequest {

	private Integer userId;
	private Integer requerimientoId;
	private Integer estadoId;

}
