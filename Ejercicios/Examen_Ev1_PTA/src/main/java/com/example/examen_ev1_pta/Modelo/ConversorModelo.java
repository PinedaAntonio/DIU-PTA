package com.example.examen_ev1_pta.Modelo;


import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import com.example.examen_ev1_pta.util.MonedaUtil;

import java.util.ArrayList;


public class ConversorModelo {
    ArrayList<MonedaVO> monedas = new ArrayList<>();
    private MonedaRepository monedaRepository;
    MonedaVO dolar;


    public void setMonedaRepository(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public float getMultiplicador(){
        float dolarMultiplicador;

        try {
            monedas =  monedaRepository.ObtenerListaMonedas();
            dolar = monedas.get(0);
            dolarMultiplicador =  dolar.getMultiplicador();
        } catch (ExcepcionMoneda e) {
            throw new RuntimeException(e);
        }

        return dolarMultiplicador;

    }

    public ArrayList<Moneda> setMonedas() throws ExcepcionMoneda {
        monedas= monedaRepository.ObtenerListaMonedas();
        return MonedaUtil.ToMoneda(monedas);
    }

    public double getConversion(float euros, double multiplicador){
        double dolares;
        dolares = euros * multiplicador;

        return dolares;
    }

    public double getConversionInversa(float euros, double multiplicador){
        double dolares;
        dolares = euros * (2-multiplicador);

        return dolares;
    }
}
