package com.banco.servicios;

import com.banco.modelo.Banco;
import com.banco.modelo.Empresa;
import com.banco.modelo.ProductoInversion;
import com.banco.modelo.enums.NivelRiesgo;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ServicioEmpresa {
   private Scanner sc;
   private Empresa empresaTemp;

   public ServicioEmpresa() {
      this.sc = new Scanner(System.in);
   }

   public void menuServicioEmpresa() {
      int opcion;

      do {
         System.out.println("""
            \n-----SERVICIOS EMPRESA----
            1) Registrar empresa
            2) Listar empresas
            3) Seleccionar empresa
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del SERVICIOS EMPRESA.");
            case 1 -> registrarEmpresa();
            case 2 -> {
               if (!listar()) break;

               System.out.println("\nLista de empresas:");
               System.out.println("{\n");
               Banco.getListaEmpresas().forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> seleccionar();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void menuEmpresa() {
      int opcion;

      do {
         System.out.println("""
            \n-----MENU EMPRESA----
            1) Informacion de la empresa
            2) Listar productos de inversion
            3) Agregar producto de inversion
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del MENU EMPRESA.");
            case 1 -> System.out.println("\n" + empresaTemp.toString());
            case 2 -> {
               if (empresaTemp.getListaProductos().isEmpty()) {
                  System.out.println("\nNo hay productos de inversion registrados.");
                  break;
               }

               System.out.println("\nLista de productos de inversion:");
               System.out.println("{\n");
               empresaTemp.getListaProductos().forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> agregarInversion();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }

   private void agregarInversion() {
      float minimo, maximo, porcentaje;
      int plazo;
      NivelRiesgo nivel;

      System.out.print("\nIngrese el monto minimo: ");
      minimo = sc.nextFloat();
      System.out.print("Ingrese el monto maximo: ");
      maximo = sc.nextFloat();
      System.out.println("Ingrese el porcentaje: Ejemplo 10");
      porcentaje = sc.nextFloat();
      System.out.print("Ingrese los meses de plazo: ");
      plazo = sc.nextInt();
      nivel = seleccionarRiesgo();

      var producto = new ProductoInversion(minimo, maximo, porcentaje, plazo, nivel, empresaTemp);
      empresaTemp.agregarPI(producto);

      System.out.println("\nProducto de inversion registrado {\n" + producto + "}");
   }

   private NivelRiesgo seleccionarRiesgo() {
      NivelRiesgo.listarRiesgos();

      System.out.print("Ingresa la posicion del nivel de riesgo: ");
      int opcion = sc.nextInt();

      return switch (opcion) {
         case 1 -> NivelRiesgo.Alto;
         case 2 -> NivelRiesgo.Medio;
         case 3 -> NivelRiesgo.Bajo;
         default -> {
            System.out.println("Posicion incorrecta.");
            yield seleccionarRiesgo();
         }
      };
   }

   private void seleccionar() {
      if (!listar()) return;

      System.out.println("\nLista de empresas:");
      System.out.println("{\n");
      Banco.getListaEmpresas().forEach(System.out::println);
      System.out.println("}");

      System.out.print("\nIngrese la clave de la empresa: ");
      int clave = sc.nextInt();

      int indice = obtenerIndice(clave);

      if (indice == -1) {
         System.out.println("La clave de la empresa no existe.");
         return;
      }

      empresaTemp = Banco.getListaEmpresas().get(indice);
      System.out.println("Acabas de seleccionar a la empresa: " + empresaTemp.getNombre());
      menuEmpresa();
   }

   private int obtenerIndice(int id) {
      return IntStream.range(0, Banco.getListaEmpresas().size())
         .filter(indice -> Banco.getListaEmpresas().get(indice).getClaveEmpresa() == id)
         .findFirst()
         .orElse(-1);
   }

   private boolean listar() {
      if (Banco.getListaEmpresas().isEmpty()) {
         System.out.println("No hay empresas registradas.");
         return false;
      }
      return true;
   }

   private void registrarEmpresa() {
      String nombre;
      float historial;

      System.out.print("\nNombre de la empresa: ");
      nombre = sc.nextLine();
      System.out.println("Porcentaje del historial de retorno: Ejemplo 10, 11");
      historial = sc.nextFloat();

      System.out.println("\nEmpresa registrada {\n" + new Empresa(nombre, historial) + "}");
   }
}
