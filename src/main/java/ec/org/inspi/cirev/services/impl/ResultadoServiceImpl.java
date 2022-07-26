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
import ec.org.inspi.cirev.models.Requerimiento;
import ec.org.inspi.cirev.models.RequerimientoDetalle;
import ec.org.inspi.cirev.models.RequerimientoEstado;
import ec.org.inspi.cirev.models.Taxonomia;
import ec.org.inspi.cirev.models.TipoMuestra;
import ec.org.inspi.cirev.models.UsuarioFirmante;
import ec.org.inspi.cirev.payload.request.RequerimientoDetallesRequest;
import ec.org.inspi.cirev.payload.request.RequerimientoRequest;
import ec.org.inspi.cirev.payload.request.ResultadoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;
import ec.org.inspi.cirev.payload.response.AprobacionDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.response.ResultadoDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.ResultadoResponseEditar;
import ec.org.inspi.cirev.repositories.AlmacenamientoRepository;
import ec.org.inspi.cirev.repositories.AnalisisRepository;
import ec.org.inspi.cirev.repositories.CantonRepository;
import ec.org.inspi.cirev.repositories.DocumentoEvidenciaRepository;
import ec.org.inspi.cirev.repositories.EspecificacionRepository;
import ec.org.inspi.cirev.repositories.GeneroRepository;
import ec.org.inspi.cirev.repositories.ParroquiaRepository;
import ec.org.inspi.cirev.repositories.ProvinciaRepository;
import ec.org.inspi.cirev.repositories.ProyectoAreaRepository;
import ec.org.inspi.cirev.repositories.RequerimientoDetalleRepository;
import ec.org.inspi.cirev.repositories.RequerimientoEstadoRepository;
import ec.org.inspi.cirev.repositories.RequerimientoRepository;
import ec.org.inspi.cirev.repositories.TaxonomiaRepository;
import ec.org.inspi.cirev.repositories.TipoMuestraRepository;
import ec.org.inspi.cirev.repositories.UsuarioFirmanteRepository;
import ec.org.inspi.cirev.services.ResultadoService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service("resultadoService")
public class ResultadoServiceImpl implements ResultadoService {

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
	private RequerimientoEstadoRepository reqEstaRep;

	@Override
	public List<RequerimientoResponseLista> findAll() {
		try {
			List<RequerimientoEstado> ids = reqEstaRep.findAllByStatusId(4);
			List<Requerimiento> requerimientos = new ArrayList<>();
			for (RequerimientoEstado id : ids) {
				Requerimiento req = requeRepo.findById(id.getRequirementId()).get();
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
					UsuarioFirmante uf = ufRepo.findById(requerimiento.getReceptionUserId()).get();
					requeRes.setReceptionUser(getName(uf.getPrefix(), uf.getName(), uf.getLastname(), uf.getSuffix()));
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
	public List<RequerimientoResponseLista> save(ResultadoRequest requerimientoRequest) {
		try {
			Calendar fechaActual = Calendar.getInstance();
			Requerimiento requerimiento = new Requerimiento();
			if (requerimientoRequest.getId() != null) {
				requerimiento = requeRepo.findById(requerimientoRequest.getId()).get();
				requerimiento.setModifiedBy(requerimientoRequest.getReportByUserId());
				requerimiento.setModifiedAt(fechaActual);
				requerimiento.setReportDate(stringToCalendar(requerimientoRequest.getReportDate()));
				requerimiento.setReportResults(requerimientoRequest.getReportResults());
				requerimiento.setObservationsReport(requerimientoRequest.getObservationsReport());
				requeRepo.save(requerimiento);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findAll();
	}

	@Override
	public ResultadoResponseEditar findById(Integer requerimientoId) {
		ResultadoResponseEditar reqResEdit = new ResultadoResponseEditar();
		List<ResultadoDetallesResponseEditar> reqResDetEdit = new ArrayList<>();
		ResultadoDetallesResponseEditar reqDetEdit;
		Requerimiento req = requeRepo.findById(requerimientoId).get();
		List<RequerimientoDetalle> reqDets = requeDetRepo.findAllByRequirementId(requerimientoId);
		reqResEdit.setId(req.getId());
		reqResEdit.setReportDate(req.getReportDate());
		reqResEdit.setAreaProjectId(req.getAreaProjectId());
		ProyectoArea pa = proyectoRepo.findById(req.getAreaProjectId()).get();
		reqResEdit.setAreaProject(pa.getName());
		reqResEdit.setAnalysisId(req.getAnalysisId());
		Analisis an = anaRepo.findById(req.getAnalysisId()).get();
		reqResEdit.setAnalysis(an.getName());
		reqResEdit.setIsSequenced(req.getIsSequenced());
		TipoMuestra tm = tmRepo.findById(req.getTypeSampleId()).get();
		reqResEdit.setTypeSample(tm.getName());
		reqResEdit.setTypeSampleId(req.getTypeSampleId());
		reqResEdit.setNumberSamples(req.getNumberSamples());
		reqResEdit.setNumberProcessedSamples(req.getNumberProcessedSamples());
		reqResEdit.setObservationsReport(req.getObservationsReport());
		reqResEdit.setReportResults(req.getReportResults());

		for (RequerimientoDetalle reqDet : reqDets) {
			reqDetEdit = new ResultadoDetallesResponseEditar();
			reqDetEdit.setId(reqDet.getId());
			reqDetEdit.setPlaceCode(reqDet.getPlaceCode());
			try {
				reqDetEdit.setCollectionDate(calendarToString(reqDet.getCollectionDate()));
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
			if (reqDet.getIsFasta() != null)
				reqDetEdit.setIsFasta(reqDet.getIsFasta() ? "Si" : "No");
			reqDetEdit.setQuality(reqDet.getQuality());
			reqDetEdit.setIdentity(reqDet.getIdentity());
			reqDetEdit.setOrganism(reqDet.getOrganism());
			reqResDetEdit.add(reqDetEdit);
		}
		reqResEdit.setDetails(reqResDetEdit);
		return reqResEdit;
	}

	@Override
	public String findVoucherById(Integer requerimientoId) {
		try {
			DocumentosEvidencia doc = docRepo.findByRequirementIdAndDocumentTypeId(requerimientoId, 2);
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
							ResourceUtils.getFile("classpath:reports/reporte_resultados.jrxml").getAbsolutePath()),
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
		DocumentosEvidencia doc = docRepo.findByRequirementIdAndDocumentTypeId(requerimiento.getId(), 2);
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
			doc.setDocumentTypeId(2);
			doc.setCreatedAt(Calendar.getInstance());
			doc.setCreatedBy(requerimiento.getUserId());
			docRepo.save(doc);
			return findAll();
		}
	}

}
