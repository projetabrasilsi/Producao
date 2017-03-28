package br.com.projetabrasil.model.entities;
import java.io.File;
import java.io.IOException;

import br.com.projetabrasil.model.dao.ProfissaoDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Ler_Excel {
	
	public void inserirProfissaoBD(Pessoa responsavel){
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File("C:\\ProjetaBrasil Repositorio\\ESTRUTURA CBO\\Profissoes.xls"));
			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();
			for(int i = 0; i < linhas; i++){
				Cell a1 = sheet.getCell(0, i);								
				String descricao = a1.getContents();
				Profissao prof = new Profissao();
				ProfissaoDAO profDAO = new ProfissaoDAO();
				prof = profDAO.PesquisaProfissaoPorDescricao(descricao);
				if(prof == null){
					
					prof = new Profissao(responsavel,descricao);
					profDAO.merge(prof);
					System.out.println("Inseririu profissão no BD");
				}
				System.out.println("Profissão: "+descricao +" - "+ i+"/"+linhas);
					
				
				
			}
			workbook.close();
			
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
