package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Reportes.ReporteExcel;

import com.example.demo.entity.Venta;

import com.example.demo.service.VentaService;

import jakarta.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Reporte")
public class ReporteController {

    @Autowired
    @Qualifier("ventaService")
    private VentaService ventaService;

    @GetMapping("/Listar")
    public String ListarReporte(Model model) {
        List<Venta> listarVentas = ventaService.ListarVenta();
        model.addAttribute("venta", listarVentas);
        return "Admin/Reportes/ListarReportes";
    }

    @GetMapping("/ventas")
    public ModelAndView ventas() {
        ModelAndView modelAndView = new ModelAndView("Admin/Reportes/formularioReporte");
        return modelAndView;
    }

    @PostMapping("/ventas/excel")
    public void generarReporteExcel(
            HttpServletResponse response,
            @RequestParam(name = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam(name = "fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) throws Exception {

        // Validar que fechaInicio no sea después de fechaFin
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser después de la fecha de fin.");
        }

        response.setContentType("application/vnd.ms-excel");

        // Configurar encabezado para el archivo descargable
        String headerKey = "Content-Disposition";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String headerValue = "attachment; filename=reporte_ventas_"
                + dateFormat.format(fechaInicio) + "_TO_"
                + dateFormat.format(fechaFin) + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // Obtener las ventas en el rango de fechas
        List<Venta> ventas = ventaService.obtenerVentasPorFecha(fechaInicio, fechaFin);

        // Generar el archivo Excel
        ReporteExcel excelGenerator = new ReporteExcel();
        excelGenerator.generarExcel(ventas, response);
    }

}
