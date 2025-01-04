package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idCategoria;

	@Column(name = "nombre", length = 45)
	private String nombre_Categoria;

	@Column(name = "estado", length = 1)
	private String estado;

	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(int idCategoria, String nombre_Categoria, String estado) {
		super();
		this.idCategoria = idCategoria;
		this.nombre_Categoria = nombre_Categoria;
		this.estado = estado;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre_Categoria() {
		return nombre_Categoria;
	}

	public void setNombre_Categoria(String nombre_Categoria) {
		this.nombre_Categoria = nombre_Categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
