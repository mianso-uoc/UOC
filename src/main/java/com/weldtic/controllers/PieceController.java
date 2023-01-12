package com.weldtic.controllers;

import java.util.ArrayList;
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

import com.weldtic.model.Piece;
import com.weldtic.model.Project;
import com.weldtic.model.ProjectMachine;
import com.weldtic.model.Weld;
import com.weldtic.model.Welder;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.ProjectRepository;
import com.weldtic.repository.WeldRepository;
import com.weldtic.repository.WelderRepository;

@Controller
public class PieceController {

	@Autowired
	private WeldRepository<Weld> weldRepository;

	@Autowired
	private WelderRepository<Welder> welderRepository;

	@Autowired
	private ProjectRepository<Project> projectRepository;

	@Autowired
	private PieceRepository<Piece> pieceRepository;

	@RequestMapping("/pieza")
	public String inicio(Model model) {
		List<Piece> pieces = pieceRepository.findAll();
		model.addAttribute("pieces", pieces);

		return "pieza";
	}

	@RequestMapping("/verPieza/{id}")
	public String verPieza(@PathVariable Long id, Model model) {
		Optional<Piece> piece = pieceRepository.findById(id);
		List<Weld> welds = new ArrayList<>();

		if (piece.isPresent()) {
			model.addAttribute("piece", piece.get());
			List<Welder> welders = welderRepository.findWelderByCompany(piece.get().getWelder().getCompany());
			model.addAttribute("welders", welders);
			model.addAttribute("project", piece.get().getProjectMachine().getProject());
			welds = weldRepository.findByPiece(piece.get());
			model.addAttribute("welds", welds);
		}
		model.addAttribute("action", "update");

		return "crearPieza";
	}

	@RequestMapping("/soldador/verPieza/{id}")
	public String soldadorVerPieza(@PathVariable Long id, Model model) {
		Optional<Piece> piece = pieceRepository.findById(id);
		List<Weld> welds = new ArrayList<>();

		if (piece.isPresent()) {
			model.addAttribute("piece", piece.get());
			model.addAttribute("project", piece.get().getProjectMachine().getProject());
			welds = weldRepository.findByPiece(piece.get());
			model.addAttribute("welds", welds);
		}
		return "verPieza";
	}

	@RequestMapping(value = "/proyecto/{id}/crearPieza", method = RequestMethod.GET)
	public String nuevaPieza(@PathVariable Long id, Model model) {
		Piece piece = new Piece();
		Optional<Project> project = projectRepository.findById(id);
		if (project.isPresent()) {
			ProjectMachine pm = new ProjectMachine();
			pm.setProject(project.get());
			piece.setProjectMachine(pm);
			List<Welder> welders = welderRepository.findWelderByCompany(project.get().getManager().getCompany());
			model.addAttribute("welders", welders);
		}
		model.addAttribute("piece", piece);
		model.addAttribute("action", "new");
		model.addAttribute("project", project.get());

		return "crearPieza";
	}

	@RequestMapping(value = "/guardarPieza", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("piece") Piece piece, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {
		// Guarda los datos del formulario en la base de datos
		if (bindingResult.hasErrors()) {
			model.addAttribute("project", piece.getProjectMachine().getProject());
			List<Welder> welders = welderRepository.findWelderByCompany(piece.getWelder().getCompany());
			model.addAttribute("welders", welders);
			return "crearPieza";
		} else {
			try {
				pieceRepository.save(piece);
				redirectAttributes.addFlashAttribute("aviso", "Pieza guardada correctamente");
				redirectAttributes.addFlashAttribute("tipo", "success");
				return "redirect:/verProyecto/" + piece.getProjectMachine().getProject().getId();
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("aviso", "No se puede guardar la pieza");
				redirectAttributes.addFlashAttribute("tipo", "danger");
				return "redirect:/verProyecto/" + piece.getProjectMachine().getProject().getId();
			}
		}
	}

	@RequestMapping("/verProyecto/{id}/quitarPieza/{idPiece}")
	public String quitar(@PathVariable Long id, @PathVariable Long idPiece, Model model, RedirectAttributes redirectAttributes) {

		Optional<Piece> piece = pieceRepository.findById(idPiece);

		try {
		if (piece.isPresent()) {
			pieceRepository.delete(piece.get());
		}
		redirectAttributes.addFlashAttribute("aviso", "Pieza borrada correctamente");
		redirectAttributes.addFlashAttribute("tipo", "success");
		return "redirect:/verProyecto/" + id;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("aviso", "No se puede eliminar la pieza");
			redirectAttributes.addFlashAttribute("tipo", "danger");
		return "redirect:/verProyecto/" + id;
		}
	}
}
