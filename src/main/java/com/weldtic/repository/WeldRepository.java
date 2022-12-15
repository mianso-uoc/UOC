package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weldtic.model.Piece;
import com.weldtic.model.Weld;

public interface WeldRepository<T extends Weld>extends JpaRepository<T, Long> {


	List <Weld> findByPiece (Piece piece); 
}
