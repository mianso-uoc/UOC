package com.weldtic.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weldtic.enums.WeldMode;
import com.weldtic.model.Company;
import com.weldtic.model.User;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository<User> userRepository;

	@Autowired
	private CompanyRepository<Company> companyRepository;

	@RequestMapping("/user")
	public String inicio(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);

		return "user";
	}

	// SELECT de base de datos con la id de user
	@RequestMapping("/verUser/{id}")
	public String inicio(@PathVariable Long id, Model model) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			model.addAttribute("user", user.get());
		}

		model.addAttribute("action", "update");

		WeldMode[] lista = WeldMode.values();

		for (WeldMode weldMode : lista) {
			weldMode.name();
		}

		model.addAttribute("weldModes", WeldMode.values());

		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

		if (user.get().getTipo().equals("Welder")) {
			model.addAttribute("welder", user.get());
			return "crearSoldador";
		}
		if (user.get().getTipo().equals("Manager")) {
			model.addAttribute("manager", user.get());
			return "crearResponsable";
		} else {
			return "crearUser";
		}
	}

	// con model pasamos datos del controller al jsp(la vista)
	@RequestMapping(value = "/crearUser", method = RequestMethod.GET)
	public String nuevoUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		model.addAttribute("action", "new");

		return "crearUser";
	}

	@RequestMapping(value = "/guardarUser", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {

		if (bindingResult.hasErrors()) {
			return "crearUser";
		} else {

			if (user.getPassword().length() == 60) {
				userRepository.save(user);
			} else {
				// Se encripta la contraseña
				String passw = user.getPassword();
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(passw);
				user.setPassword(encodedPassword);

				// Guarda los datos del formulario en la base de datos
				userRepository.save(user);
			}
			return "redirect:/user";
		}
	}

	@RequestMapping("/quitarUser/{id}")
	public String quitar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

		Optional<User> user = userRepository.findById(id);
		try {
			if (user.isPresent()) {
				userRepository.delete(user.get());
			}
			redirectAttributes.addFlashAttribute("aviso", "Usuario eliminado correctamente");
			redirectAttributes.addFlashAttribute("tipo", "success");
			return "redirect:/user";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("aviso", "No se puede eliminar el usuario");
			redirectAttributes.addFlashAttribute("tipo", "danger");

			return "redirect:/user";
		}
	}
}
