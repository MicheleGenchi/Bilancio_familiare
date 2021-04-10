package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tipologie database table.
 * 
 */
@Entity
@Table(name="tipologie")
@NamedQuery(name="Tipologie.findAll", query="SELECT t FROM Tipologie t")
public class Tipologie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45, unique=true, nullable=false, insertable=true, updatable = false)
	@StringField(nomeField="''nome''",
			notEmpty = true, 
	messageNotEmpty = "Il campo {nomeField} {campoNonVuoto}", 	
	pattern = PATTERN.SoloLettere,
	messagePattern = "{campoSoloLettere}")
	private String nome;

	//bi-directional many-to-one association to Negozi
	@OneToMany(mappedBy="tipologie")
	private List<Negozi> negozis;

	//bi-directional many-to-many association to Settori
	@ManyToMany(mappedBy="tipologies")
	private List<Settori> settoris;

	public Tipologie() {
	}
	
	public Tipologie(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Negozi> getNegozis() {
		return this.negozis;
	}

	public void setNegozis(List<Negozi> negozis) {
		this.negozis = negozis;
	}

	public Negozi addNegozi(Negozi negozi) {
		getNegozis().add(negozi);
		negozi.setTipologie(this);

		return negozi;
	}

	public Negozi removeNegozi(Negozi negozi) {
		getNegozis().remove(negozi);
		negozi.setTipologie(null);

		return negozi;
	}

	public List<Settori> getSettoris() {
		return this.settoris;
	}

	public void setSettoris(List<Settori> settoris) {
		this.settoris = settoris;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tipologie [id=").append(id)
		.append(", nome=").append(nome).append("]\n");
		return builder.toString();
	}

}