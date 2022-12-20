package com.weldtic.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class WatcherControllerAdvice {

	@ModelAttribute
	public void loadAlarm (Model model) {
		
	}
}
