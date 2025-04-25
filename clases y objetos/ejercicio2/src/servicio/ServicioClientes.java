package servicio;

import modelo.Banco;
import modelo.Cliente;
import modelo.ClienteBanco;
import servicios.ServicioCuenta;

import java.util.Scanner;

public class ServicioClientes {
   private Scanner sc;
   private Cliente clienteTemp;
   private ServicioCuenta servCuenta;

   public ServicioClientes() {
      this.sc = new Scanner(System.in);
      servCuenta = new ServicioCuenta();
   }

   public void menuSerivicioCliente() {
      int opcion;

      do {
         System.out.println("""
            \n-----SERVICIOS CLIENTE----
            1) Registrar cliente
            2) Listar clientes
            3) Seleccionar cliente
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del SERVICIOS CLIENTE.");
            case 1 -> registrarCliente();
            case 2 -> {
               if (!listar()) break;

               System.out.println("\nLista de clientes:");
               System.out.println("{\n");
               Banco.getListaClientes().forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> seleccionar();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void registrarCliente() {
      String nombre, identificacion;

      System.out.print("Numero de identididad: ");
      identificacion = sc.nextLine();
      System.out.print("\nNombre del cliente: ");
      nombre = sc.nextLine();
      System.out.println("\nCliente registrado {\n" + new ClienteBanco(identificacion, nombre) + "}");
   }
}
