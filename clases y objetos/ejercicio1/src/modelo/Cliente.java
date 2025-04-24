package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
   private static int contadorClientes = 0;
   private int idUsuario;
   private int identificacion;
   private String nombre;
   private List<CuentaBancaria> cuentas;
   private List<Inversion> inversiones;

   public Cliente(int identidad, String nombre) {
      contadorClientes++;
      this.idUsuario = contadorClientes;
      this.identificacion = identidad;
      this.nombre = nombre;
      this.cuentas = new ArrayList<>();
      this.inversiones = new ArrayList<>();
      Banco.agregarCliente(this);
   }

   public void agregarCuenta() {
      cuentas.add(new CuentaBancaria(this));
      System.out.println("Cuenta agregada.");
   }

   public void agregarInversion(ProductoInversion producto, float monto) {
      inversiones.add(new Inversion(producto, monto));
      System.out.println("Inversion agregada.");
   }

   public int getIdentificacion() {
      return identificacion;
   }

   public int getIdUsuario() {
      return idUsuario;
   }

   public String getNombre() {
      return nombre;
   }

   public List<CuentaBancaria> getCuentas() {
      return cuentas;
   }

   public List<Inversion> getInversiones() {
      return inversiones;
   }

   @Override
   public String toString() {
      return """
         Id usuario: %d
         Nombre: %s
         Identidad: %d
         """.formatted(idUsuario, nombre, identificacion);
   }
}