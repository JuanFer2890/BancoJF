package com.bancoJF.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id_Cliente")
	@NotEmpty
	private Long idCliente;
	
	@Column (name="nombre")
	@NotEmpty
	private String nombre;
	
	@Column (name="apellido")
	@NotEmpty
	private String apellido;
	
	@Column (name="numero_Telefonico")
	@NotEmpty
	private String numeroTelefonico;
	
	@Column (name="email")
	@NotEmpty
	private String email;
	
	@Column (name="id_Cuenta")
	private long idCuenta;

	//---------------------GETTERS Y SETTERS-------------------------------
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
