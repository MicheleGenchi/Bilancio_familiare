package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the frinchising database table.
 * 
 */
@Entity
@Table(name="franchising")
@NamedQuery(name="Franchising.findAll", query="SELECT f FROM Franchising f")
public class Franchising implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	@StringField(nomeField="''gruppo''",
			notEmpty = true, 
	messageNotEmpty = "Il campo {nomeField} {campoNonVuoto}", 	
	pattern = PATTERN.SoloLettere,
	messagePattern = "{campoSoloLettere}")
	private String gruppo;

	@Column(nullable=false, length=45)
	@StringField(nomeField="''nome''",
			notEmpty = true, 
	messageNotEmpty = "Il campo {nomeField} {campoNonVuoto}", 	
	pattern = PATTERN.SoloLettere,
	messagePattern = "{campoSoloLettere}")
	private String nome;

	//bi-directional many-to-one association to Negozi
	@OneToMany(mappedBy="franchising")
	private List<Negozi> negozis;

	public Franchising() {
	}

	public Franchising(int id) {
		this.id=id; 
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGruppo() {
		return this.gruppo;
	}

	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
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
		negozi.setFranchising(this);

		return negozi;
	}

	public Negozi removeNegozi(Negozi negozi) {
		getNegozis().remove(negozi);
		negozi.setFranchising(null);
		return negozi;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Frinchising [id=").append(id)
		.append(", nome=").append(nome)
		.append(", gruppo=").append(gruppo)
		.append("]\n");
		return builder.toString();
	}

}