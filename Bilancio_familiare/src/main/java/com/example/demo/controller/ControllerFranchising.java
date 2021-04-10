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
import com.example.demo.model.Franchising;
import com.example.demo.service.ServiceFranchisingImpl;

@RestController()
@RequestMapping("/bilancio/franchising")
public class ControllerFranchising {
	
	@Autowired
	private ServiceFranchisingImpl serviceFranchising;
	@Autowired
	private MyException ex;
	
	Franchising franchising;
	
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView form(ModelMap modelmap) {
		ModelAndView m = new ModelAndView("bilancio/franchising/lista");
		modelmap.addAttribute("franchisings", serviceFranchising.getAll().getBody());
		return m;
	}

	@RequestMapping(value = "aggiungi",  method = RequestMethod.GET)
	public ModelAndView formAggiungi(Model theModel) {
		ModelAndView m = new ModelAndView("bilancio/franchising/aggiungi");
		int last=((int) serviceFranchising.repo.count())+1;
		franchising=theModel.getAttribute("newfranchising")!=null?(Franchising) theModel.getAttribute("newfranchising"):new Franchising(last);
		theModel.addAttribute("newfranchising", franchising);
		m.addObject("lastId", last);
		return m;
	}
	
	@RequestMapping(value = "aggiungi", method = RequestMethod.POST)
	public ModelAndView formAggiungiPost(@Valid @ModelAttribute("newfranchising") Franchising franchising, BindingResult bindingResult) {
		System.out.println(franchising);
		System.out.println("ci sono errori di validazione = "+((bindingResult.hasErrors()==true)?"SI":"NO"));
		bindingResult.getAllErrors().forEach(System.out::println);
		
		if (!bindingResult.hasErrors()) {
			ResponseEntity<String> rp=serviceFranchising.aggiungi(franchising);
			switch (rp.getStatusCodeValue()) {
				case 200: ex.setTipo("alert alert-success");break;
				case 400: ex.setTipo("alert alert-warning");break;	
			}
			ex.setMessaggio(rp.getBody(),new String[] {"Il franchising", "nome :"+franchising.getNome()+"  e  gruppo :"+franchising.getGruppo()});
			ModelAndView model = new ModelAndView("bilancio/franchising/aggiungiConfirmed");
			model.addObject("messaggio", ex);
			return model;
		}
		
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("newfranchising", franchising);
		return new ModelAndView("bilancio/franchising/aggiungi", modelMap);
	}
}
