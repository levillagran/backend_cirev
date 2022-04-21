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
@Table(name = "requirements_details")
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
	@Column(name = "processing_results")
	private String processingResults;
	@Column(name = "observation_results")
	private String observationResults;
	@Column(name = "primer")
	private String primer;
	@Column(name = "sequence")
	private String sequence;
	@Column(name = "concentration")
	private String concentration;
	@Column(name = "is_fasta")
	private Boolean isFasta;
	@Column(name = "quantity")
	private Integer quantity;
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
