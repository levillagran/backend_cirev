package ec.org.inspi.cirev.services;

import java.util.List;


import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.request.ProcesamientoRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;

public interface ProcesamientosService {
	public List<RequerimientoResponseLista> findAll();
	public List<RequerimientoResponseLista> save(ProcesamientoRequest requerimiento);
	public ProcesamientoResponseEditar findById(Integer requerimientoId);
	public String findVoucherById(Integer requerimientoId);
	public String createVoucher(Integer requerimientoId);
	public List<RequerimientoResponseLista> saveComprobante(UploadRequest requerimiento);
}
