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
@Table(name = "evidence_documents", schema = "molecular")
public class DocumentosEvidencia {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "requirement_id")
	private Integer requirementId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "size")
	private Integer size;
	@Column(name = "document")
	private String document;
	@Column(name = "document_type_id")
	private Integer documentTypeId;
	
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
