package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idPersona;

	@Column(name = "nombres", length = 45)
	private String nombre_Persona;

	@Column(name = "apellidos", length = 100)
	private String apellidos;

	@Column(name = "dni", length = 8)
	private String dni;

	@Column(name = "direccion", length = 100)
	private String direccion;

	@Column(name = "telefono", length = 9)
	private String telefono;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "estado", length = 1)
	private String estado;

	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String nombre_Persona, String apellidos, String dni, String direccion,
			String telefono, String email, String estado) {
		this.idPersona = idPersona;
		this.nombre_Persona = nombre_Persona;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.estado = estado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre_Persona() {
		return nombre_Persona;
	}

	public void setNombre_Persona(String nombre_Persona) {
		this.nombre_Persona = nombre_Persona;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
