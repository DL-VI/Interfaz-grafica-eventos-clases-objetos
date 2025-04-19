package com.banco.modelo;

import com.banco.modelo.enums.NivelRiesgo;

public class ProductoInversion {
    private static int contadorProducos = 0;
    private final float MONTO_MINIMO;
    private final float MONTO_MAXIMO;
    private final float PORCENTAJE;
    private final int PLAZO_MESES;
    private final int idProducto;
    private final NivelRiesgo RIESGO;
    private Empresa empresaTem;

    public ProductoInversion(float minimo, float maximo, float porcentaje, int plazo, NivelRiesgo riesgo, Empresa empresa) {
        contadorProducos++;
        this.MONTO_MINIMO = minimo;
        this.MONTO_MAXIMO = maximo;
        this.PORCENTAJE = porcentaje / 100;
        this.PLAZO_MESES = plazo;
        this.idProducto = contadorProducos;
        this.RIESGO = riesgo;
        this.empresaTem = empresa;
    }

   public int getIdProducto() {
      return idProducto;
   }

   public Empresa getEmpresaTem() {
      return empresaTem;
   }

   public float getMONTO_MINIMO() {
      return MONTO_MINIMO;
   }

   public float getMONTO_MAXIMO() {
      return MONTO_MAXIMO;
   }

   public float getPORCENTAJE() {
      return PORCENTAJE;
   }

   public int getPLAZO_MESES() {
      return PLAZO_MESES;
   }

   @Override
    public String toString() {
        return """
                Id producto: %d
                Monto minimo: $%.2f
                Monto maximo: $%.2f
                Porcentaje: %.2f
                Meses: %d
                Riesgo: %s
                """.formatted(idProducto, MONTO_MINIMO, MONTO_MAXIMO, PORCENTAJE, PLAZO_MESES, RIESGO);
    }
}
