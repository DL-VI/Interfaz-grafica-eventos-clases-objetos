package com.banco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
   private static int contadorEmpresas = 0;
   private int claveEmpresa;
   private String nombre;
   private float historialRetorno;
   private List<ProductoInversion> listaProductos;

   public Empresa(String nombre, float historial) {
      contadorEmpresas++;
      this.claveEmpresa = contadorEmpresas;
      this.nombre = nombre;
      this.historialRetorno = historial / 100;
      this.listaProductos = new ArrayList<>();
      Banco.agregarEmpresa(this);
   }

   public void agregarPI(ProductoInversion producto) {
      listaProductos.add(producto);
   }

   public List<ProductoInversion> getListaProductos() {
      return listaProductos;
   }

   public float getHistorialRetorno() {
      return historialRetorno;
   }

   public String getNombre() {
      return nombre;
   }

   public int getClaveEmpresa() {
      return claveEmpresa;
   }

   @Override
   public String toString() {
      StringBuilder mensaje = new StringBuilder();
      if (listaProductos.isEmpty()) mensaje.append("No tiene productos de inversion.");
      else {
         mensaje.append("\n{");
         for (ProductoInversion producto : listaProductos)
            mensaje.append("\n" + producto.toString());
         mensaje.append("}");
      }

      return """
         Clave empresa: %d
         Nombre: %s
         Historial: %.2f
         Productos de inversion: %s
         """.formatted(claveEmpresa, nombre, historialRetorno, mensaje);
   }
}
