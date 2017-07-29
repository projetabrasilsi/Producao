package br.com.projetabrasil.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import br.com.projetabrasil.model.entities.Parcelamentos;
import jxl.Workbook;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class PDFManager {

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;

	private String Text;
	private String filePath;
	private File file;

	public PDFManager() {

	}

	public String ToText(String caminho) throws IOException {
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;
		filePath = caminho;

		file = new File(filePath);
		parser = new PDFParser(new RandomAccessFile(file, "r")); // update for
																	// PDFBox V
																	// 2.0

		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		
		List<Parcelamentos> parcs = new ArrayList<>();

		
		for (int ii = 1; ii <= pdDoc.getNumberOfPages(); ii++) {
			PDFTextStripper pdfStripper1 = new PDFTextStripper();
			pdfStripper1.setStartPage(ii);
			pdfStripper1.setEndPage(ii);
			Text = pdfStripper1.getText(pdDoc);
			Parcelamentos parc = new Parcelamentos();

			StringWriter output = new StringWriter();

			output.append(Text);
			String arquivo = caminho.substring(0, caminho.indexOf("pdf"));
			arquivo += "txt";
			 
			FileWriter fw = new FileWriter(arquivo);
			fw.write(output.toString());
			fw.close();

			try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
				String line;
				int i = 0;
				while ((line = br.readLine()) != null) {
					// System.out.println("Dados da Linha:(" + i + "): " +
					// line);
					if (i == 11)
						parc.setnParc(line);
					if (i == 34 || i == 35) {
						if (line.toLowerCase().contains("total"))
							parc.setTotal(line);
					}
					if (i == 35 || i == 36) {
						if (line.toLowerCase().contains("numero do dam"))
							parc.setDam(line);
					}
					if (i == 36 || i == 37) {
						if (line.toLowerCase().contains("sacado")){
							parc.setSacado(line);
						    line = br.readLine();
						    parc.setNome(line);
						    i++;
						}
					}

					

					if (i == 41 || i == 42)

						if (line.toLowerCase().contains("vencimento")) {
							line = br.readLine();
							parc.setVencimento(line);
							i++;

						}

					if (i == 52 || i == 53) {
						if (line.toLowerCase().contains("caixa"))
							parc.setBoleto(line);
					}

					i++;

				}
				
				parcs.add(parc);
			}
		}
		pdDoc.close();

		String arquivo = caminho.substring(0, caminho.indexOf("pdf"));
		arquivo += "xls";
		   
		 WritableWorkbook planilha = Workbook.createWorkbook(new File(arquivo)); 
         WritableSheet sheet = planilha.createSheet("IPTU-Coleta " , 0);
         String cabecalho[] = new String[7];
         cabecalho[0] = "SEQ.";
         cabecalho[1] = "TOTAL";
         cabecalho[2] = "VENCIMENTO";
         cabecalho[3] = "BOLETO";
         cabecalho[4] = "DAM";
         cabecalho[5] = "SACADO";
         cabecalho[6] = "NOME";
         for (int z = 0; z < cabecalho.length; z++) {
             Label label = new Label(z, 0, cabecalho[z]);
             try {
				sheet.addCell(label);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         
         
         int iii = 1; 
		for (Parcelamentos prc : parcs) {
			prc.setnParc(prc.getnParc().replace(" CR REAL", ""));
			prc.setTotal(prc.getTotal().replace("TOTAL", ""));
			prc.setTotal(prc.getTotal().trim());
			prc.setDam(prc.getDam().replace("Numero do DAM: ", ""));
			prc.setDam(prc.getDam().trim());
			prc.setSacado(prc.getSacado().replace("Sacado NP/CPF/CNPJ: ", ""));
			prc.setSacado(prc.getSacado().replace("Autenticação Mecânica", ""));
			prc.setSacado(prc.getSacado().trim());
			
			prc.setVencimento(prc.getVencimento().trim());
			prc.setBoleto(prc.getBoleto().substring(0, 59));
			prc.setBoleto(prc.getBoleto().trim());
						 
			
			 
			try {
				WritableCell cel = new Label(0, iii, prc.getnParc()); 
				sheet.addCell(cel);
				prc.setTotal(prc.getTotal().replace(",","."));
				Double total = Double.parseDouble(prc.getTotal());
				
				cel = new jxl.write.Number(1, iii, total);  
				sheet.addCell(cel);
				
				Date data =  new Date(prc.getVencimento());
				DateFormat customDateFormat = new DateFormat("dd/MM/yyyy");
			    WritableCellFormat dateFormat = new  WritableCellFormat(customDateFormat);
			    DateTime dateTime = new DateTime(2, iii, data, dateFormat);
				
				  
				sheet.addCell(dateTime);
				
				cel = new Label(3, iii, prc.getBoleto());  
				sheet.addCell(cel);
				
				cel = new Label(4, iii, prc.getDam());  
				sheet.addCell(cel);
				
				cel = new Label(5, iii, prc.getSacado());  
				sheet.addCell(cel);
				
				cel = new Label(6, iii, prc.getNome());  
				sheet.addCell(cel);
					
				
				
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
			iii++;
		}
		
		
        try {
        	planilha.write();
			planilha.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

		
		return Text;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}