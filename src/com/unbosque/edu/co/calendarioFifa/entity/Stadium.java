package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the stadium database table.
 * 
 */
@Entity
@Table(name = "stadium")
public class Stadium implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The capacity. */
	private String capacity;

	/** The city. */
	private String city;

	/** The photo. */
	private String photo;

	/** The population. */
	private String population;

	/** The temperature. */
	private String temperature;

	/**
	 * Instantiates a new stadium.
	 */
	public Stadium() {
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
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	@Column(name = "capacity")
	public String getCapacity() {
		return this.capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
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
	
	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	@Column(name = "population")
	public String getPopulation() {
		return this.population;
	}

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(String population) {
		this.population = population;
	}

	/**
	 * Gets the temperature.
	 *
	 * @return the temperature
	 */
	@Column(name = "temperature")
	public String getTemperature() {
		return this.temperature;
	}

	/**
	 * Sets the temperature.
	 *
	 * @param temperature the new temperature
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}