package com.example.examen_ev1_pta.Modelo;

import javafx.beans.property.*;

public class Moneda {

    private final StringProperty nombre;
    private final DoubleProperty multiplicador;
    private final IntegerProperty codigo;

    public Moneda(){
        nombre = new SimpleStringProperty("");
        multiplicador = new SimpleDoubleProperty(0);
        codigo = new SimpleIntegerProperty(0);
    }

    public Moneda(String nombre, double multiplicador, int codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleDoubleProperty(multiplicador);
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public double getMultiplicador() {
        return multiplicador.get();
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    public DoubleProperty multiplicadorProperty() {
        return multiplicador;
    }

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }
}
