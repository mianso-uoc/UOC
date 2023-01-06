package com.weldtic.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.weldtic.enums.WeldMode;


@Entity
@DiscriminatorValue(value="Welder")
public class Welder extends Employee{
	
	private static final long serialVersionUID = 1L;
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "weldMode")
	private WeldMode weldMode;

	public Welder() {}
	
	public Welder(WeldMode weldMode) {
		this.weldMode = weldMode;
		
	}

	public WeldMode getWeldMode() {
		return weldMode;
	}

	public void setWeldMode(WeldMode weldMode) {
		this.weldMode = weldMode;
	}
		
}