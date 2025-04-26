package modelo;

import java.time.LocalDate;
import java.util.Scanner;

public class CuentaInversion extends Cuenta {
   private Scanner sc;
   public static final double PRIMER_DEPOSITO = 25000d;

   public CuentaInversion() {
      super("Inversion", 0d, LocalDate.now());
      this.sc = new Scanner(System.in);
   }

   @Override
   public void validarDeposito(double monto) {
      if (monto < 0)
         System.out.println("\nFallo en el deposito.");
      else if (esPrimerDeposito && monto != PRIMER_DEPOSITO)
         System.out.println("\nEl primer deposito debe ser de $25000");
      else {
         if (esPrimerDeposito) {
            depositar(monto);
            esPrimerDeposito = false;
            System.out.println("\nPrimer deposito exitoso.");
            return;
         }

         depositar(monto);
         System.out.println("\nDeposito exitoso.");
      }
   }

   @Override
   public void validarRetiro(double monto) {
      String opcion = null;

      if (monto <= 0 || monto > saldo)
         System.out.println("\nFallo en el retiro.");
      else if ((saldo - monto) < 10000) {
         System.out.println("""
            \nDeben haber $10000 en la cuenta.
            Desea retirar todo: [S/N]
            """);
         System.out.print("Ingrese la letra: ");
         opcion = sc.next();

         if (opcion.equalsIgnoreCase("n"))
            System.out.println("\nFallo en el retiro");
         else {
            retirar(saldo);
            System.out.println("\nRetiro completo exitoso.");
         }
      } else {
         retirar(monto);
         System.out.println("\nRetiro exitoso.");
      }
   }

   @Override
   public String toString() {
      return """
         Tipo cuenta: %s
         Numero cuenta: %s
         Saldo: %.2f
         Fecha: %s
         """.formatted(tipoCuenta, numeroCuenta, saldo, fechaCreada.toString());
   }
}
