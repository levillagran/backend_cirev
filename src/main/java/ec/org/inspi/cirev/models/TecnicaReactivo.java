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
@Table(name = "techniques_kits_reagents", schema = "molecular")
public class TecnicaReactivo {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "techniques_id")
	private Integer techniquesId;
	@Column(name = "kits_reagents_id")
	private Integer kitsReagentsId;

}
