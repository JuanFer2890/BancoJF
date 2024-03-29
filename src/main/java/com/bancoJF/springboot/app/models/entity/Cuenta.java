package com.bancoJF.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//estos se llaman atributos
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id_Cuenta")
	private Long idCuenta;
	
	//Relacionada con Cliente.java "id_Cliente"
	@JoinColumn(name = "id_Cliente", referencedColumnName = "id_Cliente", nullable = false)
	@NotEmpty
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private Cliente idCliente;
	
	@Column(name="saldo_Actual")
	@NotEmpty
	private double saldoActual;
	
	@Column(name="creacion")
	@NotEmpty
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creacion;

	//-------------------GETTERS Y SETTERS---------------------------
	
	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
