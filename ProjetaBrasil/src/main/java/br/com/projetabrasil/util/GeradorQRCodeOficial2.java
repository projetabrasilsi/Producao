package br.com.projetabrasil.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;

public class GeradorQRCodeOficial2 {

	public void geraNovosCodigos() {
		UsuarioDAO usDAO = new UsuarioDAO();
		Usuario us = usDAO.autenticar("89230906115", "P2a3u0l9");
		PerfilLogado pf = new PerfilLogado();
		pf.setUsLogado(us);
		geraNovosCoders(1, pf);
	}

	public void geraNovosCoders(int qtde, PerfilLogado pf) {
		int contador = 0;
		QRCodeDAO qDAO = new QRCodeDAO();
		contador = qDAO.retornaOUltimoCoders();

		for (int i = 0; i < qtde; i++) {
			contador++;

			String cript = "" + contador;
			cript = StringUtils.leftPad(String.valueOf(cript), 8, "0");
			String cp = cript;

			SimpleHash hash = new SimpleHash("md5", cript);
			cript = hash.toHex();
			QRCode e = new QRCode(pf.getUsLogado().getPessoa(), Enum_Aux_Status_QRCodes.LIVRES, cript);
			qDAO.merge(e);
			System.out.println("Gerando: "+i+"/"+qtde);

			

		}

	}

	public int retornaQtdes(double T, double t){
		double res = T/t;
		int  ret = (int) Math.floor(res);
		return ret;
	}
	
	public void paint(Graphics2D g) {
	    AffineTransform atrans = null;

	    Graphics2D g2d = (Graphics2D) g;
	    atrans = AffineTransform.getScaleInstance(2, 3);

	    if (atrans != null)
	      g2d.setTransform(atrans);

	    g2d.fillRect(50, 50, 100, 50);
	  }
	
	@SuppressWarnings("unused")
	public AlturaLargura pegaResolucao(float cmW, float cmH){
	AlturaLargura altLarg = new AlturaLargura(); 
	 Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
     int lar = (int) tela.getWidth();
     int alt = (int) tela.getHeight();
    
     
     float dpiW = cmW*0.394f*lar;
     float dpiH = cmH*0.394f*alt;
    
     
     float pxW = cmW*300/2.5f;
     float pxH = cmH*300/2.5f;
     
    
     altLarg.setLarg((int) Math.ceil(pxW ));
     altLarg.setAlt((int)Math.ceil(pxH));
     return altLarg;
	}
	
	
	public int retornaTamanho(float tam, int offset){
		int retorno = (int) (tam*300f/2.5f)+offset;
		return retorno;
	}
	
	
	public int GerandoAsImagens(float containerW, float containerH, float conteudoW, float conteudoH, int contagemInicial ) {
		
		int pxW = retornaTamanho(conteudoW,5); 
		int pxH =  retornaTamanho(conteudoH,5);
		int W =  retornaTamanho(containerW,0);
	    int H =  retornaTamanho(containerH,0);
	    //retornaNLinhas(nEtiq, nCol);
	    int nCol = retornaQtdes(W,pxW);
		
		int nLin = retornaQtdes(H,pxH);
		String caminho = "/imagens/QRCODE-NOVOS/qr2/";
		int contador=contagemInicial-1;
		BufferedImage im1 = null;
		File f = new File(caminho + "qrcode.jpeg");
		
		try {
			im1 = conversaoqrcode(1);
		} catch (WriterException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedImage im2 = null;
		String texto = ""+(1);				
		texto = StringUtils.leftPad(String.valueOf(texto), 15, "0");
		im2 = createImageWithText(texto, im1.getWidth(), 25);
		BufferedImage joinedImg = joinBufferedImage(im1, im2);
		
		
		joinedImg = Scalr.resize(joinedImg, Method.QUALITY, pxW, pxH);
		
		BufferedImage imageGeral = new BufferedImage(joinedImg.getTileWidth()*nCol, 
				joinedImg.getHeight()*nLin , BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = imageGeral.createGraphics();
		g2.setColor(Color.WHITE);
		
		
	    
		
	    g2.fillRect(0, 0, W,H);
		g2.setColor(Color.BLACK);
		
		for( int i = 1; i<=nLin;i++){
			for( int j = 1; j<=nCol;j++){
				contador++; 
				try {
					im1 = conversaoqrcode(contador);
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				texto = ""+(contador);				
				texto = StringUtils.leftPad(String.valueOf(texto), 15, "0");
				im2 = createImageWithText(texto, im1.getWidth(), 25);
				joinedImg = joinBufferedImage(im1, im2);
				
				java.awt.Image tmp = joinedImg.getScaledInstance(pxW, pxH, java.awt.Image.SCALE_SMOOTH);
				BufferedImage dimg = new BufferedImage(pxW, pxH, BufferedImage.TYPE_INT_ARGB);

				Graphics2D g2d = dimg.createGraphics();
				g2d.setColor(Color.WHITE);
				g2d.fillRect(0, 0, pxW,pxH);
				g2d.setColor(Color.BLACK);
				    
				g2d.drawImage(tmp, 0, 0, null);
				g2d.dispose();

				
				
				BufferedImage dimensionado = Scalr.resize(joinedImg, Method.BALANCED, pxW, pxH);
				g2.drawImage(dimg, null, (j-1)*dimg.getWidth(),(i-1)*dimg.getHeight());
				
				try {
					String especificacao = "etiqueta.jpeg";
					f = new File(caminho +  especificacao);
					boolean success = ImageIO.write(dimensionado, "jpeg", f);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}	
		}
		g2.dispose();
		try {
			String especificacao = "TamPag "+containerW+"cm x "+containerH+"cm - TamEtiq "+conteudoW+"cm x "+conteudoH+"cm"+
		    " nLinhas "+nLin+"lin x nColunas "+nCol+"col"+" - total "+nLin*nCol+".jpeg";
			f = new File(caminho +  especificacao);
			boolean success = ImageIO.write(imageGeral, "jpeg", f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contador;
		
		
				
	}

	public BufferedImage conversaoqrcode(int qRAtual) throws WriterException{
		QRCode qr = new QRCode();
		QRCodeDAO qrDAO = new QRCodeDAO();
		qr = qrDAO.buscaCodersId("" + qRAtual);
		String texto = "http://www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=" + qr.getCoders();
		int size =240;//tam.getTamQrCodeArea();
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 0); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
 
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
		
		return image;
	}
	
	

	
	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
		int offset = 0;
		int wid = 0;
		int height = 0;
		
		wid = Math.max(img1.getWidth() , img2.getWidth()) + offset;
    	height = img1.getHeight()+img2.getHeight() + offset;
    		
        

		
		
		// create a new buffer and draw two image into the new image
		BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = newImage.createGraphics();		
		g2.setColor ( new Color ( 5, 5, 5 ) );
		g2.fillRect ( 0, 0, wid, height);
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, wid, height);
        
				
		
		g2.drawImage(img1, null, 0, 0);		
		g2.drawImage(img2, null, 0,img1.getHeight() + offset);
		g2.dispose();
		
		return newImage;
	}
	
	
	private static BufferedImage createImageWithText(String texto, int w, int h) {
		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.createGraphics();
		g.setColor(Color.white);
		
		g.fillRect(0, 0, w, h);
		g.setColor(Color.BLACK);
		java.awt.Font font = new java.awt.Font("Tahoma", Font.BOLD, 24);
		g.setFont(font);
		g.drawString(texto, 9, h - 7);
		return bufferedImage;

	}
	
	
	
	public void gerarPdf(BufferedImage im) throws Exception {
		float largura, altura;
		largura = im.getWidth()+10;
		altura = im.getHeight()+10;
		String caminho = "C:\\imagens\\QRCODE-NOVOS\\qr2\\";

		Rectangle pageSize = new Rectangle(largura, altura);

		Document document = new Document(pageSize);

		try {

			String nomeArq = caminho + "arquivos em pdf.pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nomeArq));
			document.setMargins(0f, 0f, 0f, 0f);
			document.open();

			PdfPTable table2 = new PdfPTable(20);
			table2.setWidthPercentage(100);
			
			PdfContentByte pdfCB = new PdfContentByte(writer);
			Image image = Image.getInstance(pdfCB, im, 1);
			
			PdfPCell cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			//cell.setNoWrap(true);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			

			cell.addElement(image);
			table2.addCell(cell);
			document.add(table2);

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

	


}
