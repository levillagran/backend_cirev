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
@Table(name = "requirements", schema = "molecular")
public class Requerimiento {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "number")
	private Integer number;
	@Column(name = "code")
	private String code;
	@Column(name = "entry_date")
	private Calendar entryDate;
	@Column(name = "processing_date")
	private Calendar processingDate;
	@Column(name = "report_date")
	private Calendar reportDate;
	@Column(name = "area_project_id")
	private Integer areaProjectId;
	@Column(name = "analysis_id")
	private Integer analysisId;
	@Column(name = "is_sequenced")
	private Boolean isSequenced;
	@Column(name = "type_sample_id")
	private Integer typeSampleId;
	@Column(name = "number_samples")
	private Integer numberSamples;
	@Column(name = "number_processed_samples")
	private Integer numberProcessedSamples;
	@Column(name = "specification_id")
	private Integer specificationId;
	@Column(name = "number_accepted_samples")
	private Integer numberAcceptedSamples;
	@Column(name = "number_unaccepted_samples")
	private Integer numberUnacceptedSamples;
	@Column(name = "reason_unaccepted_samples")
	private String reasonUnacceptedSamples;
	@Column(name = "observation_requirement")
	private String observationRequirement;
	@Column(name = "observation_entry")
	private String observationEntry;
	@Column(name = "report_results")
	private String reportResults;
	@Column(name = "observations_report")
	private String observationsReport;
	@Column(name = "report_by_user_id")
	private Integer reportByUserId;
	@Column(name = "requeriment_user_id")
	private Integer requerimentUserId;
	@Column(name = "technique_01_id")
	private Integer technique01Id;
	@Column(name = "kit_reagent_01_id")
	private Integer kitReagent01Id;
	@Column(name = "technique_02_id")
	private Integer technique02Id;
	@Column(name = "kit_reagent_02_id")
	private Integer kitReagent02Id;
	@Column(name = "technique_03_id")
	private Integer technique03Id;
	@Column(name = "kit_reagent_03_id")
	private Integer kitReagent03Id;
	@Column(name = "reception_user_id")
	private Integer receptionUserId;
	@Column(name = "processing_users_id")
	private String processingUsersId;
	@Column(name = "validator_user_id")
	private Integer validatorUserId;
	@Column(name = "approval_user_id")
	private Integer approvalUserId;
	@Column(name = "observation_shipping")
	private String observationShipping;
	@Column(name = "observation_reception")
	private String observationReception;
	@Column(name = "shipping_date")
	private Calendar shippingDate;
	@Column(name = "reception_date")
	private Calendar receptionDate;
	
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
