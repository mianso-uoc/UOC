package com.weldtic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weldtic.model.User;

@Repository
public interface UserRepository<T extends User>extends JpaRepository<T, Long> {
	Optional<User> findUserByEmail(String email);
	List <User> findUserByTipo(String tipo);
	User findUserByName(String name);
}
