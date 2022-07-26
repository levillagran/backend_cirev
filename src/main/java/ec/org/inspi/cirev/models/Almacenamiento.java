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
@Table(name = "storages", schema = "molecular")
public class Almacenamiento {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "text_01")
	private String text01;
	@Column(name = "text_02")
	private String text02;
	@Column(name = "text_03")
	private String text03;
	@Column(name = "text_04")
	private String text04;
	@Column(name = "text_05")
	private String text05;
	
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
