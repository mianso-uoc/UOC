package com.weldtic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "info")
	private String info;
	
	@JoinColumn(name = "weld_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Weld weld;
	
	public Alarm() {}
	
	public Alarm(String name, String info) {
		super();
		this.name = name;
		this.info = info;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Weld getWeld() {
		return weld;
	}

	public void setWeld(Weld weld) {
		this.weld = weld;
	}

}
