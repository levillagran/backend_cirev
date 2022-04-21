package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.ProyectoAnalisis;

@RepositoryRestResource(path="proyectosAnalisis")
public interface ProyectoAnalisisRepository extends PagingAndSortingRepository<ProyectoAnalisis, Integer>{
	List<ProyectoAnalisis> findAllByproyectoId(Integer projectId);
}
