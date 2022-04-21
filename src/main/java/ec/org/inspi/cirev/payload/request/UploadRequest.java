package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class UploadRequest {

	private Integer id;
	private String evidence;
	private Integer userId;

}
