package com.planlhas.teste.TestePlanilha.Planilha;

import com.planlhas.teste.TestePlanilha.model.Operacao;
import com.planlhas.teste.TestePlanilha.utils.ConvertDates;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlanilhaDAO {

    public List<Operacao> getOperacaoFromPlanilha(){
        ConvertDates convertDates = new ConvertDates();

        List<Operacao> operacaos = new ArrayList<>();

        String userName = System.getProperty("user.name");
        String path = "C:/Users/"+userName+"/Desktop/teste_planilha.xlsx";

        try{
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()){
                Row row = (Row) rowIterator.next();

                Operacao operacao = new Operacao();

                if(row.getRowNum()==0){
                    continue;
                }

                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()){
                    Cell cell = (Cell) cellIterator.next();

                    switch (cell.getColumnIndex()){
                        case 0:
                            operacao.setNomeMotorista(cell.getStringCellValue());
                            break;
                        case 1:
                            operacao.setCpfMotorista(cell.getStringCellValue());
                            break;
                        case 2:
                            operacao.setEmailMotorista(cell.getStringCellValue());
                            break;
                        case 3:
                            operacao.setTipoVeiculo(cell.getStringCellValue());
                            break;
                        case 4:
                            operacao.setPlaca(cell.getStringCellValue());
                            break;
                        case 5:
                            operacao.setPacotes((int) cell.getNumericCellValue());
                            break;
                        case 6:
                            operacao.setSaida((int) cell.getNumericCellValue());
                            break;
                        case 7:
                            operacao.setBonus((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            operacao.setData(cell.getStringCellValue());
                            break;
                    }
                }

                operacaos.add(operacao);

            }
            fileInputStream.close();
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
            return operacaos;
    }


}
