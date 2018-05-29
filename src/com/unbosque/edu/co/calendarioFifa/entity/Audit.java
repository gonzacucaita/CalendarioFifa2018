package com.unbosque.edu.co.calendarioFifa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the audit database table.
 * 
 */
@Entity
@Table(name = "audit")
public class Audit implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private long id;

	/** The create date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/** The operation. */
	private String operation;

	/** The table id. */
	private long tableId;

	/** The table name. */
	private String tableName;

	/** The user id. */
	long userId;
	
	/** The ip. */
	private String ip;

	/**
	 * Instantiates a new audit.
	 */
	public Audit() {
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
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate" ,length =19)
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	@Column(name = "operation", length = 1)
	public String getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets the table id.
	 *
	 * @return the table id
	 */
	@Column(name = "tableId")
	public long getTableId() {
		return this.tableId;
	}

	/**
	 * Sets the table id.
	 *
	 * @param tableId the new table id
	 */
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	@Column(name = "tableName", length = 30)
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * Sets the table name.
	 *
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	@Column(name = "userId", nullable = false, length = 8)
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	@Column(name = "ip")
	public String getIp() {
		return this.ip;
	}
	
	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
}