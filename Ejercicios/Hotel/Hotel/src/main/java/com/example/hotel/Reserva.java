package com.example.hotel;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    SimpleIntegerProperty Id;
    ObjectProperty<LocalDate> Fecha_Llegada;
    ObjectProperty<LocalDate> Fecha_Salida;
    SimpleIntegerProperty NHabitaciones;
    SimpleStringProperty Tipo_Habitacion;
    SimpleBooleanProperty Fumador;
    SimpleStringProperty Regimen;
    SimpleStringProperty Dni_Cliente;

    public Reserva(SimpleIntegerProperty id, ObjectProperty<LocalDate> fecha_Llegada, ObjectProperty<LocalDate> fecha_Salida, SimpleIntegerProperty NHabitaciones, SimpleStringProperty tipo_Habitacion, SimpleBooleanProperty fumador, SimpleStringProperty regimen, SimpleStringProperty dni_Cliente) {
        Id = id;
        Fecha_Llegada = fecha_Llegada;
        Fecha_Salida = fecha_Salida;
        this.NHabitaciones = NHabitaciones;
        Tipo_Habitacion = tipo_Habitacion;
        Fumador = fumador;
        Regimen = regimen;
        Dni_Cliente = dni_Cliente;
    }

    public Reserva() {
        this.Id = new SimpleIntegerProperty();
        this.Fecha_Llegada = new SimpleObjectProperty<>();
        this.Fecha_Salida = new SimpleObjectProperty<>();
        this.NHabitaciones = new SimpleIntegerProperty();
        this.Tipo_Habitacion = new SimpleStringProperty();
        this.Fumador = new SimpleBooleanProperty();
        this.Regimen = new SimpleStringProperty();
        this.Dni_Cliente = new SimpleStringProperty();
    }

    public int getId() {
        return Id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public LocalDate getFecha_Llegada() {
        return Fecha_Llegada.get();
    }

    public ObjectProperty<LocalDate> fecha_LlegadaProperty() {
        return Fecha_Llegada;
    }

    public void setFecha_Llegada(LocalDate fecha_Llegada) {
        this.Fecha_Llegada.set(fecha_Llegada);
    }

    public LocalDate getFecha_Salida() {
        return Fecha_Salida.get();
    }

    public ObjectProperty<LocalDate> fecha_SalidaProperty() {
        return Fecha_Salida;
    }

    public void setFecha_Salida(LocalDate fecha_Salida) {
        this.Fecha_Salida.set(fecha_Salida);
    }

    public int getNHabitaciones() {
        return NHabitaciones.get();
    }

    public SimpleIntegerProperty NHabitacionesProperty() {
        return NHabitaciones;
    }

    public void setNHabitaciones(int NHabitaciones) {
        this.NHabitaciones.set(NHabitaciones);
    }

    public String getTipo_Habitacion() {
        return Tipo_Habitacion.get();
    }

    public SimpleStringProperty tipo_HabitacionProperty() {
        return Tipo_Habitacion;
    }

    public void setTipo_Habitacion(String tipo_Habitacion) {
        this.Tipo_Habitacion.set(tipo_Habitacion);
    }

    public boolean isFumador() {
        return Fumador.get();
    }

    public SimpleBooleanProperty fumadorProperty() {
        return Fumador;
    }

    public void setFumador(boolean fumador) {
        this.Fumador.set(fumador);
    }

    public String getRegimen() {
        return Regimen.get();
    }

    public SimpleStringProperty regimenProperty() {
        return Regimen;
    }

    public void setRegimen(String regimen) {
        this.Regimen.set(regimen);
    }

    public String getDni_Cliente() {
        return Dni_Cliente.get();
    }

    public SimpleStringProperty dni_ClienteProperty() {
        return Dni_Cliente;
    }

    public void setDni_Cliente(String dni_Cliente) {
        this.Dni_Cliente.set(dni_Cliente);
    }
}
