package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class RequerimientoDetallesRequest {
	
	private Integer id;
	private String placeCode;
	private String collectionDate;
	private Integer taxonomicId;
	private Integer provinceId;
	private Integer cantonId;
	private Integer parishId;
	private double latitude;
	private double longitude;
	private Integer genderId;
	private String isPreprocessed;
	private String isAccepted;
	private String reazonNoAccepted;
	private Integer storageId;
	private Integer numberBox;
	private Integer yearCode;
	private String observationSampleDetail;
	
}
