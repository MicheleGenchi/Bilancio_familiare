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
import com.example.demo.model.Tipologie;
import com.example.demo.service.ServiceTipologieImpl;

@RestController()
@RequestMapping("/bilancio/tipologie")
public class ControllerTipologie {
	
	@Autowired
	private ServiceTipologieImpl serviceTipologie;
	@Autowired
	private MyException ex;
	
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView form(ModelMap modelmap) {
		ModelAndView m = new ModelAndView("bilancio/tipologie/lista");
		modelmap.addAttribute("tipologies", serviceTipologie.getAll().getBody());
		return m;
	}
	
	@RequestMapping(value = "aggiungi",  method = RequestMethod.GET)
	public ModelAndView formAggiungi(Model theModel) {
		ModelAndView m = new ModelAndView("bilancio/tipologie/aggiungi");
		int last=((int) serviceTipologie.repo.count())+1;
		Tipologie tipologia=theModel.getAttribute("newtipologia")!=null?(Tipologie) theModel.getAttribute("newtipologia"):new Tipologie(last);
		theModel.addAttribute("newtipologia", tipologia);
		m.addObject("lastId", last);
		return m;
	}
	
	@RequestMapping(value = "aggiungi", method = RequestMethod.POST)
	public ModelAndView formAggiungiPost(@Valid @ModelAttribute("newtipologia") Tipologie tipologia, BindingResult bindingResult) {
		System.out.println(tipologia);
		if (!bindingResult.hasErrors()) {
			ResponseEntity<String> rp=serviceTipologie.aggiungi(tipologia);
			switch (rp.getStatusCodeValue()) {
				case 200: ex.setTipo("alert alert-success");break;
				case 400: ex.setTipo("alert alert-warning");break;	
			}
	
		ex.setMessaggio(rp.getBody(), new String[] {"La tipologia", tipologia.getNome()});
		ModelAndView model = new ModelAndView("bilancio/tipologie/aggiungiConfirmed");
		model.addObject("messaggio", ex);
		return model;
		}
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("newtipologia", tipologia);
		return new ModelAndView("bilancio/tipologie/aggiungi", modelMap);
	}
	
}
