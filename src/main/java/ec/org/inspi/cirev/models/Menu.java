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
@Table(name = "menus_items", schema = "plataform")
public class Menu {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "parent_menu_item_id")
	private Integer parentId;
	@Column(name = "name")
	private String name;
	@Column(name = "icon")
	private String icon;
	@Column(name = "href")
	private String href;
	@Column(name = "permission_id")
	private Integer permissionId;
	@Column(name = "position")
	private Integer position;
	@Column(name = "visible")
	private boolean visible;
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
