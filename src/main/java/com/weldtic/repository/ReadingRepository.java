package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Reading;
import com.weldtic.model.Weld;

public interface ReadingRepository<T extends Reading>extends JpaRepository<T, Long> {

	List<Reading> findByWeldOrderByDateAsc(Weld weld);
}
