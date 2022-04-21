package ec.org.inspi.cirev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.inspi.cirev.models.Almacenamiento;
import ec.org.inspi.cirev.models.Analisis;
import ec.org.inspi.cirev.models.Canton;
import ec.org.inspi.cirev.models.Especificacion;
import ec.org.inspi.cirev.models.Genero;
import ec.org.inspi.cirev.models.Parroquia;
import ec.org.inspi.cirev.models.Provincia;
import ec.org.inspi.cirev.models.ProyectoArea;
import ec.org.inspi.cirev.models.Reactivo;
import ec.org.inspi.cirev.models.Taxonomia;
import ec.org.inspi.cirev.models.Tecnica;
import ec.org.inspi.cirev.models.TipoMuestra;
import ec.org.inspi.cirev.models.UsuarioFirmante;
import ec.org.inspi.cirev.services.CatalogoService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/catalogos")
public class CatalogosController {
	
	@Autowired
	CatalogoService catalogoService;
		
	@GetMapping(value = "/proyectos")
	public List<ProyectoArea> listProjectsAll() {
		return catalogoService.findProjectsAll();
	}
	
	@GetMapping(value = "/usuarios/{isInternal}")
	public List<UsuarioFirmante> listSigningUsersAll(@PathVariable boolean isInternal) {
		return catalogoService.findSigningUsersAll(isInternal);
	}
	
	@GetMapping(value = "/analisis/{projectId}")
	public List<Analisis> listAnalisisAll(@PathVariable Integer projectId) {
		return catalogoService.findAnalisisAll(projectId);
	}
	
	@GetMapping(value = "/especificaciones/{analisisId}")
	public List<Especificacion> lisEspecificacionAll(@PathVariable Integer analisisId) {
		return catalogoService.findEspecificacionAll(analisisId);
	}
	
	@GetMapping(value = "/tipoMuestra")
	public List<TipoMuestra> listTypeSampleAll() {
		return catalogoService.findTypeSampleAll();
	}
	
	@GetMapping(value = "/taxonomia")
	public List<Taxonomia> listTaxonomyAll() {
		return catalogoService.findTaxonomyAll();
	}
	
	@GetMapping(value = "/genero")
	public List<Genero> listGenderAll() {
		return catalogoService.findGenderAll();
	}
	
	@GetMapping(value = "/almacen/{projectId}")
	public Almacenamiento storage(@PathVariable Integer projectId) {
		return catalogoService.findStorage(projectId);
	}
	
	@GetMapping(value = "/provincias")
	public List<Provincia> procvinces() {
		return catalogoService.findProvinceAll();
	}
	
	@GetMapping(value = "/cantones/{provinciaId}")
	public List<Canton> cantons(@PathVariable Integer provinciaId) {
		return catalogoService.findCantonAll(provinciaId);
	}
	
	@GetMapping(value = "/parroquias/{cantonId}")
	public List<Parroquia> parishes(@PathVariable Integer cantonId) {
		return catalogoService.findParishAll(cantonId);
	}
	
	@GetMapping(value = "/tecnicas/{especificacionId}")
	public List<Tecnica> tecnics(@PathVariable Integer especificacionId) {
		return catalogoService.findTecnicasAll(especificacionId);
	}
	
	@GetMapping(value = "/kits/{technicaId}")
	public List<Reactivo> kits(@PathVariable Integer technicaId) {
		return catalogoService.findKitsAll(technicaId);
	}

}
