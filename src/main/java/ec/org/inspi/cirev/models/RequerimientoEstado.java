/**
 * 
 */
package ec.org.inspi.cirev.models;

/**
 * @author episig := Lenin Villagran
 *
 */
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "requirements_status", schema = "molecular")
public class RequerimientoEstado {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "requirement_id")
	private Integer requirementId;
	@Column(name = "status_id")
	private Integer statusId;
	
	public RequerimientoEstado() {
		super();
	}

	public RequerimientoEstado(Integer id, Integer requirementId, Integer statusId) {
		super();
		this.id = id;
		this.requirementId = requirementId;
		this.statusId = statusId;
	}
	
	
	
}
