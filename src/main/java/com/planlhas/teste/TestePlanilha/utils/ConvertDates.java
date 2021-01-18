package com.planlhas.teste.TestePlanilha.utils;

public class ConvertDates {

    public Boolean validateQuinzena(int dia, int quizena){
        if(quizena==1){
            if(dia>=1 && dia<=15){
                return true;
            }else{
                return false;
            }
        }else{
            if(dia>=16 && dia<=31){
                return true;
            }else{
                return false;
            }
        }
    }

    public Boolean validateDate(String data, int quinzena, int mes, int ano){
        Boolean bool = true;
        String[] dataArray = data.split("/");

        int diaArray  = Integer.parseInt(dataArray[0]);
        int mesArray = Integer.parseInt(dataArray[1]);
        int anoArray = Integer.parseInt(dataArray[2]);

        if(validateQuinzena(diaArray,quinzena) && mes==mesArray && ano==anoArray){
            bool =  true;
        }else{
            bool =  false;
        }

        return bool;
    }

}
