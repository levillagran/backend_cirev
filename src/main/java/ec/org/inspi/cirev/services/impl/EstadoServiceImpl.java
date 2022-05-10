package ec.org.inspi.cirev.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.Requerimiento;
import ec.org.inspi.cirev.models.RequerimientoEstado;
import ec.org.inspi.cirev.payload.request.EstadoRequest;
import ec.org.inspi.cirev.payload.request.EstadoValidadorRequest;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.repositories.RequerimientoEstadoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoRepository;
import ec.org.inspi.cirev.services.AprobacionesService;
import ec.org.inspi.cirev.services.EstadoService;
import ec.org.inspi.cirev.services.RequerimientoService;

@Service("estadoService")
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private RequerimientoEstadoRepository reqEstaRep;
	@Autowired
	private RequerimientoService reqSer;
	@Autowired
	private RequerimientoRepository reqRep;
	@Autowired
	private AprobacionesService apSer;

	@Override
	public List<RequerimientoResponseLista> changeStatus(EstadoRequest requerimiento) {
		RequerimientoEstado reqEs = reqEstaRep.findById(requerimiento.getRequerimientoId()).get();
		reqEs.setStatusId(requerimiento.getEstadoId());
		reqEstaRep.save(reqEs);
		return reqSer.findAll();
	}

	@Override
	public List<RequerimientoResponseLista> changeStatusValidator(EstadoValidadorRequest requerimiento) {
		RequerimientoEstado reqEs = reqEstaRep.findById(requerimiento.getRequerimientoId()).get();
		Requerimiento req = reqRep.findById(requerimiento.getRequerimientoId()).get();
		reqEs.setStatusId(requerimiento.getEstadoId());
		reqEstaRep.save(reqEs);
		req.setValidatorUserId(requerimiento.getUserId());
		reqRep.save(req);
		return apSer.findAll(requerimiento.getUserId());
	}

}
