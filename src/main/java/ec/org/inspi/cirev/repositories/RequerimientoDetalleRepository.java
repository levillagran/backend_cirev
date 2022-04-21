package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.RequerimientoDetalle;

@RepositoryRestResource(path="requerimientos")
public interface RequerimientoDetalleRepository extends PagingAndSortingRepository<RequerimientoDetalle, Integer>{
	RequerimientoDetalle findFirstByOrderByIdDesc();
	RequerimientoDetalle findFirstByOrderBySerialDesc();
	List<RequerimientoDetalle> findAllByRequirementId(Integer requerimientoId);
}
