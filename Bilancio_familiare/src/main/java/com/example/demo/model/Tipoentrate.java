package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tipoentrate database table.
 * 
 */
@Entity
@Table(name="tipoentrate")
@NamedQuery(name="Tipoentrate.findAll", query="SELECT t FROM Tipoentrate t")
public class Tipoentrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, unique=true, length=45)
	@StringField(nomeField="''descrizione''", notEmpty = true, 
	messageNotEmpty = "IL campo {nomeField} {campoNonVuoto}", 	
	pattern = PATTERN.LettereConSpazio,
	messagePattern = "{campoLettereeSpazio}")
	private String descrizione;

	//bi-directional many-to-one association to EntrateUscite
	@OneToMany(mappedBy="tipoentrate")
	@JsonIgnore
	private List<EntrateUscite> entrateUscites;

	public Tipoentrate() {
	}

	public Tipoentrate(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EntrateUscite> getEntrateUscites() {
		return this.entrateUscites;
	}

	public void setEntrateUscites(List<EntrateUscite> entrateUscites) {
		this.entrateUscites = entrateUscites;
	}

	public EntrateUscite addEntrateUscite(EntrateUscite entrateUscite) {
		getEntrateUscites().add(entrateUscite);
		entrateUscite.setTipoentrate(this);

		return entrateUscite;
	}

	public EntrateUscite removeEntrateUscite(EntrateUscite entrateUscite) {
		getEntrateUscites().remove(entrateUscite);
		entrateUscite.setTipoentrate(null);

		return entrateUscite;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tipoentrate [id=").append(id).append(", descrizione=").append(descrizione).append("]");
		return builder.toString();
	}
}