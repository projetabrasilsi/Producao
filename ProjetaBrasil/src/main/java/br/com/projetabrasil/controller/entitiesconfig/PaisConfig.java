package br.com.projetabrasil.controller.entitiesconfig;

import java.io.Serializable;

import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pais;
import br.com.projetabrasil.model.entities.Pais;


public class PaisConfig implements Serializable{
		
	private static final long serialVersionUID = -3948238679420937168L;

			private Pais pais;

			private String descPais;
			private String zoneCodeMask;
			private Enum_Aux_Tipo_Pais zoneCodeNum;

			
			
		public void mudarLabels(Enum_Aux_Tipo_Pais zoneCodeNum){
			
			
			
			
		}
		
		public Pais getPais() {
			return pais;
		}

//		public void setPais(Pais pais) {
//			this.pais = pais;
//		}


		public String getDescPais() {
			return descPais;
		}

		public void setDescPais(String descPais) {
			this.descPais = descPais;
		}



		public Enum_Aux_Tipo_Pais getZoneCodeNum() {
			return this.zoneCodeNum;
		}
		
		public Enum_Aux_Tipo_Pais setZoneCodeNum() {
			return this.zoneCodeNum;
		}
			
		public String getZoneCodeMask() {
		return this.zoneCodeMask;
		}
		
		public void setZoneCodeMask() {
			if(this.getZoneCodeNum()==null)
				this.setZoneCodeNum();
			this.zoneCodeMask = Enum_Aux_Tipo_Pais.getZoneCodeMask(this.zoneCodeNum);
		}
		
				



		
		public PaisConfig(String desc) {
			this.descPais = "Brasil";
			this.zoneCodeNum=Enum_Aux_Tipo_Pais.percorrerTodos(this.descPais);
			this.zoneCodeMask = Enum_Aux_Tipo_Pais.getZoneCodeMask(this.zoneCodeNum);

		}

}
