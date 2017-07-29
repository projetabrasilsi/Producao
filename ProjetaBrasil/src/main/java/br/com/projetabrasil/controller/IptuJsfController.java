package br.com.projetabrasil.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.util.Utilidades;

@ManagedBean
public class IptuJsfController {
	private UploadedFile file;
	private StreamedContent excel = null;
	
	public void upload(FileUploadEvent event) {
		try {
			try {
				excel = new DefaultStreamedContent(event.getFile().getInputstream());
				setFile(event.getFile());
			} catch (IOException e) {

			}

			UploadedFile arquivoUpload = event.getFile();
			Utilidades.gravaDiretorio(Utilidades.getCaminhoiptu());		
			Path destino = Paths.get(Utilidades.getCaminhoiptu()+"pilha1.xls");		
			Files.copy(arquivoUpload.getInputstream(), destino, StandardCopyOption.REPLACE_EXISTING);
			lerExcel();
			Utilidades.mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			Utilidades.mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}
	public void lerExcel(){
       
		
		
		InputStream ExcelFileToRead = null;
		try {
			ExcelFileToRead = new FileInputStream(Utilidades.getCaminhoiptu()+"pilha1.xls");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(ExcelFileToRead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();
		
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	
	}
	 
    public StreamedContent getExcel() {
		return excel;
	}

	public void setExcel(StreamedContent excel) {
		this.excel = excel;
	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    

}
