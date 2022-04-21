package ec.org.inspi.cirev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.inspi.cirev.models.Menu;
import ec.org.inspi.cirev.payload.request.MenuRequest;
import ec.org.inspi.cirev.services.MenuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MenusController {

	@Autowired
	MenuService menuService;
	
	@PostMapping(value = "/menu")
	public List<Menu> listMenu(@RequestBody MenuRequest menuRequest) {
		return menuService.findMenu(menuRequest);
	}

}
