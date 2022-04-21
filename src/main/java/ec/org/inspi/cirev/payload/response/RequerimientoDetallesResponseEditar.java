package ec.org.inspi.cirev.payload.response;

import lombok.Data;

@Data
public class RequerimientoDetallesResponseEditar {

	private Integer id;
	private String placeCode;
	private String collectionDate;
	private Integer taxonomicId;
	private String taxonomic;
	private Integer provinceId;
	private String province;
	private Integer cantonId;
	private String canton;
	private Integer parishId;
	private String parish;
	private double latitude;
	private double longitude;
	private Integer genderId;
	private String gender;
	private String isPreprocessed;
	private String isAccepted;
	private boolean isPreprocessedId;
	private boolean isAcceptedId;
	private String reazonNoAccepted;
	private Integer storageId;
	private String storage;
	private Integer box;
	private Integer year;
	private String observations;
	
}
