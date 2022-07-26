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
@Table(name = "cantons", schema = "molecular")
public class Canton {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
}
