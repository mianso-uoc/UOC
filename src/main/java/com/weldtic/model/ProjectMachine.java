package com.weldtic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projectMachine")
@IdClass(ProjectMachineId.class)
public class ProjectMachine {

	@Id
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	@Id
	@ManyToOne
	@JoinColumn(name = "machine_id", referencedColumnName = "id")
	private Machine machine;

	public ProjectMachine() {
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
