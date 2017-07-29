package br.com.projetabrasil.utilteste;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

import br.com.projetabrasil.model.entities.DAM;
import br.com.projetabrasil.util.PDFManager;



public class TestesImportantes2 {

	@Test
	public void consultaRelacao(){
		List<String> lista = new ArrayList<>();
		lista.add("45350380472188219");
		lista.add("45580390657086771");
		lista.add("45920130165435056");
		lista.add("52080270030032097");
		lista.add("52080510470047420");
		lista.add("52270110133082120");
		lista.add("52460150830035060");
		lista.add("52460151773002888");
		lista.add("52570910387022180");
		lista.add("53140540309165238");
		lista.add("60890450380102580");
		lista.add("66110942040001422");
		try {
			GetDams(lista);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void GetDams(List<String> lista) throws IOException {
		String request        = "http://adm2.pmf.sc.gov.br/stmt/online_emissao_2a_via_iptu.php";
		int i = 1;
		for (String s : lista) {
			
			System.out.println("listando: "+i+"/"+lista.size()+" - horário: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
			i++;
			URL url = new URL(request);
			Map<String,Object> params = new LinkedHashMap<>();		
	        params.put("cd_refr_ano", "2017");
	        params.put("cd_refr", s);
	        params.put("pesquisa", "1");
	        params.put("data_hoje", "27/07/2017");
	        params.put("libera_impressao", "imprimir");
	        
	        

	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	      
	    
	        List<DAM> dams = new ArrayList<>();
	        String  decodedString = null;
	        while ((decodedString = in.readLine()) != null) {
	        	String linha = new String(); 
	        	linha = in.readLine() ;
	            
	           if(linha !=null && linha.contains("id=nu_dam value=")){
	        	   DAM dam = new DAM();
	        	   dam.setChave("nu_dam[]");
	        	   dam.setValor(linha.replaceAll("[^0-9]", ""));
	        	   dams.add(dam);        	   
	        	   
	            }
	           
	        }
	        in.close();
	        downloadUrl(s, dams);
			
		}
		System.out.println("download terminado: - horário: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		System.out.println("==================================");
		System.out.println("iniciando filtragem dos dados"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		System.out.println("");
		System.out.println("");
		
		for (String s : lista) {
			
		teste2("C:\\Users\\PAULO\\Desktop\\arquivos text\\"+s+".pdf");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("finalizando filtragem dos dados"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		
		
		
        
        	
	}

	public void downloadUrl(String im, List<DAM> dams) throws IOException {		
		String request        = "http://adm2.pmf.sc.gov.br/stmt/segunda_via_internet.pdf.php";
		final int BUFFER_SIZE = 4096;
		
		URL url = new URL(request);
		Map<String,Object> params = new LinkedHashMap<>();
		int i=0;
		for (DAM dam : dams) {
			params.put("nu_dam["+i+"]" ,dam.getValor());
			System.out.println("IM: "+im+" - dam: "+dam.getValor()+"("+i+"/"+dams.size()+")");
			
			i++;
			
		}
        	
        params.put("data", "");
        params.put("controle", "ADMIN");
        params.put("pesquisa", "");
        params.put("data_hoje", "28/07/2017");
        params.put("libera_impressao", "imprimir");
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            String chave = param.getKey();
            if(chave.contains("nu_dam"))
            	chave = "nu_dam[]";
            else
            	chave = param.getKey();
            	
            postData.append(URLEncoder.encode(chave, "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        
        
      
       File destino = new File("C:\\Users\\PAULO\\Desktop\\arquivos text\\"+im+".pdf");
        final PDDocument document;
        try (InputStream urlStream = url.openStream()) {
            document = PDDocument.load(conn.getInputStream(), "UTF-8");
            document.save(destino);
        }
       
	}
	
	public void teste2(String caminho) {

		PDFManager pdfManager = new PDFManager();
	       pdfManager.setFilePath(caminho);
	       try {
			pdfManager.ToText(caminho);
		} catch (IOException e) {
			// TODO Auto-generated catxch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

	

}
