package com.example.hotel.Modelo;

public class ExcepcionReserva extends RuntimeException {
    private String mensaje;

    public ExcepcionReserva() {
    }

    public ExcepcionReserva(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
