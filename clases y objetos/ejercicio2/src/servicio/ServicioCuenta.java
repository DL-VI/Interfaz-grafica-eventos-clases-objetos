package servicio;

import modelo.*;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioCuenta {
   private Scanner sc;
   private ClienteBanco clienteTemp;
   private Cuenta cuentaTemp;

   public ServicioCuenta(ClienteBanco cliente) {
      this.sc = new Scanner(System.in);
      this.clienteTemp = cliente;
   }

   public void menuServicioCuenta() {
      int opcion;

      do {
         System.out.println("""
            \n-----MENU CLIENTE----
            1) Informacion del cliente
            2) Registrar cuenta bancaria
            3) Listar cuentas bancarias
            4) Seleccionar cuenta bancaria
            5) Depostiar
            6) Retirar
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del MENU CLIENTE.");
            case 1 -> System.out.println("\n" + clienteTemp.toString());
            case 2 -> clienteTemp.agregarCuenta(registrarCuenta());
            case 3 -> {
               if (!validarListado()) break;
               listarCuentas();
            }
            case 4 -> seleccionarCuenta();
            case 5 -> depositar();
            case 6 -> retirar();
            default -> System.out.println("\nOpcion incorrecta.");
         }
      } while (opcion != 0);
      cuentaTemp = null;
   }

   private void retirar() {
      if (!validarSeleccion()) return;

      System.out.print("\nIngrese el monto del retiro: ");
      double monto = sc.nextDouble();

      cuentaTemp.validarRetiro(monto);
   }

   private void depositar() {
      if (!validarSeleccion()) return;

      System.out.print("\nIngrese el monto del deposito: ");
      double monto = sc.nextDouble();

      cuentaTemp.validarDeposito(monto);
   }

   private boolean validarSeleccion() {
      if (cuentaTemp == null) {
         System.out.println("\nDebes seleccionar una cuenta.");
         return false;
      }
      return true;
   }

   private void seleccionarCuenta() {
      if (!validarListado()) return;

      listarCuentas();
      System.out.print("\nIngrese el numero de cuenta: ");
      long cuenta = sc.nextInt();

      int indice = obtenerIndice(cuenta);

      if (indice == -1) {
         System.out.println("\nEl numero de cuenta no existe.");
         return;
      }

      cuentaTemp = clienteTemp.getCuentas().get(indice);
      System.out.println("\nAcabas de seleccionar la cuenta: " + "\n" + cuentaTemp.toString());
   }

   private int obtenerIndice(long cuenta) {
      return IntStream.range(0, clienteTemp.getCuentas().size())
         .filter(indice -> clienteTemp.getCuentas().get(indice).getNumeroCuenta() == cuenta)
         .findFirst()
         .orElse(-1);
   }

   private void listarCuentas() {
      System.out.println("\nLista de cuentas:");
      System.out.println("{\n");
      clienteTemp.getCuentas().forEach(System.out::println);
      System.out.println("}");
   }

   private boolean validarListado() {
      if (clienteTemp.getCuentas().isEmpty()) {
         System.out.println("\nNo hay cuentas registradas.");
         return false;
      }
      return true;
   }

   private Cuenta registrarCuenta() {
      cuentaTemp = null;
      System.out.println("""
         \nTipos de cuenta:
         1) Ahorro
         2) Inversion
         """);

      System.out.print("Opcion: ");
      int opcion = sc.nextInt();

      return switch (opcion) {
         case 1 -> {
            System.out.println("\nSe agrego una cuenta de ahorro.");
            yield new CuentaAhorro();
         }
         case 2 -> {
            System.out.println("\nSe agrego una cuenta de Inversion.");
            yield new CuentaInversion();
         }
         default -> {
            System.out.println("\nOpcion incorrecta");
            yield registrarCuenta();
         }
      };
   }
}
