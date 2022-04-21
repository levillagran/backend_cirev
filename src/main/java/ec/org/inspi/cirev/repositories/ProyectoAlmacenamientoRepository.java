package ec.org.inspi.cirev.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.ProyectoAlmacenamiento;

@RepositoryRestResource(path="proyectosAlmacenamiento")
public interface ProyectoAlmacenamientoRepository extends PagingAndSortingRepository<ProyectoAlmacenamiento, Integer>{
	ProyectoAlmacenamiento findFirstByProyectoId(Integer projectId);
}
