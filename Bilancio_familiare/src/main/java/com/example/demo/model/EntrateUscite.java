package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the entrate_uscite database table.
 * 
 */
@Entity
@Table(name="entrate_uscite")
@NamedQuery(name="EntrateUscite.findAll", query="SELECT e FROM EntrateUscite e")
public class EntrateUscite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Column(length=100)
	private String descrizione;

	@Column(nullable=false)
	private double importo;

	//bi-directional many-to-one association to Negozi
	@ManyToOne
	@JoinColumn(name="fk_elenco_negozio")
	private Negozi negozi;

	//bi-directional many-to-one association to Tipoentrate
	@ManyToOne
	@JoinColumn(name="fk_elenco_tipoentrate")
	private Tipoentrate tipoentrate;

	public EntrateUscite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getImporto() {
		return this.importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public Negozi getNegozi() {
		return this.negozi;
	}

	public void setNegozi(Negozi negozi) {
		this.negozi = negozi;
	}

	public Tipoentrate getTipoentrate() {
		return this.tipoentrate;
	}

	public void setTipoentrate(Tipoentrate tipoentrate) {
		this.tipoentrate = tipoentrate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntrateUscite [id=").append(id).append(", data=").append(data).append(", descrizione=")
				.append(descrizione).append(", importo=").append(importo).append(", negozi=").append((negozi!=null)?negozi.getNome():"vuoto")
				.append(", tipoentrate=").append((tipoentrate!=null)?tipoentrate.getDescrizione():"vuoto").append("]");
		return builder.toString();
	}

	
}