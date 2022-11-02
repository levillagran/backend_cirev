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
@Table(name = "provinces_cantons", schema = "geography")
public class ProvinciaCanton {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "province_id")
	private Integer provinceId;
	@Column(name = "canton_id")
	private Integer cantonId;
}
