package com.example.hotel;


import com.example.hotel.Modelo.ReservaVO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Cliente {
    SimpleStringProperty Dni;
    SimpleStringProperty Nombre;
    SimpleStringProperty Apellidos;
    SimpleStringProperty Direccion;
    SimpleStringProperty Localidad;
    SimpleStringProperty Provincia;
    ObjectProperty<Integer> reservas;

    public Cliente(SimpleStringProperty dni, SimpleStringProperty nombre, SimpleStringProperty apellidos, SimpleStringProperty direccion, SimpleStringProperty localidad, SimpleStringProperty provincia, ObjectProperty<Integer> reservas) {
        Dni = dni;
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Localidad = localidad;
        Provincia = provincia;
        this.reservas = reservas;
    }

    public Cliente(SimpleStringProperty dni, SimpleStringProperty nombre, SimpleStringProperty apellidos, SimpleStringProperty direccion, SimpleStringProperty localidad, SimpleStringProperty provincia) {
        Dni = dni;
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Localidad = localidad;
        Provincia = provincia;
    }

    public Cliente(){
        this.Dni = new SimpleStringProperty();
        this.Nombre = new SimpleStringProperty();
        this.Apellidos = new SimpleStringProperty();
        this.Direccion = new SimpleStringProperty();
        this.Localidad = new SimpleStringProperty();
        this.Provincia = new SimpleStringProperty();
    }

    public String getDni() {
        return Dni.get();
    }

    public SimpleStringProperty dniProperty() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni.set(dni);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public String getApellidos() {
        return Apellidos.get();
    }

    public SimpleStringProperty apellidosProperty() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos.set(apellidos);
    }

    public String getDireccion() {
        return Direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion.set(direccion);
    }

    public String getLocalidad() {
        return Localidad.get();
    }

    public SimpleStringProperty localidadProperty() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        this.Localidad.set(localidad);
    }

    public String getProvincia() {
        return Provincia.get();
    }

    public SimpleStringProperty provinciaProperty() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        this.Provincia.set(provincia);
    }

    public Integer getReservas() {
        return reservas.get();
    }

    public ObjectProperty<Integer> reservasProperty() {
        return reservas;
    }

    public void setReservas(Integer reservas) {
        this.reservas.set(reservas);
    }
}
