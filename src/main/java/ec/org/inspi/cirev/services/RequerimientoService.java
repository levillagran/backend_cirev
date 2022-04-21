package ec.org.inspi.cirev.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ec.org.inspi.cirev.payload.response.RequerimientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.request.RequerimientoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;

public interface RequerimientoService {
	public List<RequerimientoResponseLista> findAll();
	public List<RequerimientoResponseLista> save(RequerimientoRequest requerimiento);
	public RequerimientoResponseEditar findById(Integer requerimientoId);
	public String findVoucherById(Integer requerimientoId);
	public String createVoucher(Integer requerimientoId);
	public List<RequerimientoResponseLista> saveComprobante(UploadRequest requerimiento);
}
