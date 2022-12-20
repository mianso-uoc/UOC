package com.weldtic.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weldtic.enums.WeldStatus;
import com.weldtic.model.Manager;
import com.weldtic.model.Piece;
import com.weldtic.model.Project;
import com.weldtic.model.Weld;
import com.weldtic.model.Welder;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.ProjectRepository;
import com.weldtic.repository.WeldRepository;
import com.weldtic.repository.WelderRepository;



@Controller
public class WeldController {

	@Autowired
	private WeldRepository<Weld> weldRepository;
	
	@Autowired
	private WelderRepository<Welder> welderRepository;
	
	@Autowired
	private ProjectRepository<Project> projectRepository;
	
	@Autowired
	private PieceRepository<Piece> pieceRepository;
	

	@RequestMapping("/soldadura")
	public String inicio(Model model) {
		List<Weld> welds = weldRepository.findAll();
		model.addAttribute("welds", welds);
				
		return "soldadura";
	}
	
	@RequestMapping("/verSoldadura/{id}")
	public String verSoldadura(@PathVariable Long id, Model model) {
		Optional<Weld> weld = weldRepository.findById(id);
		
		if (weld.isPresent()) {
			model.addAttribute("weld", weld.get());
			model.addAttribute("piece", weld.get().getPiece());
			model.addAttribute("weldStatus",Arrays.asList(WeldStatus.values()));
		}
		model.addAttribute("action", "update");

	
		return "crearSoldadura";
	}

	@RequestMapping(value = "/verPieza/{id}/crearSoldadura", method = RequestMethod.GET)
	public String nuevaPieza(@PathVariable Long id, Model model) {
		Weld weld = new Weld();
		Optional<Piece> piece = pieceRepository.findById(id);
		if (piece.isPresent()) {
			weld.setPiece(piece.get());
			model.addAttribute("weldStatus",Arrays.asList(WeldStatus.values()));

		}
		model.addAttribute("weld", weld);
		model.addAttribute("piece", piece);
		model.addAttribute("action", "new");
		return "crearSoldadura";
	}
	
	@RequestMapping(value= "/guardarSoldadura", method = RequestMethod.POST)
	public String submit(@ModelAttribute("weld") Weld weld, ModelMap model) {
		
		//Guarda los datos del formulario en la base de datos
		weldRepository.save(weld);
		
		return  "redirect:/verPieza/" + weld.getPiece().getId();
//		return "redirect:/inicio";
	}
	
	@RequestMapping("/verPieza/{id}/quitarSoldadura/{idWeld}")
	public String quitar(@PathVariable Long id,@PathVariable Long idWeld, Model model) {
		
		Optional<Weld> weld = weldRepository.findById(idWeld);
		
		if (weld.isPresent()){
			weldRepository.delete(weld.get());
		}
		return "redirect:/verPieza/" + id;
	}
	
	@RequestMapping("/alarma")
	public String alarma(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Manager currentPrincipalName = (Manager) authentication.getPrincipal();
		
		List<Weld> welds = weldRepository.findByManagerWithAlarm(currentPrincipalName.getId());
		model.addAttribute("welds", welds);
				
		return "verAlarma";
	}
}
