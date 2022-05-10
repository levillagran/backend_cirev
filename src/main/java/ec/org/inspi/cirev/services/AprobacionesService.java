package ec.org.inspi.cirev.services;

import java.util.List;

import ec.org.inspi.cirev.payload.response.AprobacionResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;

public interface AprobacionesService {
	public List<RequerimientoResponseLista> findAll(Integer userId);
	public AprobacionResponseEditar findById(Integer requerimientoId);
}
