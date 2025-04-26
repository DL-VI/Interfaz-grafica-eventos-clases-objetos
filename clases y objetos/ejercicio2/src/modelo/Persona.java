package modelo;

public abstract class Persona {
   protected String cedula, nombre;

   public Persona(String cedula, String nombre) {
      this.cedula = cedula;
      this.nombre = nombre;
   }

}
