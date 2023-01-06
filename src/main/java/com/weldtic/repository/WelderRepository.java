package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weldtic.model.Company;
import com.weldtic.model.Welder;

@Repository
public interface WelderRepository<T extends Welder>extends JpaRepository<T, Long> {
	List<Welder> findWelderByCompany(Company company);
}
