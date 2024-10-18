package com.example.conversor.Modelo;


import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

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

    public float getDolar(float euros){
        float dolares;
        dolares = euros * getMultiplicador();

        return dolares;
    }
}
