package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import ec.org.inspi.cirev.models.ModuloMenu;

public interface ModuloMenuRepository extends PagingAndSortingRepository<ModuloMenu, Integer>{
	List<ModuloMenu> findAllByModuleIdAndActiveTrue(Integer moduloId);
}
