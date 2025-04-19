package com.banco.modelo;

import com.banco.modelo.enums.Puesto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Empleado {
   private static int contadorEmpleados = 0;
   private static final int MAX_VACACIONES = 20;
   private int idEmpleado;
   private String nombre;
   private int identificacion;
   private LocalDate fechaIngreso;
   private Puesto puesto;
   private String vacaciones;
   private float salario;

   public Empleado(int identificacion, String nombre, String fechaIngreso, Puesto puesto) {
      contadorEmpleados++;
      this.idEmpleado = contadorEmpleados;
      this.identificacion = identificacion;
      this.nombre = nombre;
      DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      this.fechaIngreso = LocalDate.parse(fechaIngreso, formato);
      this.puesto = puesto;
      this.vacaciones = "";
      this.salario = 0f;
      Banco.agregarEmpleado(this);
   }

   public void vacaciones() {
      int tiempoTrabajando = Period.between(fechaIngreso, LocalDate.now()).getYears();
      int diasVacaciones = 5 + (tiempoTrabajando - 1) * 2;

      vacaciones = (tiempoTrabajando <= 0) ? "No tiene dias de vacaciones." :
      "Tiene " + Math.min(diasVacaciones, MAX_VACACIONES) + " dias de vacaciones.";

      System.out.println(vacaciones);
   }

   public void cobrarSueldo(int identidad) {
      salario = puesto.getSueldo();
      System.out.println("$" + salario + " fueron cobrados.");
   }

   public int getIdEmpleado() {
      return idEmpleado;
   }

   public String getNombre() {
      return nombre;
   }

   public int getIdentificacion() {
      return identificacion;
   }

   @Override
   public String toString() {
      return """
         Id Empleado: %d
         Identidad: %d
         Nombre: %s
         Fecha ingreso: %s
         Puesto: %s
         Salario: %.2f
         Vacaciones: %s
         """.formatted(idEmpleado, identificacion, nombre, fechaIngreso.toString(), puesto, salario, vacaciones);
   }
}