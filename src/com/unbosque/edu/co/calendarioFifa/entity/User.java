package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The active. */
	private String active;

	/** The date last password. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastPassword;

	/** The email address. */
	private String emailAddress;

	/** The full name. */
	private String fullName;

	/** The password. */
	private String password;

	/** The phone number. */
	private String phoneNumber;

	/** The user name. */
	private String userName;

	/** The user type. */
	private String userType;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 */
	public User(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	@Column(name = "active", length = 1)
	public String getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * Gets the date last password.
	 *
	 * @return the date last password
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateLastPassword", length = 19)
	public Date getDateLastPassword() {
		return this.dateLastPassword;
	}

	/**
	 * Sets the date last password.
	 *
	 * @param dateLastPassword the new date last password
	 */
	public void setDateLastPassword(Date dateLastPassword) {
		this.dateLastPassword = dateLastPassword;
	}
	
	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	@Column(name = "emailAddress", length = 75)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress+"@gmail.com";
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	@Column(name = "fullName", length = 60)
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Column(name = "password", length = 256)
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	@Column(name = "phoneNumber", length = 10)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@Column(name = "userName", length = 256)
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	@Column(name = "userType", length = 12)
	public String getUserType() {
		return this.userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}