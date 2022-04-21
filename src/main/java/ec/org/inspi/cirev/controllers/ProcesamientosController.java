package ec.org.inspi.cirev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.inspi.cirev.payload.request.ProcesamientoRequest;
import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.services.ProcesamientosService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/procesamientos")
public class ProcesamientosController {
	@Autowired
	private ProcesamientosService procesamientosService;
		
	@GetMapping(value = "/all")
	public List<RequerimientoResponseLista> listAll() {
		return procesamientosService.findAll();
	}
	
	@PostMapping(value = "/save")
	public List<RequerimientoResponseLista> save(@RequestBody ProcesamientoRequest requerimiento) {
		return procesamientosService.save(requerimiento);
	}
	
	@GetMapping(value = "/findById/{requerimientoId}")
	public ProcesamientoResponseEditar findById(@PathVariable Integer requerimientoId) {
		return procesamientosService.findById(requerimientoId);
	}
	
}
