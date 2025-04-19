package com.banco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Banco {
   private static final int ID_BANCO = 111;
   private static final String NOMBRE = "JP MORGAN";
   private static List<Empleado> listaEmpleados = new ArrayList<>();
   private static List<CuentaBancaria> listaCuentas = new ArrayList<>();
   private static List<Cliente> listaClientes = new ArrayList<>();
   private static List<Empresa> listaEmpresas = new ArrayList<>();

   public static int getID_BANCO(){
      return ID_BANCO;
   }

   public static String getNombre() {
      return NOMBRE;
   }

   public static void agregarEmpleado(Empleado empleado) {
      listaEmpleados.add(empleado);
   }

   public static void agregarCuenta(CuentaBancaria cuenta) {
      listaCuentas.add(cuenta);
   }

   public static void agregarCliente(Cliente cliente) {
      listaClientes.add(cliente);
   }

   public static void agregarEmpresa(Empresa empresa) {
      listaEmpresas.add(empresa);
   }

   public static boolean listarEmpresasInversion() {
      List<List<ProductoInversion>> productosTotales = listaEmpresas.stream()
         .filter(Banco::esConfiable)
         .map(Empresa::getListaProductos)
         .toList();

      if (productosTotales.stream().allMatch(List::isEmpty)) {
         System.out.println("\nNo hay productos de inversión");
         return false;
      } else {
         System.out.println("\nOpciones de inversión:");
         System.out.println("{\n");
         productosTotales.forEach(productos -> productos.forEach(producto -> {
            System.out.println(producto.toString());
         }));
         System.out.println("}");
      }
      return true;
   }

   private static boolean esConfiable(Empresa empresa) {
      return empresa.getHistorialRetorno() >= 0.1f;
   }

   public static List<Empleado> getListaEmpleados() {
      return listaEmpleados;
   }

   public static List<Cliente> getListaClientes() {
      return listaClientes;
   }

   public static List<Empresa> getListaEmpresas() {
      return listaEmpresas;
   }

   public static List<CuentaBancaria> getListaCuentas() {
      return listaCuentas;
   }
}
