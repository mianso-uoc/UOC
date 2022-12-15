package com.weldtic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weldtic.model.Company;
import com.weldtic.model.User;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class InicioController {

	@Autowired
	private CompanyRepository<Company> companyRepository;

	@Autowired
	private UserRepository<User> userRepository;

//	@GetMapping("/")
//	public String index(Model model, Principal principal) {
//		model.addAttribute("message", "You are logged in as " + principal.getName());
//		return "inicio";
//	}

	@RequestMapping("/inicio")
	public String inicio(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User logUser = (User) authentication.getPrincipal();

		if (logUser.getTipo().equals("Manager")) {

			return "inicioManager";
		}
		if (logUser.getTipo().equals("Welder")) {

			return "inicioWelder";
		} else {
			List<User> users = userRepository.findAll();
			model.addAttribute("users", users);

			return "inicio";
		}
	}

//	@RequestMapping(value =  "/login", method = RequestMethod.POST)
//	public String acceso(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
//		System.out.println("EMAIL: " + usuario);
//		return "inicio";
//	}

}
