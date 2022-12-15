package com.weldtic.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weldtic.model.User;
import com.weldtic.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

	   @Autowired 
	   private UserRepository<User> userRepository; 
	   
	   @Override 
	   public UserDetails loadUserByUsername(String email) 
	   throws UsernameNotFoundException { 
	      Optional<User> user = userRepository.findUserByEmail(email);
	      if(user.isPresent()) {
	         return user.get(); 
	      } else {
	    	  throw new UsernameNotFoundException("Usuario no encontrado");
	      }
	   } 
	   public void createUser(UserDetails user) { 
	      userRepository.save((User) user); 
	   } 
}
