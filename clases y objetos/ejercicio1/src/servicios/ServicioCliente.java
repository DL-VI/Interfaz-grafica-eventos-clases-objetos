package servicios;

import modelo.Banco;
import modelo.Cliente;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioCliente {
   private Scanner sc;
   private Cliente clienteTemp;
   private ServicioCuenta servCuenta;

   public ServicioCliente() {
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

   private void seleccionar() {
      if (!listar()) return;

      System.out.println("\nLista de clientes:");
      System.out.println("{\n");
      Banco.getListaClientes().forEach(System.out::println);
      System.out.println("}");

      System.out.print("\nIngrese el idUsuario: ");
      int id = sc.nextInt();

      int indice = obtenerIndice(id);

      if (indice == -1) {
         System.out.println("El id del usuario no existe.");
         return;
      }
      clienteTemp = Banco.getListaClientes().get(indice);
      System.out.println("Acabas de seleccionar al cliente: " + clienteTemp.getNombre());
      servCuenta.menuServicioCuenta(clienteTemp);
   }

   private int obtenerIndice(int id) {
      return IntStream.range(0, Banco.getListaClientes().size())
         .filter(indice -> Banco.getListaClientes().get(indice).getIdUsuario() == id)
         .findFirst()
         .orElse(-1);
   }

   private boolean listar() {
      if (Banco.getListaClientes().isEmpty()) {
         System.out.println("No hay clientes registrados.");
         return false;
      }
      return true;
   }

   private void registrarCliente() {
      String nombre;
      int identificacion;

      System.out.print("\nNombre del cliente: ");
      nombre = sc.nextLine();
      System.out.print("Numero de identididad: ");
      identificacion = sc.nextInt();
      System.out.println("\nCliente registrado {\n" + new Cliente(identificacion, nombre) + "}");
   }
}
