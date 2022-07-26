package ec.org.inspi.cirev.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import ec.org.inspi.cirev.models.Almacenamiento;
import ec.org.inspi.cirev.models.Analisis;
import ec.org.inspi.cirev.models.Canton;
import ec.org.inspi.cirev.models.DocumentosEvidencia;
import ec.org.inspi.cirev.models.Especificacion;
import ec.org.inspi.cirev.models.Genero;
import ec.org.inspi.cirev.models.Parroquia;
import ec.org.inspi.cirev.models.Provincia;
import ec.org.inspi.cirev.models.ProyectoArea;
import ec.org.inspi.cirev.models.Reactivo;
import ec.org.inspi.cirev.models.Requerimiento;
import ec.org.inspi.cirev.models.RequerimientoDetalle;
import ec.org.inspi.cirev.models.RequerimientoEstado;
import ec.org.inspi.cirev.models.Taxonomia;
import ec.org.inspi.cirev.models.Tecnica;
import ec.org.inspi.cirev.models.TipoMuestra;
import ec.org.inspi.cirev.models.User;
import ec.org.inspi.cirev.models.UsuarioFirmante;
import ec.org.inspi.cirev.payload.request.ProcesamientoDetallesRequest;
import ec.org.inspi.cirev.payload.request.ProcesamientoRequest;
import ec.org.inspi.cirev.payload.request.RequerimientoDetallesRequest;
import ec.org.inspi.cirev.payload.request.RequerimientoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;
import ec.org.inspi.cirev.payload.response.ProcesamientoDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.repositories.AlmacenamientoRepository;
import ec.org.inspi.cirev.repositories.AnalisisRepository;
import ec.org.inspi.cirev.repositories.CantonRepository;
import ec.org.inspi.cirev.repositories.DocumentoEvidenciaRepository;
import ec.org.inspi.cirev.repositories.EspecificacionRepository;
import ec.org.inspi.cirev.repositories.GeneroRepository;
import ec.org.inspi.cirev.repositories.ParroquiaRepository;
import ec.org.inspi.cirev.repositories.ProvinciaRepository;
import ec.org.inspi.cirev.repositories.ProyectoAreaRepository;
import ec.org.inspi.cirev.repositories.ReactivoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoDetalleRepository;
import ec.org.inspi.cirev.repositories.RequerimientoEstadoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoRepository;
import ec.org.inspi.cirev.repositories.TaxonomiaRepository;
import ec.org.inspi.cirev.repositories.TecnicaRepository;
import ec.org.inspi.cirev.repositories.TipoMuestraRepository;
import ec.org.inspi.cirev.repositories.UserRepository;
import ec.org.inspi.cirev.repositories.UsuarioFirmanteRepository;
import ec.org.inspi.cirev.services.ProcesamientosService;
import ec.org.inspi.cirev.services.RequerimientoService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Service("procesamientosService")
public class ProcesamientosServiceImpl implements ProcesamientosService {

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
	private TaxonomiaRepository taxRepo;
	@Autowired
	private ProvinciaRepository provRepo;
	@Autowired
	private CantonRepository cantRepo;
	@Autowired
	private ParroquiaRepository prarrRepo;
	@Autowired
	private GeneroRepository genRepo;
	@Autowired
	private AlmacenamientoRepository alRepo;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private DocumentoEvidenciaRepository docRepo;
	@Autowired
	private TecnicaRepository tecRepo;
	@Autowired
	private ReactivoRepository reacRepo;
	@Autowired
	private RequerimientoEstadoRepository reqEstaRep;

	@Override
	public List<RequerimientoResponseLista> findAll() {
		try {
			List<RequerimientoEstado> ids = reqEstaRep.findAllByStatusId(2);
			List<Requerimiento> requerimientos = new ArrayList<>();
			for (RequerimientoEstado id : ids) {
				Requerimiento req = requeRepo.findFirstByIdAndIsSequencedFalse(id.getRequirementId());
				if (req != null)
					requerimientos.add(req);
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
	public List<RequerimientoResponseLista> save(ProcesamientoRequest requerimientoRequest) {
		try {
			Calendar fechaActual = Calendar.getInstance();
			Requerimiento requerimiento = new Requerimiento();
			if (requerimientoRequest.getId() != null) {
				requerimiento = requeRepo.findById(requerimientoRequest.getId()).get();
				String[] usersProcess = requerimientoRequest.getProcessingUsersId().split(",");
				requerimiento.setModifiedBy(
						usersProcess != null ? Integer.parseInt(usersProcess[usersProcess.length - 1]) : null);
				requerimiento.setModifiedAt(fechaActual);
			}
			if (requerimientoRequest.getTechnique01Id() != null)
				requerimiento.setTechnique01Id(requerimientoRequest.getTechnique01Id());
			if (requerimientoRequest.getKitReagent01Id() != null)
				requerimiento.setKitReagent01Id(requerimientoRequest.getKitReagent01Id());
			if (requerimientoRequest.getTechnique02Id() != null)
				requerimiento.setTechnique02Id(requerimientoRequest.getTechnique02Id());
			if (requerimientoRequest.getKitReagent02Id() != null)
				requerimiento.setKitReagent02Id(requerimientoRequest.getKitReagent02Id());
			if (requerimientoRequest.getTechnique03Id() != null)
				requerimiento.setTechnique03Id(requerimientoRequest.getTechnique03Id());
			if (requerimientoRequest.getKitReagent03Id() != null)
				requerimiento.setKitReagent03Id(requerimientoRequest.getKitReagent03Id());
			requerimiento = requeRepo.save(requerimiento);
			for (ProcesamientoDetallesRequest detalleReq : requerimientoRequest.getDetails()) {
				RequerimientoDetalle detalle = new RequerimientoDetalle();
				if (detalleReq.getId() != null) {
					detalle = requeDetRepo.findById(detalleReq.getId()).get();
					String[] usersProcess = requerimientoRequest.getProcessingUsersId().split(",");
					requerimiento.setModifiedBy(
							usersProcess != null ? Integer.parseInt(usersProcess[usersProcess.length - 1]) : null);
					detalle.setModifiedAt(fechaActual);
				}
				detalle.setResultProcess01(detalleReq.getProcessingResults01());
				detalle.setObservationProcess01(detalleReq.getObservationResults01());
				detalle.setDateProcess01(
						detalleReq.getDateResults01() != null ? stringToCalendar(detalleReq.getDateResults01()) : null);
				detalle.setResultProcess02(detalleReq.getProcessingResults02());
				detalle.setObservationProcess02(detalleReq.getObservationResults02());
				detalle.setDateProcess02(
						detalleReq.getDateResults02() != null ? stringToCalendar(detalleReq.getDateResults02()) : null);
				detalle.setResultProcess03(detalleReq.getProcessingResults03());
				detalle.setObservationProcess03(detalleReq.getObservationResults03());
				detalle.setDateProcess03(
						detalleReq.getDateResults03() != null ? stringToCalendar(detalleReq.getDateResults03()) : null);
				requeDetRepo.save(detalle);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return findAll();
	}

	@Override
	public ProcesamientoResponseEditar findById(Integer requerimientoId) {
		try {
			ProcesamientoResponseEditar reqResEdit = new ProcesamientoResponseEditar();
			List<ProcesamientoDetallesResponseEditar> reqResDetEdit = new ArrayList<>();
			ProcesamientoDetallesResponseEditar reqDetEdit;
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
				reqDetEdit = new ProcesamientoDetallesResponseEditar();
				reqDetEdit.setId(reqDet.getId());
				reqDetEdit.setCode(reqDet.getCode());
				reqDetEdit.setCollectionDate(calendarToString(reqDet.getCollectionDate()));
				reqDetEdit.setProcessingResults01(reqDet.getResultProcess01());
				reqDetEdit.setProcessingResults02(reqDet.getResultProcess02());
				reqDetEdit.setProcessingResults03(reqDet.getResultProcess03());
				reqDetEdit.setObservationResults01(reqDet.getObservationProcess01());
				reqDetEdit.setObservationResults02(reqDet.getObservationProcess02());
				reqDetEdit.setObservationResults03(reqDet.getObservationProcess03());
				if (reqDet.getDateProcess01() != null)
					reqDetEdit.setDateResults01(calendarToString(reqDet.getDateProcess01()));
				else
					reqDetEdit.setDateResults01(null);
				if (reqDet.getDateProcess02() != null)
					reqDetEdit.setDateResults02(calendarToString(reqDet.getDateProcess02()));
				else
					reqDetEdit.setDateResults02(null);
				if (reqDet.getDateProcess03() != null)
					reqDetEdit.setDateResults03(calendarToString(reqDet.getDateProcess03()));
				else
					reqDetEdit.setDateResults03(null);
				reqResDetEdit.add(reqDetEdit);
			}
			reqResEdit.setDetails(reqResDetEdit);
			return reqResEdit;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String findVoucherById(Integer requerimientoId) {
		try {
			DocumentosEvidencia doc = docRepo.findByRequirementIdAndDocumentTypeId(requerimientoId, 1);
			return doc.getDocument();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String createVoucher(Integer requerimientoId) {
		try {
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("requirementId", requerimientoId);

			JasperPrint empReport = JasperFillManager.fillReport(
					JasperCompileManager.compileReport(
							ResourceUtils.getFile("classpath:reports/reporte_proceso.jrxml").getAbsolutePath()),
					parameters // dynamic parameters
					, dataSource.getConnection());

			byte[] pdf = JasperExportManager.exportReportToPdf(empReport);
			String pdfBas64 = Base64.getEncoder().encodeToString(pdf);
			return "data:application/pdf;base64," + pdfBas64;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<RequerimientoResponseLista> saveComprobante(UploadRequest requerimiento) {
		DocumentosEvidencia doc = docRepo.findByRequirementIdAndDocumentTypeId(requerimiento.getId(), 1);
		if (doc != null) {
			doc.setDocument(requerimiento.getEvidence());
			docRepo.save(doc);
			return findAll();
		} else {
			doc = new DocumentosEvidencia();
			DocumentosEvidencia docFirst = docRepo.findFirstByOrderByIdDesc();
			if (docFirst != null)
				doc.setId(docFirst.getId() + 1);
			else
				doc.setId(1);
			doc.setRequirementId(requerimiento.getId());
			doc.setDocument(requerimiento.getEvidence());
			doc.setDocumentTypeId(1);
			doc.setCreatedAt(Calendar.getInstance());
			doc.setCreatedBy(requerimiento.getUserId());
			docRepo.save(doc);
			return findAll();
		}
	}

}
