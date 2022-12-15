package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Company;
import com.weldtic.model.Machine;

public interface MachineRepository<T extends Machine>extends JpaRepository<T, Long> {

	List<Machine> findByCompany(Company company);
}
