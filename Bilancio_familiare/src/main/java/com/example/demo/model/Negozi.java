package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the negozi database table.
 * 
 */
@Entity
@Table(name="negozi")
@NamedQuery(name="Negozi.findAll", query="SELECT n FROM Negozi n")
public class Negozi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45, unique=true, nullable=false)
	@StringField(nomeField="''nome''", notEmpty = true, 
	messageNotEmpty = "IL campo {nomeField} {campoNonVuoto}", 	
	pattern = PATTERN.SoloLettere,
	messagePattern = "{campoSoloLettere}")
	private String nome;
	
	@Column(length=100, unique=true, nullable=false)
	@StringField(nomeField="''indirizzo''", notEmpty = true, 
	messageNotEmpty = "IL campo {nomeField} {campoNonVuoto}")
	private String indirizzo;

	//bi-directional many-to-one association to EntrateUscite
	@OneToMany(mappedBy="negozi")
	@JsonIgnore 
	private List<EntrateUscite> entrateUscites;

	//bi-directional many-to-one association to Frinchising
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="fk_franchising", insertable=true, updatable=true)
	@JsonIgnore
	private Franchising franchising;

	//bi-directional many-to-one association to Tipologie
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="fk_negozio_tipologia",  insertable=true, updatable=true)
	@JsonIgnore
	private Tipologie tipologie;

	public Negozi() {
	}

	public Negozi(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<EntrateUscite> getEntrateUscites() {
		return this.entrateUscites;
	}

	public void setEntrateUscites(List<EntrateUscite> entrateUscites) {
		this.entrateUscites = entrateUscites;
	}

	public EntrateUscite addEntrateUscite(EntrateUscite entrateUscite) {
		getEntrateUscites().add(entrateUscite);
		entrateUscite.setNegozi(this);

		return entrateUscite;
	}

	public EntrateUscite removeEntrateUscite(EntrateUscite entrateUscite) {
		getEntrateUscites().remove(entrateUscite);
		entrateUscite.setNegozi(null);

		return entrateUscite;
	}

	public Franchising getFranchising() {
		return this.franchising;
	}

	public void setFranchising(Franchising franchising) {
		this.franchising = franchising;
	}

	public Tipologie getTipologie() {
		return this.tipologie;
	}

	public void setTipologie(Tipologie tipologie) {
		this.tipologie = tipologie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Negozi [id=").append(id).append(", nome=").append(nome).append(", indirizzo=").append(indirizzo)
				.append("]");
		return builder.toString();
	}

}