package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Detalle_Venta;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;

public interface Detalle_VentaService {
	
    public abstract Detalle_Venta guardarDetalle_Venta(Detalle_Venta detalleventa);
    
    public abstract List<Detalle_Venta> ListarDetalle();
}
