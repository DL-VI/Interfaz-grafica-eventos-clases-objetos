package servicios;

import modelo.Banco;
import modelo.Empleado;
import modelo.enums.Puesto;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioEmpleado {
   private Scanner sc;
   private Empleado empleadoTemp;

   public ServicioEmpleado() {
      this.sc = new Scanner(System.in);
   }

   public void menuServicioEmpleado() {
      int opcion;

      do {
         System.out.println("""
            \n-----SERVICIOS EMPLEADO----
            1) Registrar empleado
            2) Listar empleados
            3) Seleccionar empleado
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del SERVICIOS EMPLEADO.");
            case 1 -> registrarEmpleado();
            case 2 -> {
               if (!listar()) break;

               System.out.println("\nLista de empleados:");
               System.out.println("{\n");
               Banco.getListaEmpleados().forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> seleccionar();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void menuEmpleado() {
      int opcion;

      do {
         System.out.println("""
            \n-----MENU EMPLEADO----
            1) Informacion del empleado
            2) Cobrar sueldo
            3) Validar vacaciones
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del MENU EMPLEADO.");
            case 1 -> System.out.println("\n" + empleadoTemp.toString());
            case 2 -> cobrar();
            case 3 -> empleadoTemp.vacaciones();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void cobrar() {
      System.out.print("\nIdentidad del empleado: ");
      int identidad = sc.nextInt();

      if (empleadoTemp.getIdentificacion() != identidad) {
         System.out.println("Error de identidad.");
         return;
      }
      empleadoTemp.cobrarSueldo(identidad);
   }

   private boolean listar() {
      if (Banco.getListaEmpleados().isEmpty()) {
         System.out.println("No hay empleados registrados.");
         return false;
      }
      return true;
   }

   private void seleccionar() {
      if (!listar()) return;

      System.out.println("\nLista de empleados:");
      System.out.println("{\n");
      Banco.getListaEmpleados().forEach(System.out::println);
      System.out.println("}");

      System.out.print("\nIngrese el idEmpleado: ");
      int id = sc.nextInt();

      int indice = obtenerIndice(id);

      if (indice == -1) {
         System.out.println("El id del empleado no existe.");
         return;
      }
      empleadoTemp = Banco.getListaEmpleados().get(indice);
      System.out.println("Acabas de seleccionar al empleado: " + empleadoTemp.getNombre());
      menuEmpleado();
   }

   private int obtenerIndice(int id) {
      return IntStream.range(0, Banco.getListaEmpleados().size())
         .filter(indice -> Banco.getListaEmpleados().get(indice).getIdEmpleado() == id)
         .findFirst()
         .orElse(-1);
   }

   private void registrarEmpleado() {
      String nombre, ingreso;
      int identidad;
      Puesto puesto;

      System.out.print("\nIdentidad del empleado: ");
      identidad = sc.nextInt();
      sc.nextLine();
      System.out.print("Nombre del empleado: ");
      nombre = sc.nextLine();
      System.out.println("Fecha de ingreso: Ejemplo 21/12/2000");
      ingreso = sc.nextLine();

      puesto = seleccionarPuesto();

      System.out.println("\nEmpleado registrado {\n" + new Empleado(identidad, nombre, ingreso, puesto) + "}");
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
            System.out.println("Posicion incorrecta.");
            yield seleccionarPuesto();
         }
      };
   }
}
