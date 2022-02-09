package com.bancoJF.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id_Tarjeta")
	private Long idTarjeta;
	
	@Column (name="numero_Tarjeta", nullable = false, length=16)
	@NotEmpty
	private String numeroTarjeta;

	//relacionada con Cuenta.java "id_Cuenta"
	@JoinColumn(name = "id_Cuenta", referencedColumnName = "id_Cuenta", nullable = false)
	@NotEmpty
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta idCuenta;
	
	@Column (name="icv", nullable = false, length=3)
	@NotEmpty
	private String icv;
	
	@Column(name="tipo_tarjeta")
	@NotEmpty
	private String tipoTarjeta;

	//--------------------------GETTERS Y SETTERS------------------------------------------
	
	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}
	
	public Cuenta getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Cuenta idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
