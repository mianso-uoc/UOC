package com.weldtic.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weldtic.model.User;
import com.weldtic.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository<User> userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (user.isPresent()) {
			return user.get();
			
//			return new org.springframework.security.core.userdetails.User(
//			          user.get().getEmail(), user.get().getPassword(), user.get().isEnabled(), true, true, 
//			          true, getGrantedAuthorities(user.get().getTipo()));
			
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

	public void createUser(UserDetails user) {
		userRepository.save((User) user);
	}
	
	

	private List<GrantedAuthority> getGrantedAuthorities(String privilege) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(privilege));
		return authorities;
	}
}
