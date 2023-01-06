package com.weldtic.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weldtic.model.Company;
import com.weldtic.model.Machine;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.MachineRepository;

@Controller
public class CompanyController {
	@Autowired
	private CompanyRepository<Company> companyRepository;

	@Autowired
	private MachineRepository<Machine> machineRepository;

	@RequestMapping("/verEmpresa")
	public String inicio(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);

//		Company company = new Company();
//		company.setName("Twitter");
//		companyRepository.save(company);

		return "verEmpresa";
	}

	// SELECT de base de datos con la id de company
	@RequestMapping("/verEmpresa/{id}")
	public String inicio(@PathVariable Long id, Model model) {
		Optional<Company> company = companyRepository.findById(id);

		List<Machine> machines = machineRepository.findAll();
		model.addAttribute("machines", machines);
		if (company.isPresent()) {
			model.addAttribute("company", company.get());
		}
		model.addAttribute("action", "update");
		return "crearEmpresa";
	}

	// con model pasamos datos del controller al jsp(la vista)
	@RequestMapping(value = "/crearEmpresa", method = RequestMethod.GET)
	public String nuevaEmpresa(Model model) {
		Company company = new Company();
		model.addAttribute("company", company);

		model.addAttribute("action", "new");

		return "crearEmpresa";
	}

	/*
	 * @RequestMapping(value = "/crearEmpresa", method = RequestMethod.GET) public
	 * ModelAndView showForm() { return new ModelAndView("company", "crearEmpresa",
	 * new Company()); }
	 */

	@RequestMapping(value = "/guardarEmpresa", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "crearEmpresa";
		} else {
			try {
				// Guarda los datos del formulario en la base de datos
				companyRepository.save(company);
				redirectAttributes.addFlashAttribute("aviso", "Empresa guardada correctamente");
				redirectAttributes.addFlashAttribute("tipo", "success");
				return "redirect:/verEmpresa";

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("aviso", "No se ha podido guardar la empresa");
				redirectAttributes.addFlashAttribute("tipo", "danger");
				return "redirect:/verEmpresa";
			}
		}
	}

	@RequestMapping("/quitarEmpresa/{id}")
	public String quitar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

		Optional<Company> company = companyRepository.findById(id);
		try {
			if (company.isPresent()) {
				companyRepository.delete(company.get());
			}
			redirectAttributes.addFlashAttribute("aviso", "Empresa eliminada correctamente");
			redirectAttributes.addFlashAttribute("tipo", "success");
			return "redirect:/verEmpresa";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("aviso", "No se puede eliminar la empresa");
			redirectAttributes.addFlashAttribute("tipo", "danger");

			return "redirect:/verEmpresa";
		}

	}
}
