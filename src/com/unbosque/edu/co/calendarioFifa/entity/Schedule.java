package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the schedule database table.
 * 
 */
@Entity
@Table(name ="schedule")
public class Schedule implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The game date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date gameDate;

	/** The id referee. */
	private int idReferee;

	/** The id stadium. */
	private int idStadium;

	/** The local goals. */
	private int localGoals;

	/** The local team. */
	private int localTeam;

	/** The state. */
	private String state;

	/** The visit goals. */
	private int visitGoals;

	/** The visit team. */
	private int visitTeam;

	/**
	 * Instantiates a new schedule.
	 */
	public Schedule() {
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
	 * Gets the game date.
	 *
	 * @return the game date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gameDate" ,length =19)
	public Date getGameDate() {
		return this.gameDate;
	}

	/**
	 * Sets the game date.
	 *
	 * @param gameDate the new game date
	 */
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	/**
	 * Gets the id referee.
	 *
	 * @return the id referee
	 */
	@Column(name = "idReferee")
	public int getIdReferee() {
		return this.idReferee;
	}

	/**
	 * Sets the id referee.
	 *
	 * @param idReferee the new id referee
	 */
	public void setIdReferee(int idReferee) {
		this.idReferee = idReferee;
	}

	/**
	 * Gets the id stadium.
	 *
	 * @return the id stadium
	 */
	@Column(name = "idStadium")
	public int getIdStadium() {
		return this.idStadium;
	}

	/**
	 * Sets the id stadium.
	 *
	 * @param idStadium the new id stadium
	 */
	public void setIdStadium(int idStadium) {
		this.idStadium = idStadium;
	}

	/**
	 * Gets the local goals.
	 *
	 * @return the local goals
	 */
	@Column(name = "localGoals")
	public int getLocalGoals() {
		return this.localGoals;
	}

	/**
	 * Sets the local goals.
	 *
	 * @param localGoals the new local goals
	 */
	public void setLocalGoals(int localGoals) {
		this.localGoals = localGoals;
	}

	/**
	 * Gets the local team.
	 *
	 * @return the local team
	 */
	@Column(name = "localTeam")
	public int getLocalTeam() {
		return this.localTeam;
	}

	/**
	 * Sets the local team.
	 *
	 * @param localTeam the new local team
	 */
	public void setLocalTeam(int localTeam) {
		this.localTeam = localTeam;
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

	/**
	 * Gets the visit goals.
	 *
	 * @return the visit goals
	 */
	@Column(name = "visitGoals")
	public int getVisitGoals() {
		return this.visitGoals;
	}

	/**
	 * Sets the visit goals.
	 *
	 * @param visitGoals the new visit goals
	 */
	public void setVisitGoals(int visitGoals) {
		this.visitGoals = visitGoals;
	}

	/**
	 * Gets the visit team.
	 *
	 * @return the visit team
	 */
	@Column(name = "visitTeam")
	public int getVisitTeam() {
		return this.visitTeam;
	}

	/**
	 * Sets the visit team.
	 *
	 * @param visitTeam the new visit team
	 */
	public void setVisitTeam(int visitTeam) {
		this.visitTeam = visitTeam;
	}

}