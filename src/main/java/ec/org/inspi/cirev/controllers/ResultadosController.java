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

import ec.org.inspi.cirev.payload.request.RequerimientoRequest;
import ec.org.inspi.cirev.payload.request.ResultadoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.response.ResultadoResponseEditar;
import ec.org.inspi.cirev.services.RequerimientoService;
import ec.org.inspi.cirev.services.ResultadoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resultados")
public class ResultadosController {
	@Autowired
	private ResultadoService resultadoService;
		
	@GetMapping(value = "/all")
	public List<RequerimientoResponseLista> listAll() {
		return resultadoService.findAll();
	}
	
	@PostMapping(value = "/save")
	public List<RequerimientoResponseLista> saveUsuario(@RequestBody ResultadoRequest requerimiento) {
		return resultadoService.save(requerimiento);
	}
	
	@GetMapping(value = "/findById/{requerimientoId}")
	public ResultadoResponseEditar findById(@PathVariable Integer requerimientoId) {
		return resultadoService.findById(requerimientoId);
	}

	@GetMapping(value = "/comprobanteView/{requerimientoId}")
	public String findVoucherById(@PathVariable Integer requerimientoId) {
		return resultadoService.findVoucherById(requerimientoId);
	}
	
	@GetMapping(value = "/comprobanteCreate/{requerimientoId}")
	public String createVoucher(@PathVariable Integer requerimientoId) {
		return resultadoService.createVoucher(requerimientoId);
	}
	
	@PostMapping(value = "/comprobanteSave")
	public List<RequerimientoResponseLista> saveComprobante(@RequestBody UploadRequest uploadRequest) {
		System.out.println(uploadRequest.getId());
		return resultadoService.saveComprobante(uploadRequest);
	}
	
}
