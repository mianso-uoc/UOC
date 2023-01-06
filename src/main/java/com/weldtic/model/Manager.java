package com.weldtic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue(value="Manager")
public class Manager extends Employee{
	
	//@GeneratedValue(strategy = GenerationType.AUTO)

	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Project> projects;

	
	public Manager() {}


	public List<Project> getProjects() {
		return projects;
	}


	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

		
}