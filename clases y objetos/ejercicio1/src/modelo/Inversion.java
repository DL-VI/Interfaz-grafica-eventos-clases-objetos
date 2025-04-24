package modelo;

public class Inversion {
    private static int contadorInversiones = 0;
    private int idInversion;
    private ProductoInversion prdInversion;
    private float monto;

    public Inversion(ProductoInversion prdInversion, float monto) {
        contadorInversiones++;
        this.idInversion = contadorInversiones;
        this.prdInversion = prdInversion;
        this.monto = monto;
    }

    private float calcularRetorno() {
            return monto + (monto * prdInversion.getPORCENTAJE() * prdInversion.getPLAZO_MESES());
    }

   @Override
   public String toString() {
      return """
         Empresa: %s
         Monto: %.2f
         Retorno: %.2f
         %s
         """.formatted(prdInversion.getEmpresaTem().getNombre(), monto, calcularRetorno(), prdInversion.toString());
   }
}