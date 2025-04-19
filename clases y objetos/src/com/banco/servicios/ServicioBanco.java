package com.banco.servicios;

import com.banco.modelo.Banco;

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
            4) Listar empresas
            5) Listar cuentas bancarias
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del SERVICIOS BANCO.");
            case 1 -> System.out.println("""
               \nId banco: %d
               Banco: %s
               """.formatted(Banco.getID_BANCO(), Banco.getNombre()));
            case 2 -> {
               if (Banco.getListaEmpleados().isEmpty()){
                  System.out.println("\nNo hay empleados registrados.");
                  break;
               }

               System.out.println("\nLista de empleados:");
               System.out.println("{\n");
               Banco.getListaEmpleados().forEach(System.out::println);
               System.out.println("}");
            }
            case 3 -> {
               if (Banco.getListaClientes().isEmpty()) {
                  System.out.println("\nNo hay clientes registrados.");
                  break;
               }

               System.out.println("\nLista de clientes:");
               System.out.println("{\n");
               Banco.getListaClientes().forEach(System.out::println);
               System.out.println("}");
            }
            case 4 -> {
               if (Banco.getListaEmpresas().isEmpty()) {
                  System.out.println("\nNo hay empresas registradas.");
                  break;
               }

               System.out.println("\nLista de empresas:");
               System.out.println("{\n");
               Banco.getListaEmpresas().forEach(System.out::println);
               System.out.println("}");
            }
            case 5 -> {
               if (Banco.getListaCuentas().isEmpty()) {
                  System.out.println("\nNo hay cuentas registradas.");
                  break;
               }

               System.out.println("\nLista de cuentas bancarias:");
               System.out.println("{\n");
               Banco.getListaCuentas().forEach(System.out::println);
               System.out.println("}");
            }
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
   }
}
