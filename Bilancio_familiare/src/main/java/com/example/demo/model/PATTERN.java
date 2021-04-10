package com.example.demo.model;

public enum PATTERN {
	LettereConSpazio("(\\b[a-zA-Z]{2,}\\b|\\s){1,}"), SoloLettere("^\\b[a-zA-Z]{2,}\\b"), no_pattern(".*");
	
	private String get=null;
	
	private PATTERN(String string) {
		this.get=string;
	}
	
	public String get() {
		return get;
	}
	
}
