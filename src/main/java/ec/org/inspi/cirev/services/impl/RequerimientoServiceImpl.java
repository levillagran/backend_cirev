package ec.org.inspi.cirev.services.impl;

import java.nio.charset.StandardCharsets;
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
import ec.org.inspi.cirev.models.Estado;
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
import ec.org.inspi.cirev.payload.request.RequerimientoDetallesRequest;
import ec.org.inspi.cirev.payload.request.RequerimientoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;
import ec.org.inspi.cirev.payload.response.RequerimientoDetallesResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.repositories.AlmacenamientoRepository;
import ec.org.inspi.cirev.repositories.AnalisisRepository;
import ec.org.inspi.cirev.repositories.CantonRepository;
import ec.org.inspi.cirev.repositories.DocumentoEvidenciaRepository;
import ec.org.inspi.cirev.repositories.EspecificacionRepository;
import ec.org.inspi.cirev.repositories.EstadoRepository;
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

@Service("requerimientoService")
public class RequerimientoServiceImpl implements RequerimientoService {

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
	private RequerimientoEstadoRepository reqEstaRep;
	@Autowired
	private EstadoRepository estaRep;

	@Override
	public List<RequerimientoResponseLista> findAll() {
		try {
			List<Requerimiento> requerimientos = (List<Requerimiento>) requeRepo.findAll();
			List<RequerimientoResponseLista> requeResL = new ArrayList<>();
			RequerimientoResponseLista requeRes;
			for (Requerimiento requerimiento : requerimientos) {
				requeRes = new RequerimientoResponseLista();
				requeRes.setId(requerimiento.getId());
				RequerimientoEstado reqEs = reqEstaRep.findByRequirementId(requerimiento.getId());
				Estado est = estaRep.findById(reqEs.getStatusId()).get();
				requeRes.setStatus(est.getName());
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
			nombre = pre + " " + name + " " + lastname + suf;
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
	public List<RequerimientoResponseLista> save(RequerimientoRequest requerimientoRequest) {
		try {
			Calendar fechaActual = Calendar.getInstance();
			Requerimiento requerimiento = new Requerimiento();
			if (requerimientoRequest.getId() != null) {
				requerimiento = requeRepo.findById(requerimientoRequest.getId()).get();
				requerimiento.setModifiedBy(requerimientoRequest.getReceptionUserId());
				requerimiento.setModifiedAt(fechaActual);
			} else {
				Requerimiento reqId = requeRepo.findFirstByOrderByIdDesc();
				if (reqId != null)
					requerimiento.setId(reqId.getId() + 1);
				else
					requerimiento.setId(1);
				Requerimiento number = requeRepo.findFirstByOrderByNumberDesc();
				if (number != null) {
					requerimiento.setNumber(number.getNumber() + 1);
					requerimiento.setCode(
							"LAM-" + fechaActual.get(Calendar.YEAR) + "-" + String.format("%03d", (reqId.getId() + 1)));
				} else {
					requerimiento.setNumber(1);
					requerimiento.setCode("LAM-" + fechaActual.get(Calendar.YEAR) + "-001");
				}
				RequerimientoEstado reqEstado = reqEstaRep.findFirstByOrderByIdDesc();
				reqEstado = reqEstaRep.save(new RequerimientoEstado((reqEstado.getId() + 1), requerimiento.getId(), 1 ));
				requerimiento.setCreatedBy(requerimientoRequest.getReceptionUserId());
				requerimiento.setCreatedAt(fechaActual);
			}
			requerimiento.setEntryDate(stringToCalendar(requerimientoRequest.getEntryDate()));
			requerimiento.setAreaProjectId(requerimientoRequest.getAreaProjectId());
			requerimiento.setAnalysisId(requerimientoRequest.getAnalysisId());
			requerimiento.setIsSequenced(requerimientoRequest.getIsSequenced());
			requerimiento.setTypeSampleId(requerimientoRequest.getTypeSampleId());
			requerimiento.setTypeSampleId(requerimientoRequest.getTypeSampleId());
			requerimiento.setNumberSamples(requerimientoRequest.getDetails().size());
			requerimiento.setSpecificationId(requerimientoRequest.getSpecificationId());
			requerimiento.setObservationRequirement(requerimientoRequest.getObservationRequirement());
			requerimiento.setObservationEntry(requerimientoRequest.getObservationEntry());
			requerimiento.setRequerimentUserId(requerimientoRequest.getRequerimentUserId());			
			requerimiento.setReceptionUserId(requerimientoRequest.getReceptionUserId());
			int nA = 0;
			for (RequerimientoDetallesRequest detalleReq : requerimientoRequest.getDetails()) {
				if (detalleReq.getIsAccepted().equals("true")) {
					nA = nA + 1;
				}
			}
			requerimiento.setNumberAcceptedSamples(nA);
			requerimiento.setNumberProcessedSamples(nA);
			requerimiento.setNumberUnacceptedSamples(requerimiento.getNumberSamples() - nA);
			requerimiento = requeRepo.save(requerimiento);

			String codeTipe = tmRepo.findFirstById(requerimiento.getTypeSampleId()).getCode();
			for (RequerimientoDetallesRequest detalleReq : requerimientoRequest.getDetails()) {
				RequerimientoDetalle detalle = new RequerimientoDetalle();
				if (detalleReq.getPlaceCode().length() > 0 && detalleReq.getCollectionDate().length() > 0) {
					if (detalleReq.getId() != null) {
						detalle = requeDetRepo.findById(detalleReq.getId()).get();
						detalle.setModifiedBy(requerimientoRequest.getReceptionUserId());
						detalle.setModifiedAt(fechaActual);
					} else {
						RequerimientoDetalle reqDetId = requeDetRepo.findFirstByOrderByIdDesc();
						if (reqDetId != null)
							detalle.setId(reqDetId.getId() + 1);
						else
							detalle.setId(1);
						detalle.setRequirementId(requerimiento.getId());
						RequerimientoDetalle serial = requeDetRepo.findFirstByOrderBySerialDesc();
						if (serial != null)
							detalle.setSerial(serial.getSerial() + 1);
						else
							detalle.setSerial(1);
						detalle.setCode("MOL-" + fechaActual.get(Calendar.YEAR) + "-" + codeTipe + "-"
								+ String.format("%04d", detalle.getSerial()));
						detalle.setCreatedBy(requerimientoRequest.getReceptionUserId());
						detalle.setCreatedAt(fechaActual);
					}
					detalle.setPlaceCode(detalleReq.getPlaceCode());
					detalle.setCollectionDate(stringToCalendar(detalleReq.getCollectionDate()));
					detalle.setTaxonomicId(detalleReq.getTaxonomicId());
					detalle.setGenderId(detalleReq.getGenderId());
					detalle.setProvinceId(detalleReq.getProvinceId());
					detalle.setCantonId(detalleReq.getCantonId());
					detalle.setParishId(detalleReq.getParishId());
					detalle.setLatitude(detalleReq.getLatitude());
					detalle.setLongitude(detalleReq.getLongitude());
					detalle.setPreprocessed(detalleReq.getIsPreprocessed().equals("true")? true: false);
					detalle.setPlaceCode(detalleReq.getPlaceCode());
					detalle.setAccepted(detalleReq.getIsAccepted().equals("true")? true: false);
					detalle.setReasonUnacceptedSamples(detalleReq.getReazonNoAccepted());
					detalle.setStorageId(detalleReq.getStorageId());
					detalle.setNumberBox(detalleReq.getNumberBox());
					detalle.setYearCode(detalleReq.getYearCode());
					detalle.setObservationSampleDetail(detalleReq.getObservationSampleDetail());
					
					requeDetRepo.save(detalle);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return findAll();
	}

	@Override
	public RequerimientoResponseEditar findById(Integer requerimientoId) {
		try {
			RequerimientoResponseEditar reqResEdit = new RequerimientoResponseEditar();
			List<RequerimientoDetallesResponseEditar> reqResDetEdit = new ArrayList<>();
			RequerimientoDetallesResponseEditar reqDetEdit;
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
			TipoMuestra tm = tmRepo.findById(req.getTypeSampleId()).get();
			reqResEdit.setTypeSample(tm.getName());
			reqResEdit.setTypeSampleId(req.getTypeSampleId());
			reqResEdit.setObservationRequirement(req.getObservationRequirement());
			reqResEdit.setObservationEntry(req.getObservationEntry());
			reqResEdit.setRequerimentUserId(req.getRequerimentUserId());
			if (req.getRequerimentUserId() != null) {
				UsuarioFirmante uf = ufRepo.findById(req.getRequerimentUserId()).get();
				reqResEdit.setRequerimentUser(getName(uf.getPrefix(), uf.getName(), uf.getLastname(), uf.getSuffix()));
			}
			reqResEdit.setReceptionUserId(req.getReceptionUserId());
			if (req.getReceptionUserId() != null) {
				UsuarioFirmante uf = ufRepo.findById(req.getReceptionUserId()).get();
				reqResEdit.setReceptionUser(getName(uf.getPrefix(), uf.getName(), uf.getLastname(), uf.getSuffix()));
			}
			for (RequerimientoDetalle reqDet : reqDets) {
				reqDetEdit = new RequerimientoDetallesResponseEditar();
				reqDetEdit.setId(reqDet.getId());
				reqDetEdit.setPlaceCode(reqDet.getPlaceCode());
				reqDetEdit.setCollectionDate(calendarToString(reqDet.getCollectionDate()));
				if (reqDet.getTaxonomicId() != null) {
					reqDetEdit.setTaxonomicId(reqDet.getTaxonomicId());
					Taxonomia tax = taxRepo.findById(reqDet.getTaxonomicId()).get();
					reqDetEdit.setTaxonomic(tax.getName());
				}
				if (reqDet.getProvinceId() != null) {
					reqDetEdit.setProvinceId(reqDet.getProvinceId());
					Provincia prov = provRepo.findById(reqDet.getProvinceId()).get();
					reqDetEdit.setProvince(prov.getName());
				}
				if (reqDet.getCantonId() != null) {
					reqDetEdit.setCantonId(reqDet.getCantonId());
					Canton cant = cantRepo.findById(reqDet.getCantonId()).get();
					reqDetEdit.setCanton(cant.getName());
				}
				if (reqDet.getParishId() != null) {
					reqDetEdit.setParishId(reqDet.getParishId());
					Parroquia parr = prarrRepo.findById(reqDet.getParishId()).get();
					reqDetEdit.setParish(parr.getName());
				}
				reqDetEdit.setLatitude(reqDet.getLatitude());
				reqDetEdit.setLongitude(reqDet.getLongitude());
				if (reqDet.getGenderId() != null) {
					reqDetEdit.setGenderId(reqDet.getGenderId());
					Genero gen = genRepo.findById(reqDet.getGenderId()).get();
					reqDetEdit.setGender(gen.getName());
				}
				if (reqDet.isPreprocessed())
					reqDetEdit.setIsPreprocessed("Si");
				else
					reqDetEdit.setIsPreprocessed("No");
				if (reqDet.isAccepted())
					reqDetEdit.setIsAccepted("Si");
				else
					reqDetEdit.setIsAccepted("No");
				reqDetEdit.setPreprocessedId(reqDet.isPreprocessed());
				reqDetEdit.setAcceptedId(reqDet.isAccepted());
				reqDetEdit.setReazonNoAccepted(reqDet.getReasonUnacceptedSamples());
				if (reqDet.getStorageId() != null) {
					reqDetEdit.setStorageId(reqDet.getStorageId());
					Almacenamiento al = alRepo.findById(reqDet.getStorageId()).get();
					if (al.getText01().length() > 0 && al.getText02().length() > 0 && al.getText03() == null) {
						reqDetEdit.setStorage(al.getText01() + " " + reqDet.getNumberBox() + al.getText02());
					} else if (al.getText01().length() > 0 && al.getText02().length() > 0 && al.getText03().length() > 0) {
						reqDetEdit.setStorage(al.getText01() + " " + reqDet.getNumberBox() + al.getText02() + " "
								+ reqDet.getYearCode() + al.getText03());
					}
				}
				reqDetEdit.setBox(reqDet.getNumberBox());
				reqDetEdit.setYear(reqDet.getYearCode());
				reqDetEdit.setObservations(reqDet.getObservationSampleDetail());
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
							ResourceUtils.getFile("classpath:reports/reporte_registro.jrxml").getAbsolutePath()),
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
