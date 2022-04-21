package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.Provincia;

@RepositoryRestResource(path="provincias")
public interface ProvinciaRepository extends PagingAndSortingRepository<Provincia, Integer>{
	
}
