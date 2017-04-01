import org.junit.Test;

import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Relacionamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_de_Contato;
import br.com.projetabrasil.util.Utilidades;

public class ContatoDAOTest {
	@Test
	public void testaInsercao(){
		Contato c = new Contato();
		ContatoDAO cDAO = new ContatoDAO();
		c.setContato("111");
		c.setId(null);
		c.setId_Empresa(0);
		c.setId_Pessoa(new PessoaDAO().retornaPelaIdentificacao("10554498928"));
		c.setId_Pessoa_Registro(new PessoaDAO().retornaPelaIdentificacao("10554498928"));
		c.setTipoContato(Enum_Aux_Tipo_de_Contato.CELULAR);
		c.setTipoRelacionamento(Enum_Aux_Tipo_Relacionamento.PROPRIO);
		c.setUltimaAtualizacao(Utilidades.retornaCalendario());
		cDAO.merge(c);
		
	}
	
}
