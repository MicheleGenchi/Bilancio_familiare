package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the settori database table.
 * 
 */
@Entity
@Table(name="settori")
@NamedQuery(name="Settori.findAll", query="SELECT s FROM Settori s")
public class Settori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45, nullable=false, insertable=true, updatable = false)
	@StringField(nomeField="''nome''", notEmpty = true, 
			messageNotEmpty = "IL campo {nomeField} {campoNonVuoto}", 	
			pattern = PATTERN.SoloLettere,
			messagePattern = "{campoSoloLettere}")
	private String nome;

	//bi-directional many-to-many association to Tipologie
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
		name="settori_tipologie"
		, joinColumns={
			@JoinColumn(name="fk_settore", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_tipologia", nullable=false)
			}
		)
	@JsonIgnore
	private List<Tipologie> tipologies;

	public Settori() {
	}
	
	public Settori(int id) {
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

	public List<Tipologie> getTipologies() {
		return this.tipologies;
	}

	public void setTipologies(List<Tipologie> tipologies) {
		this.tipologies = tipologies;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Settore [id=").append(id)
		.append(", nome=").append(nome).append("]\n");
		return builder.toString();
	}

}