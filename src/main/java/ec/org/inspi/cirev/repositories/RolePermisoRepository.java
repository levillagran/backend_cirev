package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.RolePermiso;

@RepositoryRestResource(path="users_roles")
public interface RolePermisoRepository extends PagingAndSortingRepository<RolePermiso, Integer>{
	List<RolePermiso> findByRoleId(Integer roleId);
}
