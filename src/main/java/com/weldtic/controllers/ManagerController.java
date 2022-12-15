package com.weldtic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weldtic.model.Company;
import com.weldtic.model.Manager;
import com.weldtic.model.User;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class ManagerController {

	@Autowired
	private UserRepository<User> userRepository;

	@Autowired
	private CompanyRepository<Company> companyRepository;

	// con model pasamos datos del controller al jsp(la vista)
	@RequestMapping(value = "/crearResponsable", method = RequestMethod.GET)
	public String nuevoResponsable(Model model) {

		Manager manager = new Manager();
		model.addAttribute("manager", manager);

		model.addAttribute("action", "new");

		// Mostramos todas las empresas
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

		return "crearResponsable";
	}

	@RequestMapping(value = "/guardarResponsable", method = RequestMethod.POST)
	public String submit(@ModelAttribute("manager") Manager manager) {

		String passw = manager.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(passw);
		manager.setPassword(encodedPassword);

		// Guarda los datos del formulario en la base de datos
		userRepository.save(manager);

		// System.out.println(company.getName()+" "+company.getAddress());

		return "redirect:/user";
	}
}
