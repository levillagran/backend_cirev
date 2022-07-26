package ec.org.inspi.cirev.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.Analisis;
import ec.org.inspi.cirev.models.Especificacion;
import ec.org.inspi.cirev.models.ProyectoArea;
import ec.org.inspi.cirev.models.Reactivo;
import ec.org.inspi.cirev.models.Requerimiento;
import ec.org.inspi.cirev.models.RequerimientoDetalle;
import ec.org.inspi.cirev.models.RequerimientoEstado;
import ec.org.inspi.cirev.models.Tecnica;
import ec.org.inspi.cirev.models.TipoMuestra;
import ec.org.inspi.cirev.models.User;
import ec.org.inspi.cirev.models.UsuarioFirmante;
import ec.org.inspi.cirev.payload.response.AprobacionDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.AprobacionResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.repositories.AnalisisRepository;
import ec.org.inspi.cirev.repositories.DocumentoEvidenciaRepository;
import ec.org.inspi.cirev.repositories.EspecificacionRepository;
import ec.org.inspi.cirev.repositories.ProyectoAreaRepository;
import ec.org.inspi.cirev.repositories.ReactivoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoDetalleRepository;
import ec.org.inspi.cirev.repositories.RequerimientoEstadoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoRepository;
import ec.org.inspi.cirev.repositories.TecnicaRepository;
import ec.org.inspi.cirev.repositories.TipoMuestraRepository;
import ec.org.inspi.cirev.repositories.UserRepository;
import ec.org.inspi.cirev.repositories.UsuarioFirmanteRepository;
import ec.org.inspi.cirev.services.AprobacionesService;

@Service("aprobacionesService")
public class AprobacionesServiceImpl implements AprobacionesService {

	@Autowired
	private RequerimientoRepository requeRepo;
	@Autowired
	private RequerimientoDetalleRepository requeDetRepo;
	@Autowired
	private ProyectoAreaRepository proyectoRepo;
	@Autowired
	private AnalisisRepository anaRepo;
	@Autowired
	private TipoMuestraRepository tmRepo;
	@Autowired
	private EspecificacionRepository esRepoy;
	@Autowired
	private UsuarioFirmanteRepository ufRepo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private DocumentoEvidenciaRepository docRepo;
	@Autowired
	private TecnicaRepository tecRepo;
	@Autowired
	private ReactivoRepository reacRepo;
	@Autowired
	private RequerimientoEstadoRepository reqEstaRep;

	@Override
	public List<RequerimientoResponseLista> findAll(Integer userId) {
		try {
			List<RequerimientoEstado> ids = reqEstaRep.findAllByStatusId(3);
			List<Requerimiento> requerimientos = new ArrayList<>();
			boolean bandera = false;
			for (RequerimientoEstado id : ids) {
				bandera = false;
				Requerimiento req = requeRepo.findById(id.getRequirementId()).get();
				if (req != null && req.getProcessingUsersId() != null) {
					String[] usersProcess = req.getProcessingUsersId().split(",");
					for (String userProcessId : usersProcess) {
						if (Integer.parseInt(userProcessId) != userId || usersProcess.length <= 0) {
							bandera = true;
						}
					}
				}
				if (bandera) requerimientos.add(req);
			}
			List<RequerimientoResponseLista> requeResL = new ArrayList<>();
			RequerimientoResponseLista requeRes;
			for (Requerimiento requerimiento : requerimientos) {
				requeRes = new RequerimientoResponseLista();
				requeRes.setId(requerimiento.getId());
				requeRes.setNumber(requerimiento.getCode());
				requeRes.setEntryDate(calendarToString(requerimiento.getEntryDate()));
				ProyectoArea pa = proyectoRepo.findById(requerimiento.getAreaProjectId()).get();
				requeRes.setAreaProject(pa.getName());
				Analisis an = anaRepo.findById(requerimiento.getAnalysisId()).get();
				requeRes.setAnalysis(an.getName());
				requeRes.setIsSequenced(requerimiento.getIsSequenced());
				TipoMuestra tm = tmRepo.findById(requerimiento.getTypeSampleId()).get();
				requeRes.setTypeSample(tm.getName());
				requeRes.setNumberSamples(requerimiento.getNumberSamples());
				if (requerimiento.getRequerimentUserId() != null) {
					UsuarioFirmante uf = ufRepo.findById(requerimiento.getRequerimentUserId()).get();
					requeRes.setRequerimentUser(
							getName(uf.getPrefix(), uf.getName(), uf.getLastname(), uf.getSuffix()));
				}
				if (requerimiento.getReceptionUserId() != null) {
					User uf = uRepo.findById(requerimiento.getReceptionUserId());
					requeRes.setReceptionUser(getName("", uf.getName(), uf.getLastname(), ""));
				}
				requeRes.setEvidence(
						docRepo.findByRequirementIdAndDocumentTypeId(requerimiento.getId(), 1) != null ? true : false);
				requeResL.add(requeRes);
			}
			return requeResL;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getName(String pre, String name, String lastname, String suf) {
		String nombre = "";
		if (pre == null & suf == null) {
			nombre = name + " " + lastname;
		} else if (pre != null & suf == null) {
			nombre = pre + " " + name + " " + lastname;
		} else if (pre == null & suf != null) {
			nombre = name + " " + lastname + " " + suf;
		} else {
			nombre = pre + " " + name + " " + lastname + " " + suf;
		}
		return nombre;
	}

	public Calendar stringToCalendar(String fecha) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public String calendarToString(Calendar fecha) throws ParseException {
		return String.valueOf(fecha.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(fecha.get(Calendar.MONTH) + 1)
				+ "-" + String.valueOf(fecha.get(Calendar.YEAR));
	}

	@Override
	public AprobacionResponseEditar findById(Integer requerimientoId) {
		AprobacionResponseEditar reqResEdit = new AprobacionResponseEditar();
		List<AprobacionDetallesResponseEditar> reqResDetEdit = new ArrayList<>();
		AprobacionDetallesResponseEditar reqDetEdit;
		Requerimiento req = requeRepo.findById(requerimientoId).get();
		List<RequerimientoDetalle> reqDets = requeDetRepo.findAllByRequirementId(requerimientoId);
		reqResEdit.setId(req.getId());
		reqResEdit.setEntryDate(req.getEntryDate());
		reqResEdit.setAreaProjectId(req.getAreaProjectId());
		ProyectoArea pa = proyectoRepo.findById(req.getAreaProjectId()).get();
		reqResEdit.setAreaProject(pa.getName());
		reqResEdit.setAnalysisId(req.getAnalysisId());
		Analisis an = anaRepo.findById(req.getAnalysisId()).get();
		reqResEdit.setAnalysis(an.getName());
		reqResEdit.setSpecificationId(req.getSpecificationId());
		Especificacion es = esRepoy.findById(req.getSpecificationId()).get();
		reqResEdit.setSpecification(es.getName());
		reqResEdit.setIsSequenced(req.getIsSequenced());
		if (req.getShippingDate() != null)
			reqResEdit.setShippingDate(req.getShippingDate());
		if (req.getReceptionDate() != null)
			reqResEdit.setReceptionDate(req.getReceptionDate());
		if (req.getObservationShipping() != null)
			reqResEdit.setObservationShipping(req.getObservationShipping());
		if (req.getObservationReception() != null)
			reqResEdit.setObservationReception(req.getObservationReception());
		TipoMuestra tm = tmRepo.findById(req.getTypeSampleId()).get();
		reqResEdit.setTypeSample(tm.getName());
		reqResEdit.setTypeSampleId(req.getTypeSampleId());
		if (req.getTechnique01Id() != null) {
			reqResEdit.setTechnique01Id(req.getTechnique01Id());
			Tecnica tec = tecRepo.findById(req.getTechnique01Id()).get();
			reqResEdit.setTechnique01(tec.getName());
		}
		if (req.getKitReagent01Id() != null) {
			reqResEdit.setKitReagent01Id(req.getKitReagent01Id());
			Reactivo reac = reacRepo.findById(req.getKitReagent01Id()).get();
			reqResEdit.setKitReagent01(reac.getName());
		}
		if (req.getTechnique02Id() != null) {
            reqResEdit.setTechnique02Id(req.getTechnique02Id());
            Tecnica tec = tecRepo.findById(req.getTechnique02Id()).get();
            reqResEdit.setTechnique02(tec.getName());
        }
        if (req.getKitReagent02Id() != null) {
            reqResEdit.setKitReagent02Id(req.getKitReagent02Id());
            Reactivo reac = reacRepo.findById(req.getKitReagent02Id()).get();
            reqResEdit.setKitReagent02(reac.getName());
        }
        if (req.getTechnique03Id() != null) {
            reqResEdit.setTechnique03Id(req.getTechnique03Id());
            Tecnica tec = tecRepo.findById(req.getTechnique03Id()).get();
            reqResEdit.setTechnique03(tec.getName());
        }
        if (req.getKitReagent03Id() != null) {
            reqResEdit.setKitReagent03Id(req.getKitReagent03Id());
            Reactivo reac = reacRepo.findById(req.getKitReagent03Id()).get();
            reqResEdit.setKitReagent03(reac.getName());
        }
		if (req.getProcessingUsersId() != null) {
			reqResEdit.setProcessingUsersId(req.getProcessingUsersId());
			String[] usersProcess = req.getProcessingUsersId().split(",");
			String usersProcessConcat = "";
			String userProc = "";
			for (String userId : usersProcess) {
				if (req.getRequerimentUserId() != null) {
					UsuarioFirmante uf = ufRepo.findById(Integer.parseInt(userId)).get();
					userProc = getName("", uf.getName(), uf.getLastname(), "");
				}
				usersProcessConcat = usersProcessConcat + userProc;
			}
			reqResEdit.setProcessingUsers(usersProcessConcat);
		}
		for (RequerimientoDetalle reqDet : reqDets) {
			reqDetEdit = new AprobacionDetallesResponseEditar();
			reqDetEdit.setId(reqDet.getId());
			reqDetEdit.setPlaceCode(reqDet.getPlaceCode());
			try {
				reqDetEdit.setCollectionDate(calendarToString(reqDet.getCollectionDate()));
				if(reqDet.getDateProcess01() != null) reqDetEdit.setDateResults01(calendarToString(reqDet.getDateProcess01())); else  reqDetEdit.setDateResults01(null);
				if(reqDet.getDateProcess02() != null) reqDetEdit.setDateResults02(calendarToString(reqDet.getDateProcess02())); else  reqDetEdit.setDateResults02(null);
				if(reqDet.getDateProcess03() != null) reqDetEdit.setDateResults03(calendarToString(reqDet.getDateProcess03())); else  reqDetEdit.setDateResults03(null);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reqDetEdit.setProcessingResults01(reqDet.getResultProcess01());
			reqDetEdit.setProcessingResults02(reqDet.getResultProcess02());
			reqDetEdit.setProcessingResults03(reqDet.getResultProcess03());
			reqDetEdit.setObservationResults01(reqDet.getObservationProcess01());
			reqDetEdit.setObservationResults02(reqDet.getObservationProcess02());
			reqDetEdit.setObservationResults03(reqDet.getObservationProcess03());
			reqDetEdit.setPrimer(reqDet.getPrimer());
			reqDetEdit.setSequence(reqDet.getSequence());
			reqDetEdit.setConcentration(reqDet.getConcentration());
			if(reqDet.getIsFasta() != null) reqDetEdit.setIsFasta(reqDet.getIsFasta() ? "Si" : "No");
			reqDetEdit.setQuality(reqDet.getQuality());
			reqDetEdit.setIdentity(reqDet.getIdentity());
			reqDetEdit.setOrganism(reqDet.getOrganism());
			reqResDetEdit.add(reqDetEdit);
		}
		reqResEdit.setDetails(reqResDetEdit);
		return reqResEdit;
	}

}
