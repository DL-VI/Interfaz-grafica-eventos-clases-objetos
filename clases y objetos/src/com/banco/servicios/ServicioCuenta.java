package com.banco.servicios;

import com.banco.modelo.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ServicioCuenta {
   private Scanner sc;
   private Cliente clienteTemp;
   private CuentaBancaria cuentaTemp;

   public ServicioCuenta() {
      this.sc = new Scanner(System.in);
   }

   public void menuServicioCuenta(Cliente cliente) {
      clienteTemp = cliente;
      int opcion;

      do {
         System.out.println("""
            \n-----MENU CLIENTE----
            1) Informacion del cliente
            2) Registrar cuenta bancaria
            3) Registrar inversion
            4) Listar productos de inversion
            5) Listar cuentas bancarias
            6) Seleccionar cuenta bancaria
            7) Depostiar
            8) Retirar
            0) Salir
            """);

         System.out.print("Opcion: ");
         opcion = sc.nextInt();
         sc.nextLine();

         switch (opcion) {
            case 0 -> System.out.println("Saliendo del MENU CLIENTE.");
            case 1 -> System.out.println("\n" + cliente.toString());
            case 2 -> {
               cliente.agregarCuenta();
               cuentaTemp = null;
            }
            case 3 -> inversion();
            case 4 -> {
               if (clienteTemp.getInversiones().isEmpty()) {
                  System.out.println("No hay productos de inversion registrados.");
                  break;
               }

               System.out.println("\nLista de productos de inversion:");
               System.out.println("{\n");
               clienteTemp.getInversiones().forEach(System.out::println);
               System.out.println("}");
            }
            case 5 -> {
               if (listar()) {
                  System.out.println("\nLista de cuentas:");
                  System.out.println("{\n");
                  clienteTemp.getCuentas().forEach(System.out::println);
                  System.out.println("}");
               }
            }
            case 6 -> seleccionar();
            case 7 -> depositar();
            case 8 -> retirar();
            default -> System.out.println("Opcion incorrecta.");
         }
      } while (opcion != 0);
      cuentaTemp = null;
   }

   private void inversion() {
      if (!validarSeleccion()) return;

      if (!Banco.listarEmpresasInversion()) return;

      System.out.print("\nIngrese el Id producto: ");
      int idProducto = sc.nextInt();

      List<ProductoInversion> listaProductos = Banco.getListaEmpresas().stream()
         .flatMap(empresa -> empresa.getListaProductos().stream())
         .collect(Collectors.toList());

      int isValido = IntStream.range(0, listaProductos.size())
         .filter(indice -> listaProductos.get(indice).getIdProducto() == idProducto)
         .findAny().orElse(-1);

      if (isValido == -1) {
         System.out.println("El id del producto no existe.");
         return;
      }

      ProductoInversion producto = listaProductos.get(isValido);

      System.out.print("Ingrese el monto de inversion: ");
      float monto = sc.nextFloat();

      if (cuentaTemp.getSaldo() == 0 || monto > cuentaTemp.getSaldo())
         System.out.println("Fondo insuficiente para la inversion.");
      else if (monto < producto.getMONTO_MINIMO() || monto > producto.getMONTO_MAXIMO())
         System.out.println("El monto de la inversion debe estar entre los montos del producto de inversion.");
      else {
         clienteTemp.agregarInversion(producto, monto);
         cuentaTemp.setSaldo(cuentaTemp.getSaldo() - monto);
      }
   }

   private void retirar() {
      if (!validarSeleccion()) return;

      System.out.print("\nIngrese el idUsuario: ");
      int idUsuario = sc.nextInt();

      if (clienteTemp.getIdUsuario() != idUsuario) {
         System.out.println("Usuario incorrecto.");
         return;
      }

      System.out.print("Ingrese la identidad del usuario: ");
      int identidad = sc.nextInt();

      if (clienteTemp.getIdentificacion() != identidad) {
         System.out.println("Identidad incorrecta.");
         return;
      }

      System.out.print("Ingrese el monto del retiro: ");
      float monto = sc.nextFloat();
      cuentaTemp.retirar(monto);
   }

   private void depositar() {
      if (!validarSeleccion()) return;

      System.out.print("Ingrese el idUsuario: ");
      int idUsuario = sc.nextInt();

      if (clienteTemp.getIdUsuario() != idUsuario) {
         System.out.println("Usuario incorrecto.");
         return;
      }

      System.out.print("Ingrese el monto del deposito: ");
      float monto = sc.nextFloat();
      cuentaTemp.depositar(monto);
   }

   private boolean validarSeleccion() {
      if (cuentaTemp == null) {
         System.out.println("Debes seleccionar una cuenta bancaria.");
         return false;
      }
      return true;
   }

   private void seleccionar() {
      if (!listar()) return;

      System.out.println("\nLista de cuentas:");
      System.out.println("{\n");
      clienteTemp.getCuentas().forEach(System.out::println);
      System.out.println("}");

      System.out.print("Ingrese el idCuenta: ");
      int id = sc.nextInt();
      int indice = obtenerIndice(id);

      if (indice == -1) {
         System.out.println("El id de la cuenta no existe.");
         return;
      }
      cuentaTemp = clienteTemp.getCuentas().get(indice);
      System.out.println("Acabas de seleccionar a la cuenta: " + cuentaTemp.getIdCuenta());
   }

   private int obtenerIndice(int id) {
      return IntStream.range(0, clienteTemp.getCuentas().size())
         .filter(indice -> clienteTemp.getCuentas().get(indice).getIdCuenta() == id)
         .findFirst()
         .orElse(-1);
   }

   private boolean listar() {
      if (clienteTemp.getCuentas().isEmpty()) {
         System.out.println("El cliente no tiene cuentas registradas.");
         return false;
      }
      return true;
   }

}
