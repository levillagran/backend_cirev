package ec.org.inspi.cirev.services;

import java.util.List;

import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.request.EstadoRequest;

public interface EstadoService {
	public List<RequerimientoResponseLista> change(EstadoRequest requerimiento);
}
