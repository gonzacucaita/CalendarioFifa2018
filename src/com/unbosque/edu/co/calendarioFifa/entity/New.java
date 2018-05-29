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
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNews;

	private int idUser;

	private String largeDescription;

	private String shortDescription;

	private String state;
	
	private String photo;

	public New() {
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
	@Column(name = "dateNews" ,length =19)
	public Date getDateNews() {
		return this.dateNews;
	}

	public void setDateNews(Date dateNews) {
		this.dateNews = dateNews;
	}

	@Column(name = "idUser", nullable = false, length = 8)
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Column(name = "largeDescription")
	public String getLargeDescription() {
		return this.largeDescription;
	}

	public void setLargeDescription(String largeDescription) {
		this.largeDescription = largeDescription;
	}
	
	@Column(name = "shortDescription")
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "photo")
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}