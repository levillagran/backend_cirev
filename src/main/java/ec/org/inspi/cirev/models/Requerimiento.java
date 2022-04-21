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
@Table(name = "requirements")
public class Requerimiento {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "number")
	private Integer number;
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
	private Calendar reportResults;
	@Column(name = "observations_report")
	private String observationsReport;
	@Column(name = "requeriment_user_id")
	private Integer requerimentUserId;
	@Column(name = "technique_id")
	private Integer techniqueId;
	@Column(name = "kit_reagent_id")
	private Integer kitReagentId;
	@Column(name = "reception_user_id")
	private Integer receptionUserId;
	@Column(name = "processing_users_id")
	private String processingUsersId;
	@Column(name = "approval_user_id")
	private Integer approvalUserId;
	@Column(name = "observation_sequence")
	private String observationSequence;
	@Column(name = "is_shipping")
	private Boolean isShipping;
	@Column(name = "shipping_reception_date")
	private Calendar shippingReceptionDate;
	
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
