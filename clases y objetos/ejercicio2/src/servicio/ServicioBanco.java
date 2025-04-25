package servicio;

import modelo.Banco;
import modelo.BancoPrincipal;

import java.util.Scanner;

public class ServicioBanco {
   private Scanner sc;

   public ServicioBanco() {
      this.sc = new Scanner(System.in);
   }

   public void menuSerivicioBanco() {
      int opcion;

      do {
         System.out.println("""
            \n-----SERVICIOS BANCO----
            1) Informacion
            2) Listar empleados
            3) Listar clientes
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("\nSaliendo del SERVICIOS BANCO.");
            case 1 -> System.out.println("""
               \nId banco: %d
               Banco: %s
               """.formatted(Banco.getID_BANCO(), Banco.getNombre()));
            case 2 -> {
               if (BancoPrincipal.empleados.isEmpty()) {
                  System.out.println("\nNo hay empleados registrados.");
                  break;
               }

               System.out.println("\nLista de empleados:");
               System.out.println("{\n");
               BancoPrincipal.empleados.forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> {
               if (BancoPrincipal.clientes.isEmpty()) {
                  System.out.println("\nNo hay clientes registrados.");
                  break;
               }

               System.out.println("\nLista de clientes:");
               System.out.println("{\n");
               BancoPrincipal.clientes.forEach(System.out::println);
               System.out.println("}");
            }
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }
}
