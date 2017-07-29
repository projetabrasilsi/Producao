package br.com.projetabrasil.model.services;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Pessoa;

public class PessoaAdapter extends TypeAdapter<Pessoa> {

    @Override
    public void write(JsonWriter writer, Pessoa value) throws IOException {
        writer.beginObject();
        
        
        Enum_Aux_Sim_ou_Nao AutoPontuacao = Enum_Aux_Sim_ou_Nao.NAO;

     	writer.name("id").value(value.getId());
        
        writer.name("descricao").value(value.getDescricao());
        writer.name("fantasia_Apelido").value(value.getFantasia_Apelido());
        writer.name("identificador").value(value.getIdentificador());
        writer.name("dataNascimento").value(value.getDataNascimento().toString());
        writer.name("cpf_Cnpj").value(value.getCpf_Cnpj());
        writer.name("rg_Insc").value(value.getRg_Insc());
        writer.name("sexo").value(value.getSexo().getAbrev());
        writer.name("fone_1").value(value.getFone_1());
        writer.name("fone_2").value(value.getFone_2());
        writer.name("fone_3").value(value.getFone_3());
        writer.name("fone_4").value(value.getFone_4());
        writer.name("fone_5").value(value.getFone_5());
        writer.name("email").value(value.getEmail());
        
        
        if(value.getId_Profissao()!=null)
        writer.name("id_Profissao").value(value.getId_Profissao().getDescricao());
        
        
        writer.name("fcm_Tokem").value(value.getFcm_Tokem());
        
        
        
        
        
        
        if (AutoPontuacao != null) 
            writer.name("AutoPontuacao").value(value.getAutoPontuacao().getDescricao());
           
        
        if (value.getId_Pessoa_Indicacao() != null) 
            writer.name("id_Pessoa_indicacao").value(value.getId_Pessoa_Indicacao().getId());
               
        if (value.getId_Pessoa_Registro() != null) 
            writer.name("id_Pessoa_Registro").value(value.getId_Pessoa_Registro().getId());
        
        
        if (value.getEnum_Aux_Tipo_Identificador() != null) {
            writer.name("enum_Aux_Tipo_Identificador").value(value.getEnum_Aux_Tipo_Identificador().getDescricao());
        }
        
        
        writer.endObject();
    }

    @Override
    public Pessoa read(JsonReader in) throws IOException {
        // do something you need
        return null;
    }

}