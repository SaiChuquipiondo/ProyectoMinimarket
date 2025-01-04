package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idVenta;
    @Column(name = "precio_total")
    private double precio_Total;
    @Column(name = "fecha_registro")
    private Date fecha_Registro;
    @Column(name = "estado", length = 1)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Venta() {
    }

    public Venta(int idVenta, double precio_Total, Date fecha_Registro, String estado, Persona persona,
            Usuario usuario) {
        this.idVenta = idVenta;
        this.precio_Total = precio_Total;
        this.fecha_Registro = fecha_Registro;
        this.estado = estado;
        this.persona = persona;
        this.usuario = usuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getPrecio_Total() {
        return precio_Total;
    }

    public void setPrecio_Total(double precio_Total) {
        this.precio_Total = precio_Total;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
