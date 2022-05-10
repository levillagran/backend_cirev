package ec.org.inspi.cirev.services;

import java.util.List;

import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.request.EstadoRequest;
import ec.org.inspi.cirev.payload.request.EstadoValidadorRequest;

public interface EstadoService {
	public List<RequerimientoResponseLista> changeStatus(EstadoRequest requerimiento);
	public List<RequerimientoResponseLista> changeStatusValidator(EstadoValidadorRequest requerimiento);
}
