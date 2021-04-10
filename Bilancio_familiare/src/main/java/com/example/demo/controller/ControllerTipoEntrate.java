package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.controller.utils.MyException;
import com.example.demo.model.Tipoentrate;
import com.example.demo.service.DUPLICATE;
import com.example.demo.service.ServiceTipoentrateImpl;

@RestController()
@RequestMapping("/bilancio/tipoentrate")
public class ControllerTipoEntrate {

	@Autowired
	private ServiceTipoentrateImpl serviceTipoentrate;
	@Autowired
	private MyException ex;

	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView form(ModelMap modelmap) {
		ModelAndView m = new ModelAndView("bilancio/tipoentrate/lista");
		modelmap.addAttribute("tipoentrates", serviceTipoentrate.getAll().getBody());
		return m;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.GET)
	public ModelAndView formAggiungi(Model theModel) {
		ModelAndView m = new ModelAndView("bilancio/tipoentrate/aggiungi");
		int last = ((int) serviceTipoentrate.repo.count()) + 1;
		Tipoentrate tipoentrata = theModel.getAttribute("newTipoentrata") != null
				? (Tipoentrate) theModel.getAttribute("newTipoentrata")
				: new Tipoentrate(last);
		theModel.addAttribute("newTipoentrata", tipoentrata);
		m.addObject("lastId", last);
		return m;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.POST)
	public ModelAndView formAggiungiPost(@Valid @ModelAttribute("newTipoentrata") Tipoentrate tipoentrata,
			BindingResult bindingResult) {
		System.out.println(tipoentrata);
		System.out.println("ci sono errori di validazione = " + ((bindingResult.hasErrors() == true) ? "SI" : "NO"));
		bindingResult.getAllErrors().forEach(System.out::println);
		if (!bindingResult.hasErrors()) {
			ResponseEntity<String> rp = serviceTipoentrate.aggiungi(tipoentrata, DUPLICATE.CHECK);
			switch (rp.getStatusCodeValue()) {
			case 200: ex.setTipo("alert alert-success");break;
			case 400: ex.setTipo("alert alert-warning");break;
			}
			ex.setMessaggio(rp.getBody(), new String[] { "L'entrata", tipoentrata.getDescrizione() });
			ModelAndView model = new ModelAndView("bilancio/tipoentrate/aggiungiConfirmed");
			model.addObject("messaggio", ex);
			return model;
		}
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("newTipoentrata", tipoentrata);
		return new ModelAndView("bilancio/tipoentrate/aggiungi", modelMap);
	}

}
