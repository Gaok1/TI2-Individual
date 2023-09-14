package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Game{
	private String nome;
	private int id;
	private String descricao;
	private int preco;
	
	public Game() {
		this.nome = "";
		this.id = -1;
		this.descricao = "";
		this.preco = -1;
	}
	
	public Game(int id, String nome, String descricao, int preco) {
		this.nome = nome;
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
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
		return "game [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}	
}