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
@Table(name = "specification_techniques", schema = "molecular")
public class EspecificacionTecnica {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "specification_id")
	private Integer specificationId;
	@Column(name = "technique_id")
	private Integer techniqueId;
}
