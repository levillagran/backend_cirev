package ec.org.inspi.cirev.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;

import ec.org.inspi.cirev.models.UserModulo;

public interface UserModuloRepository extends PagingAndSortingRepository<UserModulo, Integer>{
	boolean existsByUserIdAndModuleIdAndActiveTrue(Integer userId, Integer moduleId);
}
