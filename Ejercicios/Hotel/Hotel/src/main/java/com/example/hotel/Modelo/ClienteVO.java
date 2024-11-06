package com.example.hotel.Modelo;

import java.util.ArrayList;

public class ClienteVO {
    String Dni;
    String Nombre;
    String Apellidos;
    String Direccion;
    String Localidad;
    String Provincia;
    ArrayList<ReservaVO> reservaVOS;

    public ClienteVO(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        Dni = dni;
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Localidad = localidad;
        Provincia = provincia;
    }

    public ClienteVO(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia, ArrayList<ReservaVO> reservaVOS) {
        Dni = dni;
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Localidad = localidad;
        Provincia = provincia;
        this.reservaVOS = reservaVOS;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public ArrayList<ReservaVO> getReservaVOS() {
        return reservaVOS;
    }

    public void setReservaVOS(ArrayList<ReservaVO> reservaVOS) {
        this.reservaVOS = reservaVOS;
    }

    @Override
    public String toString() {
        return "ClienteVO{" +
                "Dni='" + Dni + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Localidad='" + Localidad + '\'' +
                ", Provincia='" + Provincia + '\'' +
                ", reservaVOS=" + reservaVOS +
                '}';
    }
}
