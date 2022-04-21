package ec.org.inspi.cirev.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import ec.org.inspi.cirev.models.Menu;

public interface MenuRepository extends PagingAndSortingRepository<Menu, Integer>{
	Menu findFirstByPermissionIdAndIdAndVisibleTrue(Integer permisoId, Integer id);
}
