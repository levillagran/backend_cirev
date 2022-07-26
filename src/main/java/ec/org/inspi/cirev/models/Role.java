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
@Table(name = "roles", schema = "molecular")
public class Role {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
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
