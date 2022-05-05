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
import ec.org.inspi.cirev.payload.request.SecuenciacionRequest;
import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.response.SecuenciacionResponseEditar;
import ec.org.inspi.cirev.services.ProcesamientosService;
import ec.org.inspi.cirev.services.SecuenciacionesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/secuenciaciones")
public class SecuenciacionesController {
	@Autowired
	private SecuenciacionesService secuenciacionesService;
		
	@GetMapping(value = "/all")
	public List<RequerimientoResponseLista> listAll() {
		return secuenciacionesService.findAll();
	}
	
	@PostMapping(value = "/save")
	public List<RequerimientoResponseLista> save(@RequestBody SecuenciacionRequest requerimiento) {
		return secuenciacionesService.save(requerimiento);
	}
	
	@GetMapping(value = "/findById/{secuenciacionId}")
	public SecuenciacionResponseEditar findById(@PathVariable Integer secuenciacionId) {
		return secuenciacionesService.findById(secuenciacionId);
	}
	
	@GetMapping(value = "/comprobanteCreate/{requerimientoId}")
	public String createVoucher(@PathVariable Integer requerimientoId) {
		return secuenciacionesService.createVoucher(requerimientoId);
	}
	
}
