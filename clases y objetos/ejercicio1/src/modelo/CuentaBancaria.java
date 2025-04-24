package modelo;

public class CuentaBancaria {
   private static int contadorCuentas = 0;
   private int idCuenta;
   private Cliente titular;
   private float saldo;

   public CuentaBancaria(Cliente titular) {
      contadorCuentas++;
      this.idCuenta = contadorCuentas;
      this.titular = titular;
      this.saldo = 0.0f;
      Banco.agregarCuenta(this);
   }

   public void depositar(float monto) {
      if (monto > 0) {
         saldo += monto;
         System.out.println("Deposito exitoso.");
      } else System.out.println("Monto invalido");
   }

   public void retirar(float monto) {
      if (monto <= saldo && monto > 0) {
         saldo -= monto;
         System.out.println("Retiro exitoso.");
      } else System.out.println("Error, saldo insuficiente o monto invalido.");
   }

   public int getIdCuenta() {
      return idCuenta;
   }

   public float getSaldo() {
      return saldo;
   }

   public void setSaldo(float saldo) {
      this.saldo = saldo;
   }

   @Override
   public String toString() {
      return """
         idCuenta: %d
         Titular: %s
         Saldo: $%.2f
         """.formatted(idCuenta, titular.getNombre(), saldo);
   }
}
