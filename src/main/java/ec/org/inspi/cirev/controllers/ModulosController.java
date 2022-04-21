package ec.org.inspi.cirev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.inspi.cirev.models.Modulo;
import ec.org.inspi.cirev.repositories.ModuloRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modulos")
public class ModulosController {
	
	@Autowired
	ModuloRepository moduloRepository;
	
	@GetMapping(value = "/list")
	public List<Modulo> listAll() {
		return moduloRepository.findAllByActive(true);
	}

}
