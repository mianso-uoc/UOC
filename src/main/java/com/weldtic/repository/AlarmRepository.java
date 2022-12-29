package com.weldtic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Alarm;

public interface AlarmRepository<T extends Alarm>extends JpaRepository<T, Long> {

}
