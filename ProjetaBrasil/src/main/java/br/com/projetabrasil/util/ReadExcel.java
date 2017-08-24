package br.com.projetabrasil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.projetabrasil.model.entities.InscricaoMunicipal;


public class ReadExcel {

	

	public List<InscricaoMunicipal> readFromExcel(String excelFilePath) throws IOException {
		List<InscricaoMunicipal> lista = new ArrayList<>();
		
		String rel;

        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
            	rel = "";
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:{
                        rel = cell.getStringCellValue().replaceAll("[^0-9]", "").trim() ;
                        
                        break;}
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                rel = rel.trim();
                if(rel.length()>0 )
                {
                	InscricaoMunicipal im = new InscricaoMunicipal();
                	im.setnInsc(rel);
               	lista.add(im);
                }
                
            }
            
        }
         
        //workbook.close();
        inputStream.close();
        return lista;
	}

}