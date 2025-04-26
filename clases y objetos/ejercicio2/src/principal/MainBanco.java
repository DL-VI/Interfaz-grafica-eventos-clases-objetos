package principal;

import servicio.ServicioBanco;
import servicio.ServicioClientes;
import servicio.ServicioEmpleados;

import java.util.Scanner;

public class MainBanco {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      ServicioBanco servBanco = new ServicioBanco();
      ServicioEmpleados servEmpleado = new ServicioEmpleados();
      ServicioClientes servCliente = new ServicioClientes();
      int opcion;

      do {
         System.out.println("""
            \n-----MENU PRINCIPAL----
            1) Banco
            2) Empleado
            3) Cliente
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del programa");
            case 1 -> servBanco.menuSerivicioBanco();
            case 2 -> servEmpleado.menuServicioEmpleado();
            case 3 -> servCliente.menuSerivicioCliente();
            default -> System.out.println("\nOpcion incorrecta.\n");
         }
      } while (opcion != 0);
   }
}
