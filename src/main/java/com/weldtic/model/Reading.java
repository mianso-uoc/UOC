package com.weldtic.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reading")
public class Reading {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "amp")
	private Float amp;

	@Column(name = "volt")
	private Float volt;
	
	@ManyToOne()
    @JoinColumn(name = "weld_id")
    private Weld weld;
	
	public Reading() {}
	
	public Reading(Timestamp date, Float amp, Float volt) {
		super();
		this.date = date;
		this.amp = amp;
		this.volt = volt;
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Float getAmp() {
		return amp;
	}

	public void setAmp(Float amp) {
		this.amp = amp;
	}

	public Float getVolt() {
		return volt;
	}

	public void setVolt(Float volt) {
		this.volt = volt;
	}

	public Weld getWeld() {
		return weld;
	}

	public void setWeld(Weld weld) {
		this.weld = weld;
	}
}
