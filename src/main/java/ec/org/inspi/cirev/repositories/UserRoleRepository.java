package ec.org.inspi.cirev.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.org.inspi.cirev.models.UserRole;

@RepositoryRestResource(path="users_roles")
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer>{
	List<UserRole> findByRoleId(Integer rolId);
	List<UserRole> findByUserId(Integer rolId);
	UserRole findFirstByRoleId(Integer rolId);
	UserRole findFirstByOrderByIdDesc();
}
