package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Manager;
import com.weldtic.model.Project;

public interface ProjectRepository<T extends Project>extends JpaRepository<T, Long> {

	public List<Project> findByManager (Manager manager);
}
