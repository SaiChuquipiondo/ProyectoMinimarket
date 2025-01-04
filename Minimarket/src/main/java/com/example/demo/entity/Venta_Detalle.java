package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class Venta_Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idVenta_Detalle;
    @Column(name = "cant_Prod")
    private int cantidad_Prod;
    @Column(name = "importe")
    private double importe;

    @Column(name = "estado", length = 1)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    public Venta_Detalle() {
    }

    public Venta_Detalle(int idVenta_Detalle, int cantidad_Prod, double importe, String estado, Producto producto,
            Venta venta) {
        this.idVenta_Detalle = idVenta_Detalle;
        this.cantidad_Prod = cantidad_Prod;
        this.importe = importe;
        this.estado = estado;
        this.producto = producto;
        this.venta = venta;
    }

    public int getIdVenta_Detalle() {
        return idVenta_Detalle;
    }

    public void setIdVenta_Detalle(int idVenta_Detalle) {
        this.idVenta_Detalle = idVenta_Detalle;
    }

    public int getCantidad_Prod() {
        return cantidad_Prod;
    }

    public void setCantidad_Prod(int cantidad_Prod) {
        this.cantidad_Prod = cantidad_Prod;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}
