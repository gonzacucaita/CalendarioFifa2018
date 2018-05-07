package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@Table(name = "team")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String country;

	private String flag;

	private int goalsAgainst;

	private int goalsFavor;

	@Column(name="group_")
	private String group;

	private int lostMatches;

	private int playedGames;

	private String state;

	private int tiedMatches;

	private int wonMatches;

	public Team() {
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

	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "flag")
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "goalsAgainst")
	public int getGoalsAgainst() {
		return this.goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	@Column(name = "goalsFavor")
	public int getGoalsFavor() {
		return this.goalsFavor;
	}

	public void setGoalsFavor(int goalsFavor) {
		this.goalsFavor = goalsFavor;
	}

	@Column(name = "group_")
	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Column(name = "lostMatches")
	public int getLostMatches() {
		return this.lostMatches;
	}

	public void setLostMatches(int lostMatches) {
		this.lostMatches = lostMatches;
	}

	@Column(name = "playedGames")
	public int getPlayedGames() {
		return this.playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "tiedMatches")
	public int getTiedMatches() {
		return this.tiedMatches;
	}

	public void setTiedMatches(int tiedMatches) {
		this.tiedMatches = tiedMatches;
	}

	@Column(name = "wonMatches")
	public int getWonMatches() {
		return this.wonMatches;
	}

	public void setWonMatches(int wonMatches) {
		this.wonMatches = wonMatches;
	}

}