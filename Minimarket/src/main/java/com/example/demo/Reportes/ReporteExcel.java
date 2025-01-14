package com.example.demo.Reportes;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entity.Venta;

import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.util.List;

public class ReporteExcel {
    public void generarExcel(List<Venta> ventas, HttpServletResponse response) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte Ventas");

        // Encabezado
        Row headerRow = sheet.createRow(0);
        String[] columnas = { "ID Venta", "Fecha", "Cliente", "Total", "Empleado" };
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        // Datos
        int rowIndex = 1;
        for (Venta venta : ventas) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(venta.getIdVenta());
            row.createCell(1).setCellValue(venta.getFechaRegistro().toString());
            row.createCell(2).setCellValue(venta.getPersona().getNombres() + " " + venta.getPersona().getApellidos());
            row.createCell(3).setCellValue(venta.getPrecioTotal());
            row.createCell(4).setCellValue(venta.getUsuario().getPersona().getNombres() + " "
                    + venta.getUsuario().getPersona().getApellidos());
        }

        // Escribir en la respuesta
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
    }
}
