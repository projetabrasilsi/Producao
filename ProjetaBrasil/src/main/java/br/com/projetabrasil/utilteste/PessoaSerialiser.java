package br.com.projetabrasil.utilteste;
import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.com.projetabrasil.model.entities.Pessoa;


public class PessoaSerialiser implements JsonSerializer<Pessoa> {

    

	public JsonElement serialize(final Pessoa person, final Type type, final JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        
        
        
        result.add("id", new JsonPrimitive(person.getId()));
        result.add("enum_Aux_Tipo_Identificador", new JsonPrimitive(person.getEnum_Aux_Tipo_Identificador().getDescricao()));
        result.add("descricao", new JsonPrimitive(person.getDescricao()));
        result.add("fantasia_Apelido", new JsonPrimitive(person.getFantasia_Apelido()));
        result.add("identificador", new JsonPrimitive(person.getIdentificador()));
        result.add("dataNascimento", new JsonPrimitive(person.getDataNascimento().toString()));
        result.add("cpf_Cnpj", new JsonPrimitive(person.getCpf_Cnpj()));
        result.add("rg_Insc", new JsonPrimitive(person.getRg_Insc()));
        result.add("sexo", new JsonPrimitive(person.getSexo().getAbrev()));
        result.add("fone_1", new JsonPrimitive(person.getFone_1()));
        result.add("fone_2", new JsonPrimitive(person.getFone_2()));
        result.add("fone_3", new JsonPrimitive(person.getFone_3()));
        result.add("fone_4", new JsonPrimitive(person.getFone_4()));
        result.add("fone_5", new JsonPrimitive(person.getFone_5()));
        result.add("email", new JsonPrimitive(person.getEmail()));
       /* result.add("id_Pessoa_Registro", new JsonPrimitive(person.getId_Pessoa_Registro().getId() ));
        result.add("id_Pessoa_indicacao", new JsonPrimitive(person.getId_Pessoa_Indicacao().getIdentificador()));*/
        result.add("AutoPontuacao", new JsonPrimitive(person.getAutoPontuacao().getDescricao()));
        result.add("id_Profissao", new JsonPrimitive(person.getId_Profissao().getDescricao()));
        result.add("fcm_Tokem", new JsonPrimitive(person.getFcm_Tokem()));
        

        
        
        
        
        
        Pessoa id_Pessoa_Registro = person.getId_Pessoa_Registro();
        if (id_Pessoa_Registro != null) {
            result.add("id_Pessoa_Registro", new JsonPrimitive(id_Pessoa_Registro.getId()));
        }
        
        Pessoa id_Pessoa_indicacao = person.getId_Pessoa_Indicacao();
        if (id_Pessoa_indicacao != null) {
            result.add("id_Pessoa_indicacao", new JsonPrimitive(id_Pessoa_indicacao.getId()));
        }
        return result;
    }
}
