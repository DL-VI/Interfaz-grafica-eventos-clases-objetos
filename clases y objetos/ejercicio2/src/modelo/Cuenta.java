package modelo;

import java.time.LocalDate;

public abstract class Cuenta {
   private static long contadorCuentas = 1;
   protected String tipoCuenta;
   protected long numeroCuenta;
   protected double saldo;
   protected LocalDate fechaCreada;
   protected boolean esPrimerDeposito;

   public Cuenta(String tipoCuenta, double saldo, LocalDate fechaCreada) {
      this.tipoCuenta = tipoCuenta;
      this.numeroCuenta = contadorCuentas++;
      this.saldo = saldo;
      this.fechaCreada = fechaCreada;
      this.esPrimerDeposito = true;
   }

   public long getNumeroCuenta() {
      return numeroCuenta;
   }

   public void depositar(double monto) {
      saldo += monto;
   }

   public void retirar(double monto) {
      saldo -= monto;
   }

   public abstract void validarDeposito(double monto);

   public abstract void validarRetiro(double monto);
}