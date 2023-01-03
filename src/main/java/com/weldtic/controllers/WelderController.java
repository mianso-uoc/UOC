package com.weldtic.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weldtic.enums.WeldMode;
import com.weldtic.model.Company;
import com.weldtic.model.User;
import com.weldtic.model.Welder;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.UserRepository;


@Controller
public class WelderController {
	
	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private CompanyRepository<Company> companyRepository;
		
	//con model pasamos datos del controller al jsp(la vista)
	@RequestMapping(value = "/crearSoldador", method = RequestMethod.GET)
	public String nuevoSoldador(Model model) {
		
		Welder welder = new Welder();
		model.addAttribute("welder", welder);
		
		model.addAttribute("action", "new");
		
		//Mostramos todas las empresas 
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);
		model.addAttribute("weldModes",Arrays.asList(WeldMode.values()));

		return "crearSoldador";
	}
	
	@RequestMapping(value= "/guardarSoldador", method = RequestMethod.POST)
	public String submit(@ModelAttribute("welder") Welder welder) {

		if (welder.getPassword().length() == 60) {
			userRepository.save(welder);
		} else {
		String passw = welder.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(passw);
        welder.setPassword(encodedPassword);	
		//Guarda los datos del formulario en la base de datos
		userRepository.save(welder);
		}
		return  "redirect:/user";
	}
}
