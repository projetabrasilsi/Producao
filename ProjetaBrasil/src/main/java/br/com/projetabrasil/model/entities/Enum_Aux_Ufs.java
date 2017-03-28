package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Ufs {
	
	BRASIL("BRASIL",0, new String[][]  {{"AC","Acre"},{"AL","Alagoas"}, {"AP","Amapa"}, {"AM","Amazonas"}, {"BA","Bahia"}, {"CE","Ceara"}, {"DF","Distrito Federal"}, 
			{"ES","Espirito Santo"}, {"GO","Goias"}, {"MA","Maranhão"},{"MT","Mato Grosso"},{"MS","Mato Grosso do Sul"},
			{"MG","Minas Gerais"},{"PA","Para"},{"PB","Paraiba"},{"PR","Parana"},{"PE","Pernanbuco"}, {"pi","Piaui"}, {"RJ","Rio de Janeiro"},
			{"RN","Rio Grande do Norte"},{"RS","Rio Grande do Sul"},{"RO","Rondonia"},{"RR","Roraima"}, {"SC","Santa Catarina"},
			{"SP","Sao Paulo"},{"SE","Sergipe"},{"TO","Tocantins"}} );
	
	private String pais;
	private int id;
	private String[][] estados;
	
	Enum_Aux_Ufs(String pais, int id, String[][] estados){
		this.pais = pais;
		this.id = id;
		this.estados = estados;
	}
	public  String retornaExtenso(Enum_Aux_Ufs en,String uf){
		if (uf =="")
			return "";
		String extenso ="";
		String[][] estados = en.getEstados();
		String nome;		
		for (int i = 0; i < estados.length; i++) {
	        nome = estados[i][0];
	        if (nome.toUpperCase().equals(uf.toUpperCase())){
	        	extenso = estados [i][1];
	        	return extenso;
	        }
	        	
	        	
	    }
		
		return extenso;
	}
	
	public static Enum_Aux_Ufs retornaEnum(String pais){
		if (pais == null  || pais == "")
			return null;
		for (Enum_Aux_Ufs e : Enum_Aux_Ufs.values()){
			if (e.getPais().equals(pais));
				return e;
		}
		return null;
	}

	public String getPais() {
		return pais;
	}

	public int getId() {
		return id;
	}

	public String[][] getEstados() {
		return estados;
	}
		
	}
	
