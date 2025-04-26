package modelo;

import modelo.enums.Puesto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class EmpleadoBanco extends Persona {
   private static final int MAX_VACACIONES = 20;
   private static long contadorEmpleados = 0;
   private long idEmpleado;
   private Puesto puestoTrabajo;
   private double salario;
   private LocalDate fechaIngreso;

   public EmpleadoBanco(String cedula, String nombre, Puesto puestoTrabajo,String fechaIngreso) {
      super(cedula, nombre);
      contadorEmpleados++;
      this.idEmpleado = contadorEmpleados;
      this.puestoTrabajo = puestoTrabajo;
      this.salario = puestoTrabajo.getSueldo();
      DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      this.fechaIngreso = LocalDate.parse(fechaIngreso, formato);
      BancoPrincipal.empleados.add(this);
   }

   private String calcularVacaciones() {
      int tiempoTrabajando = Period.between(fechaIngreso, LocalDate.now()).getYears();
      int diasVacaciones = 5 + (tiempoTrabajando - 1) * 2;

      return tiempoTrabajando <= 0 ? "No tiene dias de vacaciones." :
         "Tiene " + Math.min(diasVacaciones, MAX_VACACIONES) + " dias de vacaciones.";
   }

   public long getIdEmpleado() {
      return idEmpleado;
   }

   public String getNombre() {
      return nombre;
   }

   @Override
   public String toString() {
      return """ 
         IdEmpleado: %d
         Nombre: %s
         Identificacion: %s
         Puesto: %s
         Salario: %.2f
         Ingreso: %s
         Vacaciones: %s
         """.formatted(idEmpleado, nombre, cedula, puestoTrabajo, salario, fechaIngreso.toString(), calcularVacaciones());
   }
}