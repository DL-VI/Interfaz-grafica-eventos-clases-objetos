package servicio;

import modelo.BancoPrincipal;
import modelo.EmpleadoBanco;
import modelo.enums.Puesto;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioEmpleados {
   private Scanner sc;
   private EmpleadoBanco empleadoTemp;

   public ServicioEmpleados() {
      this.sc = new Scanner(System.in);
   }

   public void menuServicioEmpleado() {
      int opcion;

      do {
         System.out.println("""
            \n-----SERVICIOS EMPLEADO----
            1) Registrar empleado
            2) Listar empleados
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("\nSaliendo del SERVICIOS EMPLEADO.");
            case 1 -> registrarEmpleado();
            case 2 -> {
               if (!listar()) break;

               System.out.println("\nLista de empleados:");
               System.out.println("{\n");
               BancoPrincipal.empleados.forEach(System.out::println);
               System.out.println("}");
            }
            default -> System.out.println("\nOpcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void registrarEmpleado() {
      String nombre, ingreso, cedula;
      Puesto puesto;

      System.out.print("\nIdentidad del empleado: ");
      cedula = sc.nextLine();
      System.out.print("Nombre del empleado: ");
      nombre = sc.nextLine();
      System.out.println("Fecha de ingreso: Ejemplo 21/12/2000");
      ingreso = sc.nextLine();

      puesto = seleccionarPuesto();

      System.out.println("\nEmpleado registrado {\n" + new EmpleadoBanco(cedula, nombre, puesto, ingreso) + "}");
   }

   private Puesto seleccionarPuesto() {
      Puesto.listarPuestos();

      System.out.print("Ingresa la posicion del puesto: ");
      int opcion = sc.nextInt();

      return switch (opcion) {
         case 1 -> Puesto.Cajero;
         case 2 -> Puesto.Supervisor;
         case 3 -> Puesto.Recepcionista;
         default -> {
            System.out.println("\nPosicion incorrecta.\n");
            yield seleccionarPuesto();
         }
      };
   }

   private boolean listar() {
      if (BancoPrincipal.empleados.isEmpty()) {
         System.out.println("\nNo hay empleados registrados.");
         return false;
      }
      return true;
   }

   private int obtenerIndice(long id) {
      return IntStream.range(0, BancoPrincipal.empleados.size())
         .filter(indice -> BancoPrincipal.empleados.get(indice).getIdEmpleado() == id)
         .findFirst()
         .orElse(-1);
   }


}
