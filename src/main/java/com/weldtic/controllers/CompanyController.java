package com.weldtic.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weldtic.model.Company;
import com.weldtic.model.Machine;
import com.weldtic.model.Piece;
import com.weldtic.repository.CompanyRepository;
import com.weldtic.repository.MachineRepository;

@Controller
public class CompanyController {
	@Autowired
	private CompanyRepository<Company> companyRepository;
	
	@Autowired
	private MachineRepository<Machine> machineRepository;
	
	@RequestMapping("/company")
	public String inicio(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);
		
//		Company company = new Company();
//		company.setName("Twitter");
//		companyRepository.save(company);
		
		return "company";
	}
	//SELECT de base de datos con la id de company
	@RequestMapping("/verEmpresa/{id}")
	public String inicio(@PathVariable Long id, Model model) {
		Optional<Company> company = companyRepository.findById(id);
           		System.out.println(company.get().getMachines());
		
		List<Machine> machines = machineRepository.findAll();
		model.addAttribute("machines", machines);
		if (company.isPresent()) {
			model.addAttribute("company", company.get());
		}
		model.addAttribute("action", "update");
		return "crearEmpresa";
	}
	//con model pasamos datos del controller al jsp(la vista)
	@RequestMapping(value = "/crearEmpresa", method = RequestMethod.GET)
	public String nuevaEmpresa(Model model) {
		Company company = new Company();
		model.addAttribute("company", company);
		
		model.addAttribute("action", "new");
		
		return "crearEmpresa";
	}
	
	/*@RequestMapping(value = "/crearEmpresa", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("company", "crearEmpresa", new Company());
	}*/
	
	@RequestMapping(value= "/guardarEmpresa", method = RequestMethod.POST)
	public String submit(@ModelAttribute("company") Company company,ModelMap model) {

		//Guarda los datos del formulario en la base de datos
		companyRepository.save(company);
		

		return  "redirect:/company";
	}
	
	@RequestMapping("/quitarEmpresa/{id}")
	public String quitar(@PathVariable Long id, Model model) {
		
		Optional<Company> company = companyRepository.findById(id);
		
		if (company.isPresent()){
			companyRepository.delete(company.get());
		}
		return "redirect:/company";
	}
}
