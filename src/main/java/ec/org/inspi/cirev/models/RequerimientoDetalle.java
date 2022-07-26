/**
 * 
 */
package ec.org.inspi.cirev.models;

/**
 * @author episig := Lenin Villagran
 *
 */
import lombok.Data;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "requirements_details", schema = "molecular")
public class RequerimientoDetalle {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "requirement_id")
	private Integer requirementId;
	@Column(name = "serial")
	private Integer serial;
	@Column(name = "code")
	private String code;
	@Column(name = "place_code")
	private String placeCode;
	@Column(name = "collection_date")
	private Calendar collectionDate;
	@Column(name = "taxonomic_id")
	private Integer taxonomicId;
	@Column(name = "province_id")
	private Integer provinceId;
	@Column(name = "canton_id")
	private Integer cantonId;
	@Column(name = "parish_id")
	private Integer parishId;
	@Column(name = "latitude")
	private double latitude;
	@Column(name = "longitude")
	private double longitude;
	@Column(name = "gender_id")
	private Integer genderId;
	@Column(name = "is_preprocessed")
	private boolean isPreprocessed;
	@Column(name = "is_accepted")
	private boolean isAccepted;
	@Column(name = "reason_unaccepted_sample")
	private String reasonUnacceptedSamples;
	@Column(name = "storage_id")
	private Integer storageId;
	@Column(name = "number_box")
	private Integer numberBox;
	@Column(name = "year_code")
	private Integer yearCode;
	@Column(name = "observation_sample_detail")
	private String observationSampleDetail;
	@Column(name = "result_process_01")
	private String resultProcess01;
	@Column(name = "result_process_02")
	private String resultProcess02;
	@Column(name = "result_process_03")
	private String resultProcess03;
	@Column(name = "result_process_04")
	private String resultProcess04;
	@Column(name = "observation_process_01")
	private String observationProcess01;
	@Column(name = "observation_process_02")
	private String observationProcess02;
	@Column(name = "observation_process_03")
	private String observationProcess03;
	@Column(name = "observation_process_04")
	private String observationProcess04;
	@Column(name = "date_process_01")
	private Calendar dateProcess01;
	@Column(name = "date_process_02")
	private Calendar dateProcess02;
	@Column(name = "date_process_03")
	private Calendar dateProcess03;
	@Column(name = "date_process_04")
	private Calendar dateProcess04;
	@Column(name = "primer")
	private String primer;
	@Column(name = "sequence")
	private String sequence;
	@Column(name = "concentration")
	private String concentration;
	@Column(name = "is_fasta")
	private Boolean isFasta;
	@Column(name = "quality")
	private Integer quality;
	@Column(name = "identity")
	private Integer identity;
	@Column(name = "organism")
	private String organism;
	
	@Column(name = "created_at")
	private Calendar createdAt;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "modified_at")
	private Calendar modifiedAt;
	@Column(name = "modified_by")
	private Integer modifiedBy;
	@Column(name = "deleted_at")
	private Calendar deletedAt;
	@Column(name = "deleted_by")
	private Integer deletedBy;
}
