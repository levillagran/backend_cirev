package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.TipoMuestra;

@RepositoryRestResource(path="requerimientos")
public interface TipoMuestraRepository extends PagingAndSortingRepository<TipoMuestra, Integer>{
	TipoMuestra findFirstById(Integer id);
}
