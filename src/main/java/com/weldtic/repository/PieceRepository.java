package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Piece;
import com.weldtic.model.ProjectMachine;
import com.weldtic.model.Welder;

public interface PieceRepository<T extends Piece>extends JpaRepository<T, Long> {

	List <Piece> findByProjectMachine (ProjectMachine projectMachine);
	List <Piece> findByWelder(Welder welder);
}
