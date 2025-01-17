package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Detalle_Venta;
import com.example.demo.entity.Venta;

public interface VentaService {

    public abstract List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin);
    
    public abstract Venta guardarVenta(Venta venta);
    
    public abstract List<Venta> ListarVenta();
}
