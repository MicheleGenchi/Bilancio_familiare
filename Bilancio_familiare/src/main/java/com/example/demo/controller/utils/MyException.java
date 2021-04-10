package com.example.demo.controller.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MyException extends Exception {
	private String tipo;
	private String messaggio;
	
	@Autowired
	@Qualifier("messageResourceSB")
	MessageSource messageSource;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException() {
		
	}
	
	public MyException(String tipo, String messaggio) {
		this.tipo = tipo;
		this.messaggio = messaggio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	public void setMessaggio(String messaggio) {
		
		this.messaggio = messageSource.getMessage(messaggio, null, Locale.getDefault());
	}

	public void setMessaggio(String messaggio, String[] args) {
		this.messaggio = messageSource.getMessage(messaggio, args, Locale.getDefault());
	}
}
