package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.AnalisisEspecificacion;

@RepositoryRestResource(path="requerimientos")
public interface AnalisisEspecificacionRepository extends PagingAndSortingRepository<AnalisisEspecificacion, Integer>{
	List<AnalisisEspecificacion> findAllByanalysisId(Integer analisisId);
}
