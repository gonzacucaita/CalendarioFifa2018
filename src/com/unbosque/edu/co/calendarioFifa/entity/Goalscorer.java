package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the goalscorer database table.
 * 
 */
@Entity
@Table(name ="goalscorer")
public class Goalscorer implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The full name. */
	private String fullName;

	/** The goals. */
	private int goals;

	/** The photo. */
	private String photo;

	/** The team. */
	private String team;

	/**
	 * Instantiates a new goalscorer.
	 */
	public Goalscorer() {
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
	@Column(name ="fullName")
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
	 * Gets the goals.
	 *
	 * @return the goals
	 */
	@Column(name ="goals")
	public int getGoals() {
		return this.goals;
	}

	/**
	 * Sets the goals.
	 *
	 * @param goals the new goals
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	@Column(name ="photo")
	public String getPhoto() {
		return this.photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	@Column(name ="team")
	public String getTeam() {
		return this.team;
	}

	/**
	 * Sets the team.
	 *
	 * @param team the new team
	 */
	public void setTeam(String team) {
		this.team = team;
	}

}