package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.TecnicaReactivo;

@RepositoryRestResource(path="tecnicaReactivo")
public interface TecnicaReactivoRepository extends PagingAndSortingRepository<TecnicaReactivo, Integer>{
	List<TecnicaReactivo> findAllByTechniquesId(Integer tecnicaId);
}
