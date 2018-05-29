package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the referee database table.
 * 
 */
@Entity
@Table(name = "referee")
public class Referee implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The full name. */
	private String fullName;

	/** The nationality. */
	private String nationality;

	/** The state. */
	private String state;

	/**
	 * Instantiates a new referee.
	 */
	public Referee() {
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
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	@Column(name = "fullName")
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
	 * Gets the nationality.
	 *
	 * @return the nationality
	 */
	@Column(name = "nationality")
	public String getNationality() {
		return this.nationality;
	}

	/**
	 * Sets the nationality.
	 *
	 * @param nationality the new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

}