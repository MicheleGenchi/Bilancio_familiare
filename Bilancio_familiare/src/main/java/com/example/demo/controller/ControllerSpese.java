package com.example.demo.controller;

import java.util.Date;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.controller.utils.JTableResult;
import com.example.demo.controller.utils.MyException;
import com.example.demo.model.EntrateUscite;
import com.example.demo.model.Intestazione;
import com.example.demo.model.Negozi;
import com.example.demo.model.Utils;
import com.example.demo.service.ServiceSpeseImpl;


@RestController()
@RequestMapping("/bilancio/spese")
public class ControllerSpese {

	@Autowired 
	Intestazione intestazione;
	
	@Autowired
	ServiceSpeseImpl serviceSpese;
	
	@Autowired
	private MyException ex;
	
    @InitBinder
      	public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
    		    new PropertyEditorSupport() {
    		        public void setAsText(String value) {
    		            try {
    		                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
    		                setValue(new Timestamp(parsedDate.getTime()));
    		            } catch (ParseException e) {
    		                setValue(null);
    		            }
    		        }
    		    });
    }
    
	@RequestMapping(value = "tabella", method = RequestMethod.GET)
	public ModelAndView tableSpese() {
		return new ModelAndView("bilancio/spese/tabella");
	}
	
	@RequestMapping(value = "intestazione", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getHead() {
		List<String> head=new LinkedList<>();
		Utils.getHead(EntrateUscite.class, head);
		if (head.size()>0) {
			head.forEach(v -> System.out.printf("field=%s\n",v));
			return new ResponseEntity<List<String>>(head, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(head, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "lista/{startDate}/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<?> getSpeseByDate(@PathVariable(value = "startDate")  Date startDate, 
													@PathVariable(value = "endDate")  Date endDate) {
		try
		{
		    List<EntrateUscite> dati = serviceSpese.getSpeseByDate(startDate, endDate);
	        Collections.sort(dati, Comparator.comparing(EntrateUscite::getDescrizione));
	        System.out.println("dati");
	        dati.forEach(System.out::println);
	        return  new ResponseEntity<List<EntrateUscite>>(dati, HttpStatus.OK);
	    }
	    catch (Exception e)
	    {
	    	System.out.println("sono andato in eccezione");
	    	System.out.println(e.getMessage());
	    	ex.setTipo("alert alert-danger");
	    	ex.setMessaggio("tabellaSpese_errore");
	        return new ResponseEntity<MyException>(ex, HttpStatus.BAD_REQUEST);
	    }
	}

}
