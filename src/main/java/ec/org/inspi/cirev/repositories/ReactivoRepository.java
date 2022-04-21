package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.Reactivo;

@RepositoryRestResource(path="requerimientos")
public interface ReactivoRepository extends PagingAndSortingRepository<Reactivo, Integer>{

}
