package com.planlhas.teste.TestePlanilha.model;

import lombok.Data;

@Data
public class Operacao {

    private String nomeMotorista;
    private String cpfMotorista;
    private String emailMotorista;
    private String tipoVeiculo;
    private String placa;
    private int pacotes;
    private int saida;
    private int bonus;
    private String data;

}
