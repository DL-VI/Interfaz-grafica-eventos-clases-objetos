package modelo;

import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {
   private final double PORCENTAJE_ANUAL = 0.12;
   public static final double PRIMER_DEPOSITO = 1000d;

   public CuentaAhorro() {
      super("Ahorro", 0d, LocalDate.now());
   }

   @Override
   public void validarDeposito(double monto) {
      if (monto < 0)
         System.out.println("\nFallo en el deposito.");
      else if (esPrimerDeposito && monto != PRIMER_DEPOSITO)
         System.out.println("\nEl primer deposito debe ser de $1000");
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
      if (monto <= 0 || monto > saldo)
         System.out.println("\nFallo en el retiro.");
      else if ((saldo - monto) < 500)
         System.out.println("\nDeben haber $500 en la cuenta.");
      else {
         retirar(monto);
         System.out.println("\nRetiro exitoso.");
      }
   }

   private String interesMensual() {
      double profit = (saldo * PORCENTAJE_ANUAL) / 12;
      saldo += profit;

      return """
         \nA fin de mes, se sumaran $%.2f de interes a tu saldo.
         """.formatted(profit);
   }

   @Override
   public String toString() {
      return """
         Tipo cuenta: %s
         Numero cuenta: %s
         Saldo: %.2f
         Porcentaje: %.2f
         Fecha: %s
         Interes mensual: %s
         """.formatted(tipoCuenta, numeroCuenta, saldo, PORCENTAJE_ANUAL, fechaCreada.toString(), interesMensual());
   }
}
