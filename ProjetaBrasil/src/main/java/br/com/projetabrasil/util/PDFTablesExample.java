package br.com.projetabrasil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

public class PDFTablesExample {
	
	
	public static void pdftoText(String fileName) {
		PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        try {
            PDFParser parser = new PDFParser((RandomAccessRead) new FileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            String parsedText = pdfStripper.getText(pdDoc);
            System.out.println(parsedText);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
	

	public static void retornaexcel(String pdf, String txt) throws IOException {		 	
		PdfReader reader = new PdfReader(pdf);
	    StringWriter output = new StringWriter();  
	        try {
	            output.append(PdfTextExtractor.getTextFromPage(reader, 1, new SimpleTextExtractionStrategy()));	            
	            FileWriter fw = new FileWriter(txt);	            
	            fw.write(output.toString());
	            fw.close();
	        } catch (OutOfMemoryError e) {

	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    

	}

}