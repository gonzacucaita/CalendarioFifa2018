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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The country. */
	private String country;

	/** The flag. */
	private String flag;

	/** The goals against. */
	private int goalsAgainst;

	/** The goals favor. */
	private int goalsFavor;

	/** The group. */
	@Column(name="group_")
	private String group;

	/** The lost matches. */
	private int lostMatches;

	/** The played games. */
	private int playedGames;

	/** The state. */
	private String state;

	/** The tied matches. */
	private int tiedMatches;

	/** The won matches. */
	private int wonMatches;

	/**
	 * Instantiates a new team.
	 */
	public Team() {
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	@Column(name = "flag")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Gets the goals against.
	 *
	 * @return the goals against
	 */
	@Column(name = "goalsAgainst")
	public int getGoalsAgainst() {
		return this.goalsAgainst;
	}

	/**
	 * Sets the goals against.
	 *
	 * @param goalsAgainst the new goals against
	 */
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	/**
	 * Gets the goals favor.
	 *
	 * @return the goals favor
	 */
	@Column(name = "goalsFavor")
	public int getGoalsFavor() {
		return this.goalsFavor;
	}

	/**
	 * Sets the goals favor.
	 *
	 * @param goalsFavor the new goals favor
	 */
	public void setGoalsFavor(int goalsFavor) {
		this.goalsFavor = goalsFavor;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	@Column(name = "group_")
	public String getGroup() {
		return this.group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gets the lost matches.
	 *
	 * @return the lost matches
	 */
	@Column(name = "lostMatches")
	public int getLostMatches() {
		return this.lostMatches;
	}

	/**
	 * Sets the lost matches.
	 *
	 * @param lostMatches the new lost matches
	 */
	public void setLostMatches(int lostMatches) {
		this.lostMatches = lostMatches;
	}

	/**
	 * Gets the played games.
	 *
	 * @return the played games
	 */
	@Column(name = "playedGames")
	public int getPlayedGames() {
		return this.playedGames;
	}

	/**
	 * Sets the played games.
	 *
	 * @param playedGames the new played games
	 */
	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
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
	 * Gets the tied matches.
	 *
	 * @return the tied matches
	 */
	@Column(name = "tiedMatches")
	public int getTiedMatches() {
		return this.tiedMatches;
	}

	/**
	 * Sets the tied matches.
	 *
	 * @param tiedMatches the new tied matches
	 */
	public void setTiedMatches(int tiedMatches) {
		this.tiedMatches = tiedMatches;
	}

	/**
	 * Gets the won matches.
	 *
	 * @return the won matches
	 */
	@Column(name = "wonMatches")
	public int getWonMatches() {
		return this.wonMatches;
	}

	/**
	 * Sets the won matches.
	 *
	 * @param wonMatches the new won matches
	 */
	public void setWonMatches(int wonMatches) {
		this.wonMatches = wonMatches;
	}

}