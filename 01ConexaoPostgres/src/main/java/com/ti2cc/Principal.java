package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Games game = new Games("nome do jogo", "aventura", "um jogo ai de teste de aventura", 100);
		if(dao.inserirGame(game) == true) {
			System.out.println("Inserção com sucesso -> " + game.toString());
		}
		
		
		//Atualizar games
		game.setDescricao("nova descricao");
		dao.atualizarGame(game);

	
		
		//Excluir games
		dao.excluirGame(game.getNome());
		
		//Mostrar games
		Games games[] = dao.getGames();
		System.out.println("==== Mostrar usuários === ");		
		for(int i = 0; i < games.length; i++) {
			System.out.println(games[i].toString());
		}
		
		dao.close();
	}
}