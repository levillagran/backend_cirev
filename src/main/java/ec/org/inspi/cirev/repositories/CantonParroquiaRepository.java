package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.CantonParroquia;

@RepositoryRestResource(path="requerimientos")
public interface CantonParroquiaRepository extends PagingAndSortingRepository<CantonParroquia, Integer>{
	List<CantonParroquia> findAllByCantonId(Integer cantonId);
}
