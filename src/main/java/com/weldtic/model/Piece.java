package com.weldtic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "piece")
public class Piece {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	private String observaciones;
	
	@ManyToOne()
    @JoinColumn(name = "welder_id")
    private Welder welder;
	
	@ManyToOne()
	private ProjectMachine projectMachine;
	
	
	public Piece() {}
	
	public Piece(String name) {
		super();
		this.name = name;
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

	public Welder getWelder() {
		return welder;
	}

	public void setWelder(Welder welder) {
		this.welder = welder;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public ProjectMachine getProjectMachine() {
		return projectMachine;
	}

	public void setProjectMachine(ProjectMachine projectMachine) {
		this.projectMachine = projectMachine;
	}

}
