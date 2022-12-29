package com.weldtic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.weldtic.model.Manager;
import com.weldtic.model.Weld;
import com.weldtic.repository.WeldRepository;

@ControllerAdvice
public class WatcherControllerAdvice {
	@Autowired
	private WeldRepository<Weld> weldRepository;

	@ModelAttribute
	public void loadAlarm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Manager mg = new Manager();
		if (authentication.getPrincipal() != "anonymousUser") {
			if(authentication.getPrincipal().getClass().getName() == "com.weldtic.model.Manager") {
			Object manager = authentication.getPrincipal();
			mg = (Manager) manager;
			List<Weld> welds = weldRepository.findByManagerWithAlarm(mg.getId());
			model.addAttribute("alarms", welds.size());
			}
		}
	}
}
