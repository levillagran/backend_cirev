package ec.org.inspi.cirev.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.Almacenamiento;
import ec.org.inspi.cirev.models.Analisis;
import ec.org.inspi.cirev.models.AnalisisEspecificacion;
import ec.org.inspi.cirev.models.Canton;
import ec.org.inspi.cirev.models.CantonParroquia;
import ec.org.inspi.cirev.models.Especificacion;
import ec.org.inspi.cirev.models.EspecificacionTecnica;
import ec.org.inspi.cirev.models.Genero;
import ec.org.inspi.cirev.models.Parroquia;
import ec.org.inspi.cirev.models.Provincia;
import ec.org.inspi.cirev.models.ProvinciaCanton;
import ec.org.inspi.cirev.models.ProyectoAlmacenamiento;
import ec.org.inspi.cirev.models.ProyectoAnalisis;
import ec.org.inspi.cirev.models.ProyectoArea;
import ec.org.inspi.cirev.models.Reactivo;
import ec.org.inspi.cirev.models.Taxonomia;
import ec.org.inspi.cirev.models.Tecnica;
import ec.org.inspi.cirev.models.TecnicaReactivo;
import ec.org.inspi.cirev.models.TipoMuestra;
import ec.org.inspi.cirev.models.UsuarioFirmante;
import ec.org.inspi.cirev.repositories.AlmacenamientoRepository;
import ec.org.inspi.cirev.repositories.AnalisisEspecificacionRepository;
import ec.org.inspi.cirev.repositories.AnalisisRepository;
import ec.org.inspi.cirev.repositories.CantonParroquiaRepository;
import ec.org.inspi.cirev.repositories.CantonRepository;
import ec.org.inspi.cirev.repositories.EspecificacionRepository;
import ec.org.inspi.cirev.repositories.EspecificacionTecnicaRepository;
import ec.org.inspi.cirev.repositories.GeneroRepository;
import ec.org.inspi.cirev.repositories.ParroquiaRepository;
import ec.org.inspi.cirev.repositories.ProvinciaCantonRepository;
import ec.org.inspi.cirev.repositories.ProvinciaRepository;
import ec.org.inspi.cirev.repositories.ProyectoAlmacenamientoRepository;
import ec.org.inspi.cirev.repositories.ProyectoAnalisisRepository;
import ec.org.inspi.cirev.repositories.ProyectoAreaRepository;
import ec.org.inspi.cirev.repositories.ReactivoRepository;
import ec.org.inspi.cirev.repositories.TaxonomiaRepository;
import ec.org.inspi.cirev.repositories.TecnicaReactivoRepository;
import ec.org.inspi.cirev.repositories.TecnicaRepository;
import ec.org.inspi.cirev.repositories.TipoMuestraRepository;
import ec.org.inspi.cirev.repositories.UsuarioFirmanteRepository;
import ec.org.inspi.cirev.services.CatalogoService;

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	private ProyectoAreaRepository proyectoAreaRepository;
	@Autowired
	private UsuarioFirmanteRepository usuarioFirmanteRepository;
	@Autowired
	private AnalisisRepository analisisRepository;
	@Autowired
	private AnalisisEspecificacionRepository anaEspeRepository;
	@Autowired
	private ProyectoAnalisisRepository proyectoAnalisisRepository;
	@Autowired
	private EspecificacionRepository EespeRepository;
	@Autowired
	private TipoMuestraRepository tipoMuestraRepository;
	@Autowired
	private TaxonomiaRepository taxoRepository;
	@Autowired
	private GeneroRepository generoRepository;
	@Autowired
	private AlmacenamientoRepository almaRepository;
	@Autowired
	private ProyectoAlmacenamientoRepository proAlmaRepository;	
	@Autowired
	private ProvinciaRepository proRepo;
	@Autowired
	private CantonRepository cantonRepo;
	@Autowired
	private ProvinciaCantonRepository proCanRepo;
	@Autowired
	private ParroquiaRepository parRepo;
	@Autowired
	private CantonParroquiaRepository canParrRepo;
	@Autowired
	private EspecificacionTecnicaRepository esTecRepo;
	@Autowired
	private TecnicaRepository tecRepo;
	@Autowired
	private TecnicaReactivoRepository tecReaRepo;
	@Autowired
	private ReactivoRepository reacRepo;
	
	@Override
	public List<ProyectoArea> findProjectsAll() {
		return (List<ProyectoArea>) proyectoAreaRepository.findAll();
	}

	@Override
	public List<UsuarioFirmante> findSigningUsersAll(boolean isInternal) {
		return usuarioFirmanteRepository.findAllByIsInternal(isInternal);
	}

	@Override
	public List<Analisis> findAnalisisAll(Integer projectId) {
		List<Analisis> analisis = new ArrayList<>();
		List<ProyectoAnalisis> listProAna = proyectoAnalisisRepository.findAllByproyectoId(projectId);
		for (ProyectoAnalisis proAna : listProAna) {
			analisis.add(analisisRepository.findById(proAna.getAnalisisId()).get());
		}
		return analisis;
	}

	@Override
	public List<Especificacion> findEspecificacionAll(Integer analisisId) {
		List<Especificacion> anaEspes = new ArrayList<>();
		List<AnalisisEspecificacion> listProAna = anaEspeRepository.findAllByanalysisId(analisisId);
		for (AnalisisEspecificacion anaEspe : listProAna) {
			anaEspes.add(EespeRepository.findById(anaEspe.getSpecificationId()).get());
		}
		return anaEspes;
	}

	@Override
	public List<TipoMuestra> findTypeSampleAll() {
		return (List<TipoMuestra>) tipoMuestraRepository.findAll();
	}

	@Override
	public List<Taxonomia> findTaxonomyAll() {
		return (List<Taxonomia>) taxoRepository.findAll();
	}

	@Override
	public List<Genero> findGenderAll() {
		return (List<Genero>) generoRepository.findAll();
	}

	@Override
	public Almacenamiento findStorage(Integer projectId) {
		ProyectoAlmacenamiento proAlma = proAlmaRepository.findFirstByProyectoId(projectId);
		return almaRepository.findById(proAlma.getStorageId()).get();
	}
	
	@Override
	public List<Provincia> findProvinceAll() {
		return (List<Provincia>) proRepo.findAll();
	}

	@Override
	public List<Canton> findCantonAll(Integer projectId) {
		List<ProvinciaCanton> proCans = proCanRepo.findAllByProvinceId(projectId);
		List<Canton> canton = new ArrayList<>();
		for (ProvinciaCanton proCan : proCans) {
			canton.add(cantonRepo.findById(proCan.getCantonId()).get());
		}
		return canton;
	}
	
	@Override
	public List<Parroquia> findParishAll(Integer cantontId) {
		List<CantonParroquia> proParrs = canParrRepo.findAllByCantonId(cantontId);
		List<Parroquia> parr = new ArrayList<>();
		for (CantonParroquia proParr : proParrs) {
			parr.add(parRepo.findById(proParr.getParishId()).get());
		}
		return parr;
	}
	
	@Override
	public List<Tecnica> findTecnicasAll(Integer especificacionId) {
		List<EspecificacionTecnica> espTecs = esTecRepo.findAllBySpecificationId(especificacionId);
		List<Tecnica> tecnicas = new ArrayList<>();
		for (EspecificacionTecnica espTec : espTecs) {
			tecnicas.add(tecRepo.findById(espTec.getTechniqueId()).get());
		}
		return tecnicas;
	}
	
	@Override
	public List<Reactivo> findKitsAll(Integer technicaId) {
		List<TecnicaReactivo> tecReacs = tecReaRepo.findAllByTechniquesId(technicaId);
		List<Reactivo> reacs = new ArrayList<>();
		for (TecnicaReactivo tecReac : tecReacs) {
			reacs.add(reacRepo.findById(tecReac.getTechniquesId()).get());
		}
		return reacs;
	}

}
