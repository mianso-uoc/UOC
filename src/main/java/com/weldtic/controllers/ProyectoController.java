package com.weldtic.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.weldtic.model.Manager;
import com.weldtic.model.Piece;
import com.weldtic.model.Project;
import com.weldtic.model.ProjectMachine;
import com.weldtic.model.User;
import com.weldtic.repository.MachineRepository;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.ProjectMachineRepository;
import com.weldtic.repository.ProjectRepository;
import com.weldtic.repository.UserRepository;

@Controller
public class ProyectoController {

	@Autowired
	private ProjectMachineRepository<ProjectMachine> projectMachineRepository;

	@Autowired
	private MachineRepository<Machine> machineRepository;

	@Autowired
	private ProjectRepository<Project> projectRepository;

	@Autowired
	private PieceRepository<Piece> pieceRepository;

	@Autowired
	private UserRepository<User> userRepository;

	@RequestMapping("/proyecto")
	public String inicio(Model model) {
		List<Project> projects = projectRepository.findAll();
		model.addAttribute("projects", projects);

		return "inicioResponsable";
	}

	@RequestMapping("/verProyecto/{id}")
	public String inicio(@PathVariable Long id, Model model) {
		Optional<Project> project = projectRepository.findById(id);
		// se crea una lista de momento vacia de las máquinas que no están en el
		// proyecto
		List<Machine> machineNotInProject = new ArrayList<>();
		List<Piece> pieces = new ArrayList<>();

		if (project.isPresent()) {
			model.addAttribute("project", project.get());
			// Se crea company con la info del proyecto, que tiene manager y manager tiene
			// empresa
			Company company = project.get().getManager().getCompany();
			// Busca todas las máquinas de la compañia
			List<Machine> machines = machineRepository.findByCompany(company);
			// Recorre la lista machines, en cada iteración el objeto sería machine
			for (Machine machine : machines) {
				// se crea una lista de projectMachines con la funcion que se ha creado ad hoc
				// en el repositorio.
				// Se le pasa una machine de la lista machines en cada iteración, y el proyecto
				// La list lista se crea/destruye en cada iteración porque esta dentro del for
				List<ProjectMachine> lista = projectMachineRepository.findByMachineAndProject(machine, project.get());
				if (lista.isEmpty()) {
					machineNotInProject.add(machine);
				}
			}
			model.addAttribute("machines", machineNotInProject);

			Set<ProjectMachine> pm = project.get().getProjectMachine();
//			List<ProjectMachine> pm = projectMachineRepository.findAll();
			for (ProjectMachine projectMachine : pm) {
//				if (projectMachine.getProject().getId() == id) {
				pieces.addAll(pieceRepository.findByProjectMachine(projectMachine));
//				}
			}
			model.addAttribute("pieces", pieces);
		}

		model.addAttribute("action", "update");

		return "crearProyecto";
	}

	@RequestMapping(value = "/crearProyecto", method = RequestMethod.GET)
	public String nuevoProyecto(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);

		model.addAttribute("action", "new");

		return "crearProyecto";
	}

	@RequestMapping(value = "/guardarProyecto", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("project") Project project, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {
		// Se comprueba que no hay errores en el formulario
		if (bindingResult.hasErrors()) {
			return "crearProyecto";
		} else {
			try {
				// Se guarda el objeto del user logeado
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Manager currentPrincipalName = (Manager) authentication.getPrincipal();
				project.setManager(currentPrincipalName);

				// Guarda los datos del formulario en la base de datos
				projectRepository.save(project);
				redirectAttributes.addFlashAttribute("aviso", "Proyecto guardado correctamente");
				redirectAttributes.addFlashAttribute("tipo", "success");
				

				return "redirect:/inicio";
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("aviso", "El proyecto no se ha podido guardar");
				redirectAttributes.addFlashAttribute("tipo", "success");
				return "redirect:/proyecto";
			}
		}
	}

	@RequestMapping("/verProyecto/{id}/anadir/{idMachine}")
	public String anadir(@PathVariable Long id, @PathVariable Long idMachine, Model model) {
		Optional<Project> project = projectRepository.findById(id);
		Optional<Machine> machine = machineRepository.findById(idMachine);

		if (project.isPresent() && machine.isPresent()) {
			ProjectMachine projectMachine = new ProjectMachine();
			projectMachine.setProject(project.get());
			projectMachine.setMachine(machine.get());
			projectMachineRepository.save(projectMachine);
		}
		return "redirect:/verProyecto/" + id;
	}

	@RequestMapping("/verProyecto/{id}/quitar/{idMachine}")
	public String quitar(@PathVariable Long id, @PathVariable Long idMachine, Model model) {
		Optional<Project> project = projectRepository.findById(id);
		Optional<Machine> machine = machineRepository.findById(idMachine);

		if (project.isPresent() && machine.isPresent()) {
			ProjectMachine projectMachine = new ProjectMachine();
			projectMachine.setProject(project.get());
			projectMachine.setMachine(machine.get());
			projectMachineRepository.delete(projectMachine);
		}
		return "redirect:/verProyecto/" + id;
	}
}
