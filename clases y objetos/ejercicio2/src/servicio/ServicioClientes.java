package servicio;

import modelo.BancoPrincipal;
import modelo.ClienteBanco;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioClientes {
   private Scanner sc;

   public ServicioClientes() {
      this.sc = new Scanner(System.in);
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
               BancoPrincipal.clientes.forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> seleccionar();
            default -> System.out.println("\nOpcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void registrarCliente() {
      String nombre, identificacion;

      System.out.print("\nNumero de identididad: ");
      identificacion = sc.nextLine();
      System.out.print("Nombre del cliente: ");
      nombre = sc.nextLine();
      System.out.println("\nCliente registrado {\n" + new ClienteBanco(identificacion, nombre) + "}");
   }

   private boolean listar() {
      if (BancoPrincipal.clientes.isEmpty()) {
         System.out.println("\nNo hay clientes registrados.");
         return false;
      }
      return true;
   }

   private void seleccionar() {
      if (!listar()) return;

      System.out.println("\nLista de clientes:");
      System.out.println("{\n");
      BancoPrincipal.clientes.forEach(System.out::println);
      System.out.println("}");

      System.out.print("\nIngrese el idcliente: ");
      long id = sc.nextInt();

      int indice = obtenerIndice(id);

      if (indice == -1) {
         System.out.println("\nEl id del cliente no existe.");
         return;
      }
      ClienteBanco clienteTemp = BancoPrincipal.clientes.get(indice);
      System.out.println("\nAcabas de seleccionar al cliente: " + clienteTemp.getNombre());
      new ServicioCuenta(clienteTemp).menuServicioCuenta();
   }

   private int obtenerIndice(long id) {
      return IntStream.range(0, BancoPrincipal.clientes.size())
         .filter(indice -> BancoPrincipal.clientes.get(indice).getIdCliente() == id)
         .findFirst()
         .orElse(-1);
   }
}
