package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_usuario")
public class Tipo_Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idTipo_Usuario;

    @Column(name = "nombre", length = 45)
    private String nombre_Tipo_Usuario;

    @Column(name = "estado", length = 1)
    private String estado;

    public Tipo_Usuario() {
    }

    public Tipo_Usuario(int idTipo_Usuario, String nombre_Tipo_Usuario, String estado) {
        this.idTipo_Usuario = idTipo_Usuario;
        this.nombre_Tipo_Usuario = nombre_Tipo_Usuario;
        this.estado = estado;
    }

    public int getIdTipo_Usuario() {
        return idTipo_Usuario;
    }

    public void setIdTipo_Usuario(int idTipo_Usuario) {
        this.idTipo_Usuario = idTipo_Usuario;
    }

    public String getNombre_Tipo_Usuario() {
        return nombre_Tipo_Usuario;
    }

    public void setNombre_Tipo_Usuario(String nombre_Tipo_Usuario) {
        this.nombre_Tipo_Usuario = nombre_Tipo_Usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
