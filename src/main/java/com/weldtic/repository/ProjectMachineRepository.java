package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Machine;
import com.weldtic.model.Project;
import com.weldtic.model.ProjectMachine;

public interface ProjectMachineRepository<T extends ProjectMachine>extends JpaRepository<T, Long> {

	List<ProjectMachine> findByMachineAndProject(Machine machine, Project project);
	
	List<Project> findByProject(Project project);
}
