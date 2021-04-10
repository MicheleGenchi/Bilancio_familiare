package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Intestazione;

@Controller()
public class ControllerPage {

	@Autowired 
	Intestazione intestazione;
	
	@GetMapping("/bilancio")
	public ModelAndView home(@ModelAttribute String messaggio) {
		
		Map<String, Object> myMap=new HashMap<>();
		myMap.put("dataCorrente", intestazione.getDataCorrente());
		myMap.put("mioNome", intestazione.getCognome()+" "+intestazione.getNome());
		myMap.put("lingua", intestazione.getLingua());
		myMap.put("messaggio", messaggio);
		return new ModelAndView("home", myMap);
	}

}
