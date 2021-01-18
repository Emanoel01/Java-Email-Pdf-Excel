package com.planlhas.teste.TestePlanilha;

import com.planlhas.teste.TestePlanilha.Planilha.PlanilhaDAO;
import com.planlhas.teste.TestePlanilha.email.EnvioEmail;
import com.planlhas.teste.TestePlanilha.model.Operacao;
import com.planlhas.teste.TestePlanilha.utils.GeradorDeExtrato;
import com.planlhas.teste.TestePlanilha.utils.ListManipulation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class TestePlanilhaApplicationTests {

	@Test
	void contextLoads() {

		int quinzena = 1;
		int mes = 1;
		int ano = 2020;

		PlanilhaDAO dao = new PlanilhaDAO();

		EnvioEmail envioEmail = new EnvioEmail();

		List<Operacao> operacaos = dao.getOperacaoFromPlanilha();

		GeradorDeExtrato geradorDeExtrato = new GeradorDeExtrato();

		ListManipulation listManipulation = new ListManipulation();

		HashMap<String[],List<Operacao>> hashMap = listManipulation.groupOperacoesByMotorista(operacaos);

		hashMap.forEach((a,b)->{
			Boolean status = geradorDeExtrato.gerarExtrato(b,a[0],a[1],quinzena,mes,ano);
		});

		String userNAME = System.getProperty("user.name");

		File file =  new File("C:\\Users\\"+userNAME+"\\Desktop\\extratos\\"+quinzena+"Q"+mes+ano);

		File[] files = file.listFiles();

		for(int i=0;i<files.length;i++){
			File extrato = files[i];

			String[] pathArray = extrato.getPath().split("_");
			String driverEmail = pathArray[2];

			envioEmail.sendExtatoByEmail(driverEmail,extrato);

		}



	}

}
