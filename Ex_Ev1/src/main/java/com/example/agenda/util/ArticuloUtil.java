package com.example.agenda.util;

import com.example.agenda.model.ArticuloVO;
import com.example.agenda.Articulo;

import java.util.ArrayList;

public class ArticuloUtil {

    // cambiará una lista de reservasVO a Reservas
    public static ArrayList<Articulo> ToArticulo(ArrayList<ArticuloVO> lista) {
        if (lista == null) {
            return null;
        } else {
            ArrayList<Articulo> personas = new ArrayList<>();
            for (ArticuloVO articuloVO : lista) {
                personas.add(new Articulo(articuloVO.getNombre(), articuloVO.getDescripcion(), articuloVO.getPrecio(), articuloVO.getStock(), articuloVO.getId()));
            }
            return personas;
        }
    }
    // cambiará una Reserva a ReservaVO
    public static ArticuloVO ToArticuloVO(Articulo articulo) {
              ArticuloVO articuloVO= new ArticuloVO(articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getStock());
            return articuloVO;
        }
    }


