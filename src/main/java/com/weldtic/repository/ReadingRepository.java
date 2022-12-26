package com.weldtic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Reading;

public interface ReadingRepository<T extends Reading>extends JpaRepository<T, Long> {

}
