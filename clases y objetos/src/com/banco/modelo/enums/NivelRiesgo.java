package com.banco.modelo.enums;

public enum NivelRiesgo {
   Alto,
   Medio,
   Bajo;

   public static void listarRiesgos() {
      System.out.println("Niveles de riesgo:");
      int contador = 1;

      for (NivelRiesgo valor : NivelRiesgo.values()) {
         System.out.println(contador + ") " + valor);
         contador++;
      }
   }
}
