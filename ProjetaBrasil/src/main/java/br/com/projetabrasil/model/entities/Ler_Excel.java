package br.com.projetabrasil.model.entities;

import java.io.File;
import java.io.IOException;

import br.com.projetabrasil.model.dao.Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.Modelo_de_Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.ProfissaoDAO;
import br.com.projetabrasil.util.Utilidades;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Ler_Excel {

	public void inserirProfissaoBD(Pessoa responsavel) {
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File("C:\\ProjetaBrasil Repositorio\\ESTRUTURA CBO\\Profissoes.xls"));
			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();
			for (int i = 0; i < linhas; i++) {
				Cell a1 = sheet.getCell(0, i);
				String descricao = Utilidades.removerAcentos(a1.getContents());
				Profissao prof = new Profissao();
				ProfissaoDAO profDAO = new ProfissaoDAO();
				prof = profDAO.PesquisaProfissaoPorDescricao(descricao);
				if (prof == null) {

					prof = new Profissao(responsavel, descricao);
					profDAO.merge(prof);
					
				}
				

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

	public void inserirMarca_e_RacaBD(Pessoa responsavel, String caminho,
			Enum_Aux_Classificacao_Objetos classificacao) {
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(caminho));
			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();
			for (int i = 0; i < linhas; i++) {
				Cell a1 = sheet.getCell(0, i);
				Cell a2 = sheet.getCell(1, i);

				String codigo = a1.getContents();
				String descricao = Utilidades.removerAcentos(a2.getContents());
				Marca_e_Raca mR = new Marca_e_Raca();
				Marca_e_RacaDAO mRDAO = new Marca_e_RacaDAO();
				mR.setCodigo(codigo);
				mR.setDescricao(descricao);
				mR.setEnum_Aux_Classificacao_Objetos(classificacao);

				mR = mRDAO.verifica_Marca_e_Raca(mR);

				if (mR == null) {
					mR = new Marca_e_Raca();
					mR.setId_Empresa(1);
					mR.setCodigo(codigo);
					mR.setDescricao(descricao);
					mR.setEnum_Aux_Classificacao_Objetos(classificacao);
					mR.setId_Empresa(1);
					mR.setId_Pessoa_Registro(responsavel);
					mR.setUltimaAtualizacao(Utilidades.retornaCalendario());
					mR = mRDAO.merge(mR);
					
				}
				

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

	public void inserirModelo_de_Marca_e_RacaBD(Pessoa responsavel, String caminho,
			Enum_Aux_Classificacao_Objetos classificacao) {
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(caminho));
			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();
			for (int i = 0; i < linhas; i++) {
				Cell a2;
				Cell a3;
				if (classificacao.getEnum_Aux_Tipos_Objetos().equals(Enum_Aux_Tipos_Objetos.PETS)) {
					a2 = sheet.getCell(0, i);
					a3 = sheet.getCell(1, i);
				} else {
					a2 = sheet.getCell(1, i);
					a3 = sheet.getCell(2, i);
				}
				String codigo = a2.getContents();
				String descricao = Utilidades.removerAcentos(a3.getContents());
				Modelo_de_Marca_e_Raca mR = new Modelo_de_Marca_e_Raca();
				Modelo_de_Marca_e_RacaDAO mRDAO = new Modelo_de_Marca_e_RacaDAO();
				Marca_e_Raca marca = new Marca_e_Raca();
				Marca_e_RacaDAO marcaDAO = new Marca_e_RacaDAO();
				marca = marcaDAO.verifica_Marca_e_Raca_Pelo_Codigo_E_Pela_Classificacao(codigo, classificacao);
				
				if (marca != null) {
					mR.setDescricao(descricao);
					mR.setMarca_e_Raca(marca);
					mR.setCodigo(codigo);
					mR = mRDAO.verifica_Modelo_de_Marca_e_Por_Marca_e_Raca_e_Descricao_e_Tipo_de_Objeto(mR);

					if (mR == null) {
						mR = new Modelo_de_Marca_e_Raca();
						mR.setId_Empresa(1);
						mR.setCodigo(codigo);
						mR.setDescricao(descricao);
						mR.setMarca_e_Raca(marca);
						mR.setId_Empresa(1);
						mR.setId_Pessoa_Registro(responsavel);
						mR.setUltimaAtualizacao(Utilidades.retornaCalendario());
						mR = mRDAO.merge(mR);
						
					}
					

				}

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
