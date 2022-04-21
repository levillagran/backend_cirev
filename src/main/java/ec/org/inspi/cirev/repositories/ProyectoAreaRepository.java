package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.ProyectoArea;

@RepositoryRestResource(path="proyectos")
public interface ProyectoAreaRepository extends PagingAndSortingRepository<ProyectoArea, Integer>{

}
