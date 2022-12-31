package com.weldtic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weldtic.model.Company;
import com.weldtic.model.Manager;
import com.weldtic.model.Piece;
import com.weldtic.model.Project;
import com.weldtic.model.User;
import com.weldtic.model.Welder;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.ProjectRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class InicioController {

	@Autowired
	private CompanyRepository<Company> companyRepository;

	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private PieceRepository<Piece> pieceRepository;
	
	@Autowired
	private ProjectRepository<Project> projectRepository;

	@RequestMapping("/inicio")
	public String inicio(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User logUser = (User) authentication.getPrincipal();

		if (logUser.getTipo().equals("Manager")) {
			Manager manager = (Manager) logUser; 
			List<Project> projects = projectRepository.findByManager(manager);
			model.addAttribute("projects", projects);

			return "inicioResponsable";
		}
		if (logUser.getTipo().equals("Welder")) {

			Welder welder = (Welder) logUser;
			List<Piece> pieces = pieceRepository.findByWelder(welder);
			model.addAttribute("pieces", pieces);
			return "inicioSoldador";
		} 
		else {
			List<User> users = userRepository.findAll();
			model.addAttribute("users", users);

			return "inicio";
		}
	}
}
