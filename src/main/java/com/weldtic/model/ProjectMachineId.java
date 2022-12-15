package com.weldtic.model;

import java.io.Serializable;
import java.util.Objects;

public class ProjectMachineId implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long project;
	private Long machine;
	
	public ProjectMachineId(Long project, Long machine) {
		this.project = project;
		this.machine = machine;
	}
	
	public ProjectMachineId() {

	}

	public Long getProject() {
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}
	public Long getMachine() {
		return machine;
	}
	public void setMachine(Long machine) {
		this.machine = machine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(machine, project);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMachineId other = (ProjectMachineId) obj;
		return machine == other.machine && project == other.project;
	}
	

}
