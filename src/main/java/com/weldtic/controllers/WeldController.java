package com.weldtic.controllers;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.weldtic.enums.WeldStatus;
import com.weldtic.model.Alarm;
import com.weldtic.model.Manager;
import com.weldtic.model.Piece;
import com.weldtic.model.Reading;
import com.weldtic.model.Weld;
import com.weldtic.repository.AlarmRepository;
import com.weldtic.repository.PieceRepository;
import com.weldtic.repository.ReadingRepository;
import com.weldtic.repository.WeldRepository;

@Controller
public class WeldController {

	@Autowired
	private WeldRepository<Weld> weldRepository;

	@Autowired
	private AlarmRepository<Alarm> alarmRepository;

	@Autowired
	private ReadingRepository<Reading> readingRepository;

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
			model.addAttribute("weldStatus", Arrays.asList(WeldStatus.values()));
			List<Reading> readings = readingRepository.findByWeldOrderByDateAsc(weld.get());
			if (readings.isEmpty())
				;
			else {
				model.addAttribute("readings", readings);
				datosGraficos(model, readings, weld.get());
			}
		}
		model.addAttribute("action", "update");

		return "crearSoldadura";
	}

	@RequestMapping("/soldador/verSoldadura/{id}")
	public String soldadorVerSoldadura(@PathVariable Long id, Model model) {
		Optional<Weld> weld = weldRepository.findById(id);

		if (weld.isPresent()) {
			model.addAttribute("weld", weld.get());
			model.addAttribute("piece", weld.get().getPiece());
			model.addAttribute("weldStatus", Arrays.asList(WeldStatus.values()));
		}
		model.addAttribute("action", "update");

		return "verSoldadura";
	}

	@RequestMapping(value = "/verPieza/{id}/crearSoldadura", method = RequestMethod.GET)
	public String nuevaPieza(@PathVariable Long id, Model model) {
		Weld weld = new Weld();
		Optional<Piece> piece = pieceRepository.findById(id);
		if (piece.isPresent()) {
			weld.setPiece(piece.get());
			// model.addAttribute("weldStatus",Arrays.asList(WeldStatus.values()));
			if (weld.getState() == null) {
				weld.setState("CREADA");
			}
		}
		model.addAttribute("weld", weld);
		model.addAttribute("piece", piece);
		model.addAttribute("action", "new");
		return "crearSoldadura";
	}

	@RequestMapping(value = "/guardarSoldadura", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("weld") Weld weld, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {

		// Guarda los datos del formulario en la base de datos
		if (bindingResult.hasErrors()) {
			return "crearSoldadura";
		} else {
			try {
				weldRepository.save(weld);
				redirectAttributes.addFlashAttribute("aviso", "Soldadura guardada correctamente");
				redirectAttributes.addFlashAttribute("tipo", "success");
				return "redirect:/verPieza/" + weld.getPiece().getId();
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("aviso", "No se ha podido guardar la soldadura");
				redirectAttributes.addFlashAttribute("tipo", "danger");
				return "redirect:/verPieza/" + weld.getPiece().getId();
			}
		}
	}

	@RequestMapping("/verPieza/{id}/quitarSoldadura/{idWeld}")
	public String quitar(@PathVariable Long id, @PathVariable Long idWeld, Model model,
			RedirectAttributes redirectAttributes) {

		Optional<Weld> weld = weldRepository.findById(idWeld);
		try {
			if (weld.isPresent()) {
				weldRepository.delete(weld.get());
				redirectAttributes.addFlashAttribute("aviso", "La soldadura se ha eliminado correctamente");
				redirectAttributes.addFlashAttribute("tipo", "success");

			}
			return "redirect:/verPieza/" + id;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("aviso", "La soldadura no se ha podido eliminar");
			redirectAttributes.addFlashAttribute("tipo", "danger");
			return "redirect:/verPieza/" + id;
		}
	}

	@RequestMapping("/alarma")
	public String alarma(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Manager currentPrincipalName = (Manager) authentication.getPrincipal();

		List<Weld> welds = weldRepository.findByManagerWithAlarm(currentPrincipalName.getId());
		model.addAttribute("welds", welds);

		return "verAlarma";
	}

	@RequestMapping("/iniciarSoldadura/{id}")
	public String soldadorIniciarSoldadura(@PathVariable Long id, Model model) {
		Optional<Weld> weld = weldRepository.findById(id);

		if (weld.isPresent() && WeldStatus.CREADA.toString().equals(weld.get().getState())) {
			weld.get().setState(WeldStatus.INICIADA.toString());
			weldRepository.save(weld.get());
		}

		return "redirect:/soldador/verSoldadura/" + id;
	}

	@RequestMapping("/pararSoldadura/{id}")
	public String soldadorPararSoldadura(@PathVariable Long id, Model model) {
		Optional<Weld> weld = weldRepository.findById(id);
		int a = 0;

		if (weld.isPresent() && WeldStatus.INICIADA.toString().equals(weld.get().getState())) {
			weld.get().setState(WeldStatus.FINALIZADA.toString());
			weldRepository.save(weld.get());
			for (int n = 0; n < 10; n++) {
				readingRepository.save(lectura(id, n));
				if (alarmaBoolean(lectura(id, n), weld) == true) {
					a = 1;
				}
			}
			if (a == 1) {
				alarmRepository.save(alarma(weld));
			}
		}

		return "redirect:/soldador/verPieza/" + weld.get().getPiece().getId();
	}

	private Reading lectura(Long id, int sec) {
		Weld weld = new Weld();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		weld.setId(id);
		Reading reading = new Reading();
		float a = 100;
		float v = 20;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.SECOND, sec);
		reading.setWeld(weld);
		reading.setAmp(a);
		reading.setVolt(v);
		reading.setDate(new Timestamp(cal.getTimeInMillis()));
		return (reading);
	}

	private Alarm alarma(Optional<Weld> weld) {

		Alarm alarm = new Alarm();

		alarm.setWeld(weld.get());
		alarm.setName("Soldadura defectuosa");
		alarm.setInfo("Soldadura con parametros fuera de la tolerancia");

		return alarm;
	}

	private boolean alarmaBoolean(Reading reading, Optional<Weld> weld) {

		float porcentaje = weld.get().getTolerance();
		porcentaje = porcentaje / 100;
		float amp = weld.get().getAmp();
		float ampSup = amp + amp * porcentaje;
		float ampInf = amp - amp * porcentaje;
		float volt = weld.get().getVolt();
		float voltSup = volt + volt * porcentaje;
		float voltInf = volt - volt * porcentaje;

		if (reading.getVolt() > voltSup || reading.getAmp() > ampSup || reading.getVolt() < voltInf
				|| reading.getAmp() < ampInf) {
			return true;
		} else
			return false;
	}

	private void datosGraficos(Model model, List<Reading> readings, Weld weld) {
		String chartVol = "";
		String chartVolMax = "";
		String chartVolMin = "";
		String chartAmp = "";
		String chartAmpMax = "";
		String chartAmpMin = "";
		for (int n = 0; n < readings.size(); n++) {

			Reading reading = readings.get(n);
			chartVol += "{\"x\":" + reading.getDate().getTime() + ",\"y\":" + reading.getVolt() + "}";
			chartAmp += "{\"x\":" + reading.getDate().getTime() + ",\"y\":" + reading.getAmp() + "}";
			if (n != readings.size() - 1) {
				chartVol += ",";
				chartAmp += ",";
			}
		}
		float voltMax = weld.getVolt() + ((float) weld.getTolerance() / 100) * weld.getVolt();
		chartVolMax += "{\"x\":" + readings.get(0).getDate().getTime() + ",\"y\":" + voltMax + "},";
		chartVolMax += "{\"x\":" + readings.get(readings.size() - 1).getDate().getTime() + ",\"y\":" + voltMax + "}";

		float voltMin = weld.getVolt() - ((float) weld.getTolerance() / 100) * weld.getVolt();
		chartVolMin += "{\"x\":" + readings.get(0).getDate().getTime() + ",\"y\":" + voltMin + "},";
		chartVolMin += "{\"x\":" + readings.get(readings.size() - 1).getDate().getTime() + ",\"y\":" + voltMin + "}";

		float ampMax = weld.getAmp() + ((float) weld.getTolerance() / 100) * weld.getAmp();
		chartAmpMax += "{\"x\":" + readings.get(0).getDate().getTime() + ",\"y\":" + ampMax + "},";
		chartAmpMax += "{\"x\":" + readings.get(readings.size() - 1).getDate().getTime() + ",\"y\":" + ampMax + "}";

		float ampMin = weld.getAmp() - ((float) weld.getTolerance() / 100) * weld.getAmp();
		chartAmpMin += "{\"x\":" + readings.get(0).getDate().getTime() + ",\"y\":" + ampMin + "},";
		chartAmpMin += "{\"x\":" + readings.get(readings.size() - 1).getDate().getTime() + ",\"y\":" + ampMin + "}";

		model.addAttribute("datosVoltMax", chartVolMax);
		model.addAttribute("datosVoltMin", chartVolMin);
		model.addAttribute("datosVolt", chartVol);
		model.addAttribute("datosAmpMax", chartAmpMax);
		model.addAttribute("datosAmpMin", chartAmpMin);
		model.addAttribute("datosAmp", chartAmp);
		model.addAttribute("ampMax", ampMax * 1.1);
		model.addAttribute("ampMin", ampMin * 0.9);
		model.addAttribute("voltMin", voltMin * 0.9);
		model.addAttribute("voltMax", voltMax * 1.1);

	}
}
