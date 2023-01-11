package com.weldtic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weldtic.model.Piece;
import com.weldtic.model.Weld;

public interface WeldRepository<T extends Weld>extends JpaRepository<T, Long> {

	@Query(value="select * from weld w join piece p on w.piece_id = p.id join project pr on  p.project_machine_project_id = pr.id join alarm a on a.weld_id = w.id	where pr.manager_id = ?1", nativeQuery = true)
	List<Weld> findByManagerWithAlarm (Long id_manager);
	List <Weld> findByPiece (Piece piece); 
	@Query(value="select * from weld w join piece p on w.piece_id = p.id join project pr on p.project_machine_project_id = pr.id where pr.manager_id = ?1", nativeQuery = true)
	List<Weld> findByManager (Long id_manager);
}
