package com.weldtic.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weldtic.model.Company;
import com.weldtic.model.Piece;
import com.weldtic.model.User;
import com.weldtic.model.Welder;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class InicioController {

	@Autowired
	private CompanyRepository<Company> companyRepository;

	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private PieceRepository<Piece> pieceRepository;

	@RequestMapping("/inicio")
	public String inicio(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User logUser = (User) authentication.getPrincipal();

		if (logUser.getTipo().equals("Manager")) {

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
