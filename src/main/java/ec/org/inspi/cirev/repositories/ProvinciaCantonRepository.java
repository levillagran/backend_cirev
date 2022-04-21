package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.ProvinciaCanton;

@RepositoryRestResource(path="requerimientos")
public interface ProvinciaCantonRepository extends PagingAndSortingRepository<ProvinciaCanton, Integer>{
	List<ProvinciaCanton> findAllByProvinceId(Integer provinciaId);
}
