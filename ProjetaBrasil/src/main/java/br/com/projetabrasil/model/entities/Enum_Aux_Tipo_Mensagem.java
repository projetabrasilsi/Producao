package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Mensagem {
	INCLUSAO("Inclusao","Registro incluido com sucesso!!!"),
	ALTERACAO("Alteracao","Registro alterado com sucesso!!!"),
	EXCLUSAO("Exclusao","Exclusao efetuada com sucesso!!!"),
	NULO("Nulo","Objeto está Nulo"),
	EXISTENTE("Existente","Já existe um registro cadastrado!!!"),
	ERRINCLUSAO("ErrInclusao","Erro ao tentar incluir registro!!!"),
	ERRALTERACAO("ErrAlteracao","Erro ao tentar alterar registro!!!"),
	ERREXCLUSAO("ErrExclusao","Erro ao tentar excluir registro!!!"),
	QUESTIONEXCLUSAO("QuestionExclusao","Deseja realmente excluir este registro?"),
	ERRLOGAR("ErrLogar","Ocorreu um erro ao tentar logar!!!"),
	ERRACESSOPESSOAS("ErrAcessoPessoas","Ocorreu um erro ao tentar acessar a Página de Cadastro de Pessoas!!!"),
	ERRAUTENTICACAO("errAutenticacao","Usuário ou senha inválido(s)"),
	ERRSENHADIFERENTE("errSenhaDiferente","Senha e confirmação de senha não confere");
	
	String tipo, mensagem;
	
	public String getTipo() {
		return tipo;
	}
	

	public String getMensagem() {
		return mensagem;
	}

	

	Enum_Aux_Tipo_Mensagem(String tipo, String mensagem){
		this.tipo = tipo;
		this.mensagem = mensagem;
		
	}
	
	

}
