 package com.example.demo.controller;

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
import com.example.demo.model.Negozi;
import com.example.demo.model.Utils;
import com.example.demo.service.DUPLICATE;
import com.example.demo.service.ServiceFranchisingImpl;
import com.example.demo.service.ServiceNegoziImpl;
import com.example.demo.service.ServiceTipologieImpl;

@RestController()
@RequestMapping("/bilancio/negozi")
public class ControllerNegozi {

	@Autowired
	private ServiceNegoziImpl serviceNegozi;
	@Autowired
	private ServiceFranchisingImpl serviceFranchising;
	@Autowired
	private ServiceTipologieImpl serviceTipologie;
	@Autowired
	private MyException ex;

	private Negozi negozio;

	private final static Logger logger = LoggerFactory.getLogger(ControllerNegozi.class);
	
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView form(ModelMap modelmap) {
		ModelAndView m = new ModelAndView("bilancio/negozi/lista");
		modelmap.addAttribute("negozis", serviceNegozi.getAll().getBody());
		return m;
	}
	
	@RequestMapping(value = "listaFiltrata", method = RequestMethod.GET)
	public ModelAndView formFilter(ModelMap modelmap) {
		ModelAndView m = new ModelAndView("bilancio/negozi/listaFiltrata");
		modelmap.addAttribute("tipologies", serviceTipologie.getAll().getBody());
		modelmap.addAttribute("franchisings", serviceFranchising.getAll().getBody());
		return m;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.GET)
	public ModelAndView form(Model theModel) throws MyException {
		ModelAndView m = new ModelAndView();
		m.setViewName("bilancio/negozi/aggiungi");
		int last = ((int) serviceNegozi.repo.count()) + 1;
		m.addObject("lastId", last);
		negozio = theModel.getAttribute("newnegozio") != null ? (Negozi) theModel.getAttribute("newnegozio")
				: new Negozi(last);
		theModel.addAttribute("newnegozio", negozio);
		m.addObject("listaFranchising", serviceFranchising.getAll().getBody());
		m.addObject("listaTipologie", serviceTipologie.getAll().getBody());
		return m;
	}

	@RequestMapping(value = "aggiungi", method = RequestMethod.POST)
	public ModelAndView formAggiungiPost(@Valid @ModelAttribute("newnegozio") Negozi negozio,
			BindingResult bindingResult) {
		System.out.println(negozio);
//		System.out.printf("Ci sono %s errori di inserimento\n",bindingResult.getFieldErrorCount());
//		bindingResult.getAllErrors().forEach(e -> System.out.println("errore :"+e));
		if (!bindingResult.hasErrors()) {
			ResponseEntity<String> rp = serviceNegozi.aggiungi(negozio, DUPLICATE.CHECK);
			switch (rp.getStatusCodeValue()) {
			case 200:
				ex.setTipo("alert alert-success");
				ex.setMessaggio(rp.getBody(), new String[] {"Il negozio ", negozio.getNome()});
				break;
			case 400:
				ex.setTipo("alert alert-warning");
				ex.setMessaggio(rp.getBody(), new String[] { negozio.getIndirizzo() });
				break;
			}
			ModelAndView model = new ModelAndView("bilancio/negozi/aggiungiConfirmed");
			if (negozio.getTipologie()!=null)
				model.addObject("tipologia", negozio.getTipologie().getNome());
			if (negozio.getFranchising()!=null)
				model.addObject("franchising", negozio.getFranchising().getNome());
			model.addObject("messaggio", ex);
			return model;
		}
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("newnegozio", negozio);
		return new ModelAndView("bilancio/negozi/aggiungi", modelMap);
	}

	@RequestMapping(value = "intestazione", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getHead() {
		List<String> head=new LinkedList<>();
		Utils.getHead(Negozi.class, head);
		if (head.size()>0) {
			head.forEach(v -> System.out.printf("val=%s\n",v));
			return new ResponseEntity<List<String>>(head, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(head, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "negoziByTipFra/{tipologia}/{franchising}", method = RequestMethod.GET)
	public ResponseEntity<List<Negozi>> getByTipFra(@PathVariable(value = "tipologia") Integer tipologia,
			@PathVariable(value = "franchising") Integer franchising) {
		return serviceNegozi.getByTipFra(tipologia, franchising);
	}
}
