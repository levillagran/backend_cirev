package ec.org.inspi.cirev.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.RequerimientoEstado;
import ec.org.inspi.cirev.payload.request.EstadoRequest;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.repositories.RequerimientoEstadoRepository;
import ec.org.inspi.cirev.services.EstadoService;
import ec.org.inspi.cirev.services.RequerimientoService;

@Service("estadoService")
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private RequerimientoEstadoRepository reqEstaRep;
	@Autowired
	private RequerimientoService reqSer;

	@Override
	public List<RequerimientoResponseLista> change(EstadoRequest requerimiento) {
		RequerimientoEstado reqEs = reqEstaRep.findById(requerimiento.getRequerimientoId()).get();
		reqEs.setStatusId(requerimiento.getEstadoId());
		return reqSer.findAll();
	}

}
