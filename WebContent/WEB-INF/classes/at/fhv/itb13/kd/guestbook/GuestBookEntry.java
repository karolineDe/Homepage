package at.fhv.itb13.kd.guestbook;

import java.util.Date;
import javax.persistence.*;

/**
 * Class for Guestbook Entrys
 * @author Karoline Deuring
 */
@Entity
@Table(name = "guestbook")
public class GuestBookEntry {
	@Id @GeneratedValue
	@Column(name = "id")
	private int _id;
	@Column(name = "name")
	private String _name;
	@Column(name = "email")
	private String _email;
	@Column(name = "comment")
	private String _comment;
	@Column(name = "date")
	private Date _date;

	/**
	 * Constructors
	 */
	public GuestBookEntry() {
	}
	public GuestBookEntry(int id, String name, String email, String comment, Date date) {
		_id = id;
		_name = name;
		_email = email;
		_comment = comment;
		_date = date;
	}

	/**
	 * Getter and Setter
	 */
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}
}
