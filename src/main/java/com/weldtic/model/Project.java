package com.weldtic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	@NotBlank
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToOne()
    @JoinColumn(name = "manager_id")
    private Manager manager;
	
	@OneToMany(mappedBy = "project")
	private Set<ProjectMachine> projectMachine;
	
	public Project() {}
	
	public Project(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<ProjectMachine> getProjectMachine() {
		return projectMachine;
	}

	public void setProjectMachine(Set<ProjectMachine> projectMachine) {
		this.projectMachine = projectMachine;
	}
}
