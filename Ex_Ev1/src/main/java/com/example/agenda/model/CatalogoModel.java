package com.example.agenda.model;
import com.example.agenda.model.repository.ArticuloRepository;


import com.example.agenda.Articulo;
import com.example.agenda.util.ArticuloUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class CatalogoModel {

    private IntegerProperty totalProductos = new SimpleIntegerProperty(50);


    private ArticuloRepository articuloRepository; //Esta es la dependencia de ConversorModelo con MonedaRepository
    ArrayList<ArticuloVO> articulos = new ArrayList<>();

    public void setArticuloRepository(ArticuloRepository articuloRepository)  throws ExcepcionArticulo {
        this.articuloRepository = articuloRepository;
    }

    public ArrayList<Articulo> setArticulos() throws ExcepcionArticulo {
        articulos= articuloRepository.ObtenerListaArticulos();
        totalProductos.set(totalProductos.get() - articulos.size());
        return ArticuloUtil.ToArticulo(articulos);
    }

    public void editarArticulo(Articulo articulo) throws ExcepcionArticulo {
        articuloRepository.editArticulo(ArticuloUtil.ToArticuloVO(articulo));
    }

    public void añadirArticulo(Articulo articulo) throws ExcepcionArticulo {
        articuloRepository.addArticulo(ArticuloUtil.ToArticuloVO(articulo));
        decrementarTotalProductos();
    }

    public void eliminarArticulo(int codigo) throws ExcepcionArticulo {
        articuloRepository.deleteArticulo(codigo);
        decrementarTotalProductos();
    }

    public int obtenerID () throws ExcepcionArticulo {
        return articuloRepository.lastId();
    }

    public int getTotalProductos() {
        return totalProductos.get();
    }
    public IntegerProperty getTotalProductosProperty() {
        return totalProductos;
    }

    private void decrementarTotalProductos() {
        if (totalProductos.get() > 0) {
            totalProductos.set(totalProductos.get() - 1);
        }
    }

    private void incrementarTotalProductos() {
        totalProductos.set(totalProductos.get() + 1);
    }

}
