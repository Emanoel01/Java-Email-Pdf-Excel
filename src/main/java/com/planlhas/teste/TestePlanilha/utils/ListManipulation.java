package com.planlhas.teste.TestePlanilha.utils;

import com.planlhas.teste.TestePlanilha.model.Operacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListManipulation {

    public HashMap<String[],List<Operacao>> groupOperacoesByMotorista(List<Operacao> operacaos){
        String nomeAnterior = "";
        String emailAnterior = "";

        HashMap<String[], List<Operacao>> operacoesAgrupadas = new HashMap<>();

        List<Operacao> operacaoMotorista = new ArrayList<>();


        //PERCORRENDO LISTA COM TODAS AS OPERACOES
        for(int i=0;i<operacaos.size();i++){
            Operacao operacao = operacaos.get(i);

//			System.out.println("OPERACAO = " + operacao + "\n");

            //VERIFICANDO SE É O PRIMEIRO ELEMENTO PARA COLOCAR OS VALORES INICIAIS
            if(i==0){
                operacaoMotorista.add(operacao);
                nomeAnterior = operacao.getNomeMotorista();
                emailAnterior = operacao.getEmailMotorista();
            }else if(i==operacaos.size()-1){
                operacoesAgrupadas.put(new String[]{nomeAnterior,emailAnterior},operacaoMotorista);
            }else{

                //VERIFICANDO SE A OPERACAO CONTEM OS VALORES
                if(!operacao.getEmailMotorista().equals("")){

                    //VERIFICANDO SE A OPERAÇÃO AINDA É DO MESMO MOTORISTA
                    if(operacao.getEmailMotorista().equals(emailAnterior)){
                        operacaoMotorista.add(operacao);
                    }else{

//						System.out.println("CPF ATUAL = " + operacao.getCpfMotorista());
//						System.out.println("CPF ANTERIOR = " + nomeAnterior + "\n");

                        List<Operacao> operacaoObjMotoca = new ArrayList<>();

                        operacaoMotorista.forEach((a)->{
                            operacaoObjMotoca.add(a);
                        });

                        //CASO NAO SEJA, É SETADO CPF COMO CHAVE E A LISTA COM AS OPERACOES DE UM DETERMIANDO MOTORISTA
                        operacoesAgrupadas.put(new String[]{nomeAnterior,emailAnterior},operacaoObjMotoca);

                        //REMOVENDO TODOS AS OPERACOES DO MOTORISTA ANTERIOR
                        operacaoMotorista.clear();

                        //SETANDO NOME DO PROXIMO MOTORISTA PARA VERIFICACAO
                        nomeAnterior = operacao.getNomeMotorista();

                        //SETANDO EMAIL DO PROXIMO MOTORISTA
                        emailAnterior = operacao.getEmailMotorista();

                        //ADICIONANDO OPERACAO DO NOVO MOTORISTA
                        operacaoMotorista.add(operacao);
                    }
                }else{
                    break;
                }

            }
        }

//        operacoesAgrupadas.forEach((a,b)->{
//            System.out.println("CHAVE = " + a + "\n" + "VALOR : " + b + "\n");
//        });

        return operacoesAgrupadas;

    }

}
