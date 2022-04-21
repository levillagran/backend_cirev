package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.Requerimiento;

@RepositoryRestResource(path="requerimientos")
public interface RequerimientoRepository extends PagingAndSortingRepository<Requerimiento, Integer>{
	Requerimiento findFirstByOrderByIdDesc();
	Requerimiento findFirstByOrderByNumberDesc();
}
