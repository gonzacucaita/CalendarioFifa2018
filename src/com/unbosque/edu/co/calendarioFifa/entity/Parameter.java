package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the parameter database table.
 * 
 */
@Entity
@Table(name = "parameter")
public class Parameter implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The description parameter. */
	private String descriptionParameter;

	/** The number value. */
	private int numberValue;

	/** The parameter code. */
	private String parameterCode;

	/** The parameter type. */
	private String parameterType;

	/** The text value. */
	private String textValue;

	/**
	 * Instantiates a new parameter.
	 */
	public Parameter() {
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
	 * Gets the description parameter.
	 *
	 * @return the description parameter
	 */
	@Column(name = "descriptionParameter")
	public String getDescriptionParameter() {
		return this.descriptionParameter;
	}

	/**
	 * Sets the description parameter.
	 *
	 * @param descriptionParameter the new description parameter
	 */
	public void setDescriptionParameter(String descriptionParameter) {
		this.descriptionParameter = descriptionParameter;
	}

	/**
	 * Gets the number value.
	 *
	 * @return the number value
	 */
	@Column(name = "numberValue")
	public int getNumberValue() {
		return this.numberValue;
	}

	/**
	 * Sets the number value.
	 *
	 * @param numberValue the new number value
	 */
	public void setNumberValue(int numberValue) {
		this.numberValue = numberValue;
	}

	/**
	 * Gets the parameter code.
	 *
	 * @return the parameter code
	 */
	@Column(name = "parameterCode")
	public String getParameterCode() {
		return this.parameterCode;
	}

	/**
	 * Sets the parameter code.
	 *
	 * @param parameterCode the new parameter code
	 */
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	/**
	 * Gets the parameter type.
	 *
	 * @return the parameter type
	 */
	@Column(name = "parameterType")
	public String getParameterType() {
		return this.parameterType;
	}

	/**
	 * Sets the parameter type.
	 *
	 * @param parameterType the new parameter type
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * Gets the text value.
	 *
	 * @return the text value
	 */
	@Column(name = "textValue")
	public String getTextValue() {
		return this.textValue;
	}

	/**
	 * Sets the text value.
	 *
	 * @param textValue the new text value
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

}