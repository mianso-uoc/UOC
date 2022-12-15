package com.weldtic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Company;

public interface CompanyRepository<T extends Company>extends JpaRepository<T, Long> {

}
