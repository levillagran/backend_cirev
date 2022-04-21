package ec.org.inspi.cirev.services;

import java.util.List;

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

public interface CatalogoService {
	public List<ProyectoArea> findProjectsAll();
	public List<UsuarioFirmante> findSigningUsersAll(boolean isInternal);
	public List<Analisis> findAnalisisAll(Integer projectId);
	public List<Especificacion> findEspecificacionAll(Integer analisisId);
	public List<TipoMuestra> findTypeSampleAll();
	public List<Taxonomia> findTaxonomyAll();
	public List<Genero> findGenderAll();
	public Almacenamiento findStorage(Integer projectId);
	public List<Provincia> findProvinceAll();
	public List<Canton> findCantonAll(Integer provinciaId);
	public List<Parroquia> findParishAll(Integer cantontId);
	public List<Tecnica> findTecnicasAll(Integer especificacionId);
	public List<Reactivo> findKitsAll(Integer technicaId);
}
