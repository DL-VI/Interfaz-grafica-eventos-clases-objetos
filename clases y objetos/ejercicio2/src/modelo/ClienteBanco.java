package modelo;

import java.util.ArrayList;
import java.util.List;

public class ClienteBanco extends Persona {
   private static long contadorClientes = 0;
   private long idCliente;
   private List<Cuenta> cuentas;

   public ClienteBanco(String cedula, String nombre) {
      super(cedula, nombre);
      contadorClientes++;
      this.idCliente = contadorClientes;
      this.cuentas = new ArrayList<>();
      BancoPrincipal.clientes.add(this);
   }

   public String getNombre() {
      return nombre;
   }

   public long getIdCliente() {
      return idCliente;
   }

   public List<Cuenta> getCuentas() {
      return cuentas;
   }

   public void agregarCuenta(Cuenta cuenta) {
      cuentas.add(cuenta);
   }


   @Override
   public String toString() {
      return """
         Idcliente: %d
         Nombre: %s
         Identificacion: %s
         Cantidad de cuentas: %d
         """.formatted(idCliente, nombre, cedula, cuentas.size());
   }
}
