package com.example.examen_ev1_pta.util;

import Modelo.MonedaVO;
import com.example.examen_ev1_pta.Modelo.Moneda;

import java.util.ArrayList;

public class MonedaUtil {

    // cambiará una lista de reservasVO a Reservas
    public static ArrayList<Moneda> ToMoneda(ArrayList<MonedaVO> lista) {
        if (lista == null) {
            return null;
        } else {
            ArrayList<Moneda> monedas = new ArrayList<>();
            for (MonedaVO monedaVO : lista) {
                monedas.add(new Moneda(monedaVO.getNombre(), monedaVO.getMultiplicador(), monedaVO.getCodigo()));
            }
            return monedas;
        }
    }
    // cambiará una Reserva a ReservaVO
    public static MonedaVO ToMonedaVO(Moneda moneda) {
        MonedaVO monedaVO= new MonedaVO(moneda.getNombre(), (float) moneda.getMultiplicador(), moneda.getCodigo());
        return monedaVO;
    }
}
