package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Detalle_Venta;

public interface Detalle_VentaService {

    public abstract Detalle_Venta guardarDetalle_Venta(Detalle_Venta detalleventa);

    public abstract List<Detalle_Venta> ListarDetalle();
}
