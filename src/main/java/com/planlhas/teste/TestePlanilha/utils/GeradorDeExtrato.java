package com.planlhas.teste.TestePlanilha.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.planlhas.teste.TestePlanilha.model.Operacao;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class GeradorDeExtrato {

    public Boolean gerarExtrato(List<Operacao> list,String nomeUsuario,String emailUsuario,int quinzena, int mes, int ano){

        //TRATANDO NOME DO USUARIO
        String[] nomeUsuarioArray = nomeUsuario.split(" ");

        nomeUsuario = nomeUsuarioArray[0];

        String userNameSystem = System.getProperty("user.name");
        String dest = "C:\\User\\"+userNameSystem+"\\Desktop\\extratos";

        ConvertDates convertDates = new ConvertDates();

        File diretorio = new File(dest);

        String fileName = "extrato_"+nomeUsuario+"_"+emailUsuario+"_"+quinzena+"Q"+mes+"_"+ano + ".pdf";

        //DIRETORIO DE IDENTIFICACAO DATA

        String destData = "C:\\Users\\"+userNameSystem+"\\Desktop\\extratos\\"+quinzena+"Q"+mes+ano;
        File pastasIdentificada = new File(destData);

        //VERIFICANDO SE O DIRETORIO QUE ARMAZENARA AS PASTAS EXISTE
        if(!diretorio.exists()){
            diretorio.mkdir();
        }

        //VERIFICANDO SE O DIRETORIO QUE ARMAZENARÁ OS EXTRATOS EXISTE
        if(! pastasIdentificada.exists()){
            pastasIdentificada.mkdir();
        }

        File arquivoExistente = new File(diretorio+"\\"+pastasIdentificada+"\\"+fileName);

        //DELETANDO O ARQUIVO CASO ELE JÁ EXISTA
        if(arquivoExistente.exists()){
            arquivoExistente.delete();
        }

        Document extrato = new Document();

        try{
            PdfWriter.getInstance(extrato, new FileOutputStream(pastasIdentificada+"\\"+fileName));
            extrato.open();

            extrato.setPageSize(PageSize.A2.rotate());

            //ADICIONANDO IMAGEM
//            extrato.add(Image.getInstance(String.format("C:\\Users\\"+userNameSystem+"\\Pictures\\logo_redefrete.jpg")));

            //Adicionando espaço
            Paragraph espaco = new Paragraph("\n");

            extrato.add(espaco);

            ////TITULO DO ARQUIVO
            Paragraph tituloExtato  = new Paragraph("Extrato Quinzenal");
            tituloExtato.setAlignment(1);
            Font arial = new Font();
            arial.setFamily("Arial");
            arial.setSize(24);
            tituloExtato.setFont(arial);

            //Adicionando título no extrato
            extrato.add(tituloExtato);

            //Adicionando espaco
            extrato.add(espaco);

            //CRIANDO TABELA
            PdfPTable table = new PdfPTable(9);
            table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);

            table.setWidthPercentage(100);

            //Criando os titulos de cada coluna
            PdfPCell titulo1 = new PdfPCell(new Phrase("NOME"));
            PdfPCell titulo2 = new PdfPCell(new Phrase("CPF/CNPJ"));
            PdfPCell titulo3 = new PdfPCell(new Phrase("EMAIL"));
            PdfPCell titulo4 = new PdfPCell(new Phrase("TIPO VEÍCULO"));
            PdfPCell titulo5 = new PdfPCell(new Phrase("PLACA"));
            PdfPCell titulo6 =  new PdfPCell(new Phrase("PACOTES"));
            PdfPCell titulo7 = new PdfPCell(new Phrase("SAÍDA"));
            PdfPCell titulo8 = new PdfPCell(new Phrase("BONUS"));
            PdfPCell titulo9 = new PdfPCell(new Phrase("DATA"));

            ///ADICIONANDO TITULOS DAS COLUNAS NA TABELA

            table.addCell(titulo1);
            table.addCell(titulo2);
            table.addCell(titulo3);
            table.addCell(titulo4);
            table.addCell(titulo5);
            table.addCell(titulo6);
            table.addCell(titulo7);
            table.addCell(titulo8);
            table.addCell(titulo9);


            ///PERCORRENDO A LISTA DE OPERACOES PARA ADICIONAR OS VALORES NA TABELA
            for(int i=0;i<list.size();i++){
                Operacao operacao = list.get(i);

                if(convertDates.validateDate(operacao.getData(),quinzena,mes,ano)){


                    ///ADICIONANDO OS DADOS NA TABELA
                    table.addCell(new Phrase(operacao.getNomeMotorista()));
                    table.addCell(new Phrase(operacao.getCpfMotorista()));
                    table.addCell(new Phrase(operacao.getEmailMotorista()));
                    table.addCell(new Phrase(operacao.getTipoVeiculo()));
                    table.addCell(new Phrase(operacao.getPlaca()));
                    table.addCell(new Phrase(String.valueOf(operacao.getPacotes())));
                    table.addCell(new Phrase(String.valueOf(operacao.getSaida())));
                    table.addCell(new Phrase(String.valueOf(operacao.getBonus())));
                    table.addCell(new Phrase(String.valueOf(operacao.getData())));

                }
            }

            table.setHorizontalAlignment(1);
            extrato.add(table);
            extrato.close();
            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
