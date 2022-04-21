package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.UsuarioFirmante;

@RepositoryRestResource(path="requerimientos")
public interface UsuarioFirmanteRepository extends PagingAndSortingRepository<UsuarioFirmante, Integer>{
	List<UsuarioFirmante> findAllByIsInternal(boolean isInternal);
}
