package com.banco.principal;

import com.banco.servicios.ServicioCliente;
import com.banco.servicios.ServicioEmpleado;
import com.banco.servicios.ServicioEmpresa;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      ServicioEmpleado servEmpleado = new ServicioEmpleado();
      ServicioCliente servCliente = new ServicioCliente();
      ServicioEmpresa servEmpresa = new ServicioEmpresa();
      int opcion;

      do {
         System.out.println("""
            \n-----MENU PRINCIPAL----
            1) Empleado
            2) Cliente
            3) Empresa
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del programa");
            case 1 -> servEmpleado.menuServicioEmpleado();
            case 2 -> servCliente.menuSerivicioCliente();
            case 3 -> servEmpresa.menuServicioEmpresa();
            default -> System.out.println("Opcion incorrecta.\n");
         }
      } while (opcion != 0);
   }
}
