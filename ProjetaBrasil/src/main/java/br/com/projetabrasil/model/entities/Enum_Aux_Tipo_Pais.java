package br.com.projetabrasil.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


	//private PaisConfig paisConfig = new PaisConfig("Nome do País");
		public enum Enum_Aux_Tipo_Pais{
			BRASIL,//("00000-000", 1/*, true*/),
			EUA;//("000-000", 2/*, false*/);
			
//			private String codeMask;
//			private int id;
//			//private boolean selecionar;
//			
//			public Enum_Aux_Tipo_Pais(String m, int i){
//				this.codeMask = getZoneCodeMask(percorrerTodos(m));
//				this.id = 0 ;
//			} 
//			
//			public Enum_Aux_Tipo_Pais(String m){
//				this.codeMask = getZoneCodeMask(percorrerTodos(m));
//			}
	
			public static Enum_Aux_Tipo_Pais percorrerTodos(String mask){
				
				List<Enum_Aux_Tipo_Pais> eaux = zoneCodeList();
						for(Enum_Aux_Tipo_Pais ePG : eaux){
							if(mask.toLowerCase().equals(ePG.toString().toLowerCase())){
							return ePG;
							}
						}
				return null;
			}

			public static List<Enum_Aux_Tipo_Pais> zoneCodeList(){
				return new ArrayList<Enum_Aux_Tipo_Pais>();
			}
			
			
			public static String getZoneCodeMask(Enum_Aux_Tipo_Pais eATP) {
			return getMapCodeMask().get(eATP);
			}
			
			
			public static Map<Enum_Aux_Tipo_Pais, String> getMapCodeMask(){
				Map<Enum_Aux_Tipo_Pais, String> codeMasks = new HashMap<>();
				codeMasks.put(Enum_Aux_Tipo_Pais.BRASIL, "00000-000");
				codeMasks.put(Enum_Aux_Tipo_Pais.EUA, "000-000");
				return codeMasks;
			}
			
		}
