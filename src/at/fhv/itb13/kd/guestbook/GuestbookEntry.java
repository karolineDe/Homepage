package at.fhv.itb13.kd.guestbook;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GuestbookEntry")
public class GuestbookEntry {

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
	
	public GuestbookEntry(){}
	
	public GuestbookEntry(int id, String name, String email, String comment, Date date) {
		_id = id;
		_name = name;
		_email = email;
		_comment = comment;
		_date = date;
	}
	
	//Getter
	public int getID()	{  return _id;	}
	public String getName()	{	return _name;	}
	public String getEmail()	{	return _email;	}
	public String getComment()	{	return _comment;	}
	public Date getDate()	{	return _date;	}
	
	//Setter
	public void setID(int id)	{	_id = id;	}
	public void setName(String name)	{	_name = name;	}
	public void setEmail(String email)	{	_email = email;	}
	public void setComment(String comment)	{	_comment = comment;	}
	public void setDate(Date date)	{	_date = date;	}

}
