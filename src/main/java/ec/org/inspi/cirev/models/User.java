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
@Table(name = "users", schema = "molecular")
public class User {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "secondname")
	private String secondname;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "secondlastname")
	private String secondlastname;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "mail")
	private String mail;
	@Column(name = "identification")
	private String identification;
	@Column(name = "code")
	private String code;
	@Column(name = "phone_01")
	private String phone1;
	@Column(name = "mobile_phone_01")
	private String mobilePhone01;
	@Column(name = "active")
	private boolean active;
	@Column(name = "is_connected")
	private boolean isConnected;
	@Column(name = "last_connected_date")
	private Calendar lastConnectedDate;
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
