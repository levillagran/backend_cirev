package ec.org.inspi.cirev.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.Menu;
import ec.org.inspi.cirev.models.Modulo;
import ec.org.inspi.cirev.models.ModuloMenu;
import ec.org.inspi.cirev.models.Role;
import ec.org.inspi.cirev.models.RolePermiso;
import ec.org.inspi.cirev.payload.request.MenuRequest;
import ec.org.inspi.cirev.repositories.MenuRepository;
import ec.org.inspi.cirev.repositories.ModuloMenuRepository;
import ec.org.inspi.cirev.repositories.ModuloRepository;
import ec.org.inspi.cirev.repositories.RolePermisoRepository;
import ec.org.inspi.cirev.repositories.RoleRepository;
import ec.org.inspi.cirev.services.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private RolePermisoRepository rolePermisoRepo;
	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private ModuloMenuRepository moduloMenuRepository;
	@Autowired
	private ModuloRepository moduloRepository;

	@Override
	public List<Menu> findMenu(MenuRequest menuRequest) {
		List<Menu> listMenu = new ArrayList<>();
		Modulo modulo = moduloRepository.findFirstByCodeAndActiveTrue(menuRequest.getModule());
		List<ModuloMenu> moduloMenus = moduloMenuRepository.findAllByModuleIdAndActiveTrue(modulo.getId());
		
		Role role = roleRepo.findFirstByCode(menuRequest.getCodeRole());
		List<RolePermiso> rolPremisos = rolePermisoRepo.findByRoleId(role.getId());
		
		for (ModuloMenu moduloMenu : moduloMenus) {
			for (RolePermiso rolPremiso : rolPremisos) {
				if((menuRepo.findFirstByPermissionIdAndIdAndVisibleTrue(rolPremiso.getPermissionId(), moduloMenu.getMenuId())) != null) {
					listMenu.add(menuRepo.findFirstByPermissionIdAndIdAndVisibleTrue(rolPremiso.getPermissionId(), moduloMenu.getMenuId()));
				}
			}
		}
		
		return listMenu;
	}

	

}
