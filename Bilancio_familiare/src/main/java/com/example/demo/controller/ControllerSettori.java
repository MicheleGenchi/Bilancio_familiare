package com.example.demo.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.utils.MyException;
import com.example.demo.model.Settori;
import com.example.demo.model.Tipologie;
import com.example.demo.model.Utils;
import com.example.demo.service.DUPLICATE;
import com.example.demo.service.ServiceSettoriImpl;
import com.example.demo.service.ServiceTipologieImpl;

@RestController()
@RequestMapping("/bilancio/settori")
public class ControllerSettori {

	@Autowired
	private ServiceSettoriImpl serviceSettori;
	@Autowired
	private ServiceTipologieImpl serviceTipologie;
	@Autowired
	private MyException ex;
	
	Tipologie tipologia;
	Settori settore;

	private final static Logger logger=LoggerFactory.getLogger(ControllerSettori.class);
	
	@RequestMapping(value = "seleziona", method = RequestMethod.GET)
	public ModelAndView form(ModelMap modelmap) throws MyException {

		ModelAndView m = new ModelAndView();
		List<Settori> listaSettori=serviceSettori.getAll().getBody();
		List<Tipologie> listaTipologie=serviceTipologie.getAll().getBody();
		if (listaSettori.isEmpty() | listaTipologie.isEmpty()) {
			m.setViewName("home");
			ex.setTipo("alert alert-warning");
			ex.setMessaggio("TipoligieSettori_vuoti", null);
			m.addObject("errore", ex);
			return m;
		} 
		m.setViewName("bilancio/settori/selezione");
		tipologia = new Tipologie();
		settore = new Settori();
		modelmap.addAttribute("settore", settore);
		modelmap.addAttribute("tipologia", tipologia);
		m.addObject("listaSettori", serviceSettori.getAll().getBody());
		m.addObject("listaTipologie", serviceTipologie.getAll().getBody());
	  return m;
	}
	
	@RequestMapping(value = "lista", method = RequestMethod.POST)
	public ModelAndView formPost(@ModelAttribute("tipologia") Tipologie tipologia,
			@ModelAttribute("settore") Settori settore) {
			
			if (tipologia!=null & settore!=null) {
				tipologia.getSettoris().forEach(s -> {
				serviceSettori.aggiungiSettoreTipologia(s, settore.getTipologies());
				});
			}
					
			ModelAndView model = new ModelAndView("bilancio/settori/lista");
			return model;
	}

	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView formPost() {
			Settori settore=new Settori();
			settore.setTipologies(serviceTipologie.getAll().getBody());

			ModelAndView model = new ModelAndView("bilancio/settori/lista");
			model.addObject("settore", settore);
			return model;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.GET)
	public ModelAndView formAggiungi(Model theModel) {
		ModelAndView m = new ModelAndView("bilancio/settori/aggiungi");
		int last=((int) serviceSettori.repo.count())+1;
		m.addObject("lastId", last);
		settore=theModel.getAttribute("newsettore")!=null?(Settori) theModel.getAttribute("newsettore"):new Settori(last);
		theModel.addAttribute("newsettore", settore);
		return m;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.POST)
	public ModelAndView formAggiungiPost(@Valid @ModelAttribute("newsettore") Settori settore, BindingResult bindingResult) {
		System.out.println(settore);
		System.out.println("ci sono errori di validazione = "+((bindingResult.hasErrors()==true)?"SI":"NO"));
		bindingResult.getAllErrors().forEach(System.out::println);
		
		if (!bindingResult.hasErrors()) {
			ResponseEntity<String> rp=serviceSettori.aggiungi(settore, DUPLICATE.CHECK);
			switch (rp.getStatusCodeValue()) {
				case 200: ex.setTipo("alert alert-success");break;
				case 400: ex.setTipo("alert alert-warning");break;	
			}
			ex.setMessaggio(rp.getBody(), new String[] {"Il settore", settore.getNome()});
			ModelAndView model = new ModelAndView("bilancio/settori/aggiungiConfirmed");
			model.addObject("messaggio", ex);
			return model;
		}
		
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("newsettore", settore);
		return new ModelAndView("bilancio/settori/aggiungi", modelMap);
	}
	
	@RequestMapping(value = "intestazione", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getHead() {
		List<String> head=new LinkedList<>();
		Utils.getHead(Settori.class, head);
		if (head.size()>0) {
			head.forEach(v -> System.out.printf("val=%s\n",v));
			return new ResponseEntity<List<String>>(head, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(head, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "settoriPerTipologia/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Settori>> getSettoriPerTipologia(@PathVariable(value = "id") int idTipologia) {
		List<Settori> dati=new LinkedList<>();
		//System.out.println("ID Tipologia  : "+idTipologia);
		Tipologie tipologia=serviceTipologie.repo.findById(idTipologia).get();
		if (tipologia.getSettoris().size()>0) { 
			tipologia.getSettoris().forEach(s -> dati.add(s));
			Collections.sort(dati, Comparator.comparing(Settori::getNome));
			dati.forEach((s) -> System.out.printf("%s", s));
			return new ResponseEntity<List<Settori>>(dati, HttpStatus.OK);
		}
		return new ResponseEntity<List<Settori>>(dati, HttpStatus.BAD_REQUEST);
	}
}
