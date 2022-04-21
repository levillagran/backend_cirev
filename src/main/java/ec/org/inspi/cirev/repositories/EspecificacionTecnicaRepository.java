package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.EspecificacionTecnica;

@RepositoryRestResource(path="especificacionTecnica")
public interface EspecificacionTecnicaRepository extends PagingAndSortingRepository<EspecificacionTecnica, Integer>{
	List<EspecificacionTecnica> findAllBySpecificationId(Integer especificacionId);
}
