package com.banco.modelo.enums;

public enum Puesto {
   Cajero(1200000f),
   Supervisor(3000000f),
   Recepcionista(2100000f);

   private final float sueldo;

   Puesto(float sueldo) {
      this.sueldo = sueldo;
   }

   public float getSueldo() {
      return sueldo;
   }

   public static void listarPuestos() {
      System.out.println("Puestos:");
      int contador = 1;

      for (Puesto valor : Puesto.values()) {
         System.out.println(contador + ") " + valor);
         contador++;
      }
   }
}
