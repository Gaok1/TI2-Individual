package com.ti2cc;

public class Games {
	private String nome;
	private String genero;
	private String descricao;
	private int preco;
	
	public Games() {
		this.nome = "";
		this.genero = "";
		this.descricao = "";
		this.preco = -1;
	}
	
	public Games(String nome, String genero, String descricao, int preco) {
		this.nome = nome;
		this.genero = genero;
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + nome + ", login=" + genero + ", senha=" + descricao + ", sexo=" + preco + "]";
	}	
}
