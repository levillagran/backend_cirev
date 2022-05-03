package ec.org.inspi.cirev.services;

import java.util.List;


import ec.org.inspi.cirev.payload.response.ProcesamientoResponseEditar;
import ec.org.inspi.cirev.payload.response.RequerimientoResponseLista;
import ec.org.inspi.cirev.payload.response.SecuenciacionResponseEditar;
import ec.org.inspi.cirev.payload.request.ProcesamientoRequest;
import ec.org.inspi.cirev.payload.request.SecuenciacionRequest;
import ec.org.inspi.cirev.payload.request.UploadRequest;

public interface SecuenciacionesService {
	public List<RequerimientoResponseLista> findAll();
	public List<RequerimientoResponseLista> save(SecuenciacionRequest requerimiento);
	public SecuenciacionResponseEditar findById(Integer requerimientoId);
	public String findVoucherById(Integer requerimientoId);
	public String createVoucher(Integer requerimientoId);
	public List<RequerimientoResponseLista> saveComprobante(UploadRequest requerimiento);
}
