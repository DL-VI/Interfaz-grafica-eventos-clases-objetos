package com.banco.principal;

import com.banco.servicios.ServicioBanco;
import com.banco.servicios.ServicioCliente;
import com.banco.servicios.ServicioEmpleado;
import com.banco.servicios.ServicioEmpresa;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      ServicioBanco servBanco = new ServicioBanco();
      ServicioEmpleado servEmpleado = new ServicioEmpleado();
      ServicioCliente servCliente = new ServicioCliente();
      ServicioEmpresa servEmpresa = new ServicioEmpresa();
      int opcion;

      do {
         System.out.println("""
            \n-----MENU PRINCIPAL----
            1) Banco
            2) Empleado
            3) Cliente
            4) Empresa
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del programa");
            case 1 -> servBanco.menuSerivicioBanco();
            case 2 -> servEmpleado.menuServicioEmpleado();
            case 3 -> servCliente.menuSerivicioCliente();
            case 4 -> servEmpresa.menuServicioEmpresa();
            default -> System.out.println("Opcion incorrecta.\n");
         }
      } while (opcion != 0);
   }
}