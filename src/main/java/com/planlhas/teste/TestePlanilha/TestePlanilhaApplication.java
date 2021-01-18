package com.planlhas.teste.TestePlanilha;

import com.planlhas.teste.TestePlanilha.Planilha.PlanilhaDAO;
import com.planlhas.teste.TestePlanilha.model.Operacao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestePlanilhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestePlanilhaApplication.class, args);
		PlanilhaDAO dao = new PlanilhaDAO();

		List<Operacao> operacaos = dao.getOperacaoFromPlanilha();

		operacaos.forEach(System.out::println);
	}

}
