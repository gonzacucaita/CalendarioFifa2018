package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name="news")
public class New implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The date news. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNews;

	/** The id user. */
	private int idUser;

	/** The large description. */
	private String largeDescription;

	/** The short description. */
	private String shortDescription;

	/** The state. */
	private String state;
	
	/** The photo. */
	private String photo;

	/**
	 * Instantiates a new new.
	 */
	public New() {
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
	 * Gets the date news.
	 *
	 * @return the date news
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateNews" ,length =19)
	public Date getDateNews() {
		return this.dateNews;
	}

	/**
	 * Sets the date news.
	 *
	 * @param dateNews the new date news
	 */
	public void setDateNews(Date dateNews) {
		this.dateNews = dateNews;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	@Column(name = "idUser", nullable = false, length = 8)
	public int getIdUser() {
		return this.idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * Gets the large description.
	 *
	 * @return the large description
	 */
	@Column(name = "largeDescription")
	public String getLargeDescription() {
		return this.largeDescription;
	}

	/**
	 * Sets the large description.
	 *
	 * @param largeDescription the new large description
	 */
	public void setLargeDescription(String largeDescription) {
		this.largeDescription = largeDescription;
	}
	
	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	@Column(name = "shortDescription")
	public String getShortDescription() {
		return this.shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	@Column(name = "photo")
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
}