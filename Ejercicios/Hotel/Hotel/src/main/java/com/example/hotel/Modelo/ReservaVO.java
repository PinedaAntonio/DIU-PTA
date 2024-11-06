package com.example.hotel.Modelo;

import java.time.LocalDate;

public class ReservaVO {
    int Id;
    LocalDate Fecha_Llegada;
    LocalDate Fecha_Salida;
    int NHabitaciones;
    String Tipo_Habitacion;
    boolean Fumador;
    String Regimen;
    String Dni_Cliente;

    public ReservaVO(int id, LocalDate fecha_Llegada, LocalDate fecha_Salida, int NHabitaciones, String tipo_Habitacion, boolean fumador, String regimen, String dni_Cliente) {
        Id = id;
        Fecha_Llegada = fecha_Llegada;
        Fecha_Salida = fecha_Salida;
        this.NHabitaciones = NHabitaciones;
        Tipo_Habitacion = tipo_Habitacion;
        Fumador = fumador;
        Regimen = regimen;
        Dni_Cliente = dni_Cliente;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getFecha_Llegada() {
        return Fecha_Llegada;
    }

    public void setFecha_Llegada(LocalDate fecha_Llegada) {
        Fecha_Llegada = fecha_Llegada;
    }

    public LocalDate getFecha_Salida() {
        return Fecha_Salida;
    }

    public void setFecha_Salida(LocalDate fecha_Salida) {
        Fecha_Salida = fecha_Salida;
    }

    public int getNHabitaciones() {
        return NHabitaciones;
    }

    public void setNHabitaciones(int NHabitaciones) {
        this.NHabitaciones = NHabitaciones;
    }

    public String getTipo_Habitacion() {
        return Tipo_Habitacion;
    }

    public void setTipo_Habitacion(String tipo_Habitacion) {
        Tipo_Habitacion = tipo_Habitacion;
    }

    public boolean isFumador() {
        return Fumador;
    }

    public void setFumador(boolean fumador) {
        Fumador = fumador;
    }

    public String getRegimen() {
        return Regimen;
    }

    public void setRegimen(String regimen) {
        Regimen = regimen;
    }

    public String getDni_Cliente() {
        return Dni_Cliente;
    }

    public void setDni_Cliente(String dni_Cliente) {
        Dni_Cliente = dni_Cliente;
    }
}
