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
import ec.org.inspi.cirev.payload.response.AprobacionResponseEditar;
import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.response.SecuenciacionResponseEditar;
import ec.org.inspi.cirev.services.AprobacionesService;
import ec.org.inspi.cirev.services.ProcesamientosService;
import ec.org.inspi.cirev.services.SecuenciacionesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/aprobaciones")
public class AprobacionesController {
	@Autowired
	private AprobacionesService aproServ;
		
	@GetMapping(value = "/all/{userId}")
	public List<RequerimientoResponseLista> listAll(@PathVariable Integer userId) {
		return aproServ.findAll(userId);
	}
	
	@GetMapping(value = "/findById/{secuenciacionId}")
	public AprobacionResponseEditar findById(@PathVariable Integer secuenciacionId) {
		return aproServ.findById(secuenciacionId);
	}

}
