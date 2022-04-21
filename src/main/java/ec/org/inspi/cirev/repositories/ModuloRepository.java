package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import ec.org.inspi.cirev.models.Modulo;

public interface ModuloRepository extends PagingAndSortingRepository<Modulo, Integer>{
	List<Modulo> findAllByActive(boolean active);
	Modulo findFirstByCodeAndActiveTrue(String code);
}
