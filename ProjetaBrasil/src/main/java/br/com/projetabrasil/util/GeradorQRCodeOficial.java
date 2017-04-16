package br.com.projetabrasil.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.jfree.graphics2d.svg.SVGGraphics2D;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ImgTemplate;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_QRCode_Tamanhos2;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;

public class GeradorQRCodeOficial {

	public void geraNovosCodigos() {
		UsuarioDAO usDAO = new UsuarioDAO();
		Usuario us = usDAO.autenticar("89230906115", "P2a3u0l9");
		PerfilLogado pf = new PerfilLogado();
		pf.setUsLogado(us);		
		geraNovosCoders(10000, pf);
	}

	public void gerarPdf() throws Exception {
		float largura,altura;
		largura =Utilidades.convertcentimetroparaDpi(55f, EnumDpi.DPI072);
		altura =Utilidades.convertcentimetroparaDpi(30f, EnumDpi.DPI072);
		
		Rectangle pageSize = new Rectangle(largura,altura);
		
		//Rectangle pageSize = new Rectangle(PageSize.A4);
		
		Document document = new Document(pageSize);
		try {
			Enum_Aux_QRCode_Tamanhos2 tam = Enum_Aux_QRCode_Tamanhos2.TAM90;			
			String caminho ="C:\\imagens\\QRCODE-NOVOS\\qr2\\";
			String nomeArq =  caminho+ tam.getAbrev() + ".pdf";			
						
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nomeArq));
			document.setMargins(3, 3, 3, 3);
			document.open();
			PdfPTable table2 = new PdfPTable(15);
			int nEtiq = 180;
			for (int i = 1; i <= nEtiq; i++) {
				QRCode qr = new QRCode();
				QRCodeDAO qrDAO = new QRCodeDAO();
				qr = qrDAO.buscaCodersId("" + i);
				String texto = "http://www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=" + qr.getCoders();
				Image im;				
				im = geraQRCode(caminho, texto,i);	
		        im.setAlignment(Image.ALIGN_RIGHT);
		        
		        im.setScaleToFitHeight(false);
		        
				
				
				
				PdfPCell cell = new PdfPCell(im,true);
				cell.setNoWrap(true);
				cell.addElement(im);
				
				
				cell.setBorder(PdfPCell.NO_BORDER);
				//cell.setFixedHeight(120f);
				table2.addCell(cell);
				
				

			}

			document.add(table2);

			////////////////////////////////////////////////////////////////////////////////////////////////////

		} catch (DocumentException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

		finally {
			if (document != null) {
				// fechamento do documento
				document.close();
			}
			if (document != null) {
				// fechamento da stream de saída
				document.close();
			}
		}
		document.close();
	}

	@SuppressWarnings("unused")
	private PdfPTable geraTabelaBase(PdfWriter writer, int i, Enum_Aux_QRCode_Tamanhos2 tam, String texto)
			throws Exception {
		final String IMG1 = "file:///C:\\imagens\\QRCODE-NOVOS\\logo-acheipet.svg";
		final String IMG2 = "file:///C:\\imagens\\QRCODE-NOVOS\\site.svg";
		final String IMG3 = "file:///C:\\imagens\\QRCODE-NOVOS\\qr2\\QRCode.pdf";
		final String IMG4 = "file:///C:\\imagens\\QRCODE-NOVOS\\baixeapp.svg";
		final String IMGCOD = "file:///C:\\imagens\\QRCODE-NOVOS\\qr2\\codigo.jpeg";

		

		int altura = 0;
		int largura = 0;
		ImgTemplate im;

		PdfPTable table = new PdfPTable(1);
		table.setHorizontalAlignment(0);
		table.setTotalWidth(tam.getTabTam());
		table.setLockedWidth(true);

		

		altura = tam.getqRCodeH();
		largura = tam.getqRCodeW();
		PdfPCell cellQRCode = new PdfPCell();
		

		cellQRCode.setPaddingLeft(tam.getqRCodePadL());
		cellQRCode.setPaddingTop(tam.getqRCodePadT());
		
		
		cellQRCode.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cellQRCode);
		

		/*Font f = new Font(FontFamily.TIMES_ROMAN, tam.getParagrafoFZ(), Font.NORMAL, BaseColor.BLACK);
		Paragraph p = new Paragraph(StringUtils.leftPad(String.valueOf(i), 8, "0"), f);
		PdfPCell cellParagrafo = new PdfPCell();
		cellParagrafo.setBorder(PdfPCell.NO_BORDER);
		cellParagrafo.setPaddingLeft(tam.getParagrafoAppPadL());
		cellParagrafo.setPaddingTop(tam.getParagrafoAppPadT());
		cellParagrafo.setColspan(3);
		cellParagrafo.addElement(p);
		table.addCell(cellParagrafo);*/
		return table;
	}
	
	

	

	@SuppressWarnings("unused")
	private static PdfPCell createImageCell(ImgTemplate img) throws DocumentException, IOException {

		PdfPCell cell = new PdfPCell(img, true);
		return cell;
	}

	@SuppressWarnings("unused")
	private static PdfPCell createTextCell(String text) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(text);
		p.setAlignment(Element.ALIGN_RIGHT);
		cell.addElement(p);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private Image geraQRCode(String caminho, String texto, int it) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		int largura,altura;
		
		
		largura = (int)  Utilidades.convertcentimetroparaDpi(1.7f, EnumDpi.DPI200) ;
		 
		altura =(int) Utilidades.convertcentimetroparaDpi(1.3f, EnumDpi.DPI225);
		
		
		BitMatrix bitMatrix = qrCodeWriter.encode(texto, 
				BarcodeFormat.QR_CODE,largura ,altura);
		int w = bitMatrix.getWidth();
		int h = bitMatrix.getHeight();
		SVGGraphics2D g2 = new SVGGraphics2D(w, h);
		g2.setColor(Color.BLACK);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (bitMatrix.get(i, j)) {
					g2.fillRect(i, j, 1, 1);
				}
			}
		}

		File f = new File(caminho + "qrcode.jpeg");
		ImageIO.write(drawCodeOnImage(bitMatrix), "JPEG", f);

		String hex = "" +it;
		convertStringParaImage(caminho, "qrcode.jpeg", hex, largura,altura);
		Image jpg = Image.getInstance(caminho+"codigo.jpeg");
		
		
		
		return jpg;
	}

	private static BufferedImage drawCodeOnImage(BitMatrix bitMatrix) {

		if (bitMatrix == null) {
			throw new IllegalArgumentException("BitMatrix cannot be null");
		}

		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (bitMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		return image;
	}

	public BufferedImage convertStringParaImage(String caminho, String arquivo, String texto, int larg, int alt) throws IOException, InterruptedException {		
		BufferedImage image = ImageIO.read(new File(caminho+arquivo));
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		
		texto = StringUtils.leftPad(String.valueOf(texto), 10, "0");
		g.drawString(texto, larg, alt);
		g.dispose();
		
		File f = new File(caminho + "codigo.jpeg");
		ImageIO.write(image, "JPEG", f);
        BufferedImage bufferedImage = ImageIO.read(new File(caminho+arquivo));
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 0, 0);
        graphics.setColor(Color.black);        
        java.awt.Font font = new java.awt.Font("Tahoma", Font.BOLD, 12);
        graphics.setFont(font);
        graphics.drawString(texto, 27,bufferedImage.getHeight()-25);        
        ImageIO.write(bufferedImage, "jpeg",f);
        f = new File(caminho + "codigo.jpeg");
        //Thread.sleep(20);        
        return pegaImagemEmAlgumLugar(caminho,"codigo.jpeg");
        
        
        
           
        
        
	}
	
	public BufferedImage pegaImagemEmAlgumLugar(String caminho, String arquivo) throws IOException, InterruptedException {		
		BufferedImage image = ImageIO.read(new File(caminho+arquivo));
        return image;
	}
	
	
	
	public void geraNovosCoders(int qtde, PerfilLogado pf) {		
		int contador = 0;		
		QRCodeDAO qDAO = new QRCodeDAO();
		contador = qDAO.retornaOUltimoCoders();
		
		for (int i = 0; i < qtde; i++) {
			contador++;

			String cript = ""+contador;
			cript = StringUtils.leftPad(String.valueOf(cript), 8, "0");
			String cp = cript;

			SimpleHash hash = new SimpleHash("md5", cript);
			cript = hash.toHex();
			QRCode e = new QRCode(pf.getUsLogado().getPessoa(), Enum_Aux_Status_QRCodes.LIVRES, cript);			
			qDAO.merge(e);
			
			

		}

	}

}
