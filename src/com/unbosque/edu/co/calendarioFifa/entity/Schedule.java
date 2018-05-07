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
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date gameDate;

	private int idReferee;

	private int idStadium;

	private int localGoals;

	private int localTeam;

	private String state;

	private int visitGoals;

	private int visitTeam;

	public Schedule() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gameDate" ,length =19)
	public Date getGameDate() {
		return this.gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	@Column(name = "idReferee")
	public int getIdReferee() {
		return this.idReferee;
	}

	public void setIdReferee(int idReferee) {
		this.idReferee = idReferee;
	}

	@Column(name = "idStadium")
	public int getIdStadium() {
		return this.idStadium;
	}

	public void setIdStadium(int idStadium) {
		this.idStadium = idStadium;
	}

	@Column(name = "localGoals")
	public int getLocalGoals() {
		return this.localGoals;
	}

	public void setLocalGoals(int localGoals) {
		this.localGoals = localGoals;
	}

	@Column(name = "localTeam")
	public int getLocalTeam() {
		return this.localTeam;
	}

	public void setLocalTeam(int localTeam) {
		this.localTeam = localTeam;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "visitGoals")
	public int getVisitGoals() {
		return this.visitGoals;
	}

	public void setVisitGoals(int visitGoals) {
		this.visitGoals = visitGoals;
	}

	@Column(name = "visitTeam")
	public int getVisitTeam() {
		return this.visitTeam;
	}

	public void setVisitTeam(int visitTeam) {
		this.visitTeam = visitTeam;
	}

}