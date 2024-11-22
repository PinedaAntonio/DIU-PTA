package com.example.agenda;

import javafx.beans.property.*;

public class Articulo {

    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final IntegerProperty id;

    // Constructor por defecto, inicializa el artículo con valores predeterminados.
    public Articulo() {
        this(null, null, 0.0, 0, 0);
    }

    // Constructor con datos iniciales.
    public Articulo(String nombre, String descripcion, double precio, int stock, int id) {
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.id = new SimpleIntegerProperty(id);
    }

    // Métodos getter y setter para el nombre del artículo.
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    // Métodos getter y setter para la descripción del artículo.
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    // Métodos getter y setter para el precio del artículo.
    public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    // Métodos getter y setter para el stock del artículo.
    public int getStock() {
        return stock.get();
    }

    public void setStock(int cantidad) {
        this.stock.set(cantidad);
    }

    public IntegerProperty cantidadProperty() {
        return stock;
    }

    // Métodos getter y setter para el ID del artículo.
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }
}
