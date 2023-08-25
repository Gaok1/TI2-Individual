package com.ti2cc;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAO dao = new DAO();
        dao.conectar();
        
        int opcao;
        do {
            System.out.println("===== Menu =====");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Remover");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    // Listar games
                    Games[] games = dao.getGames();
                    System.out.println("==== Lista de Games === ");        
                    for (Games game : games) {
                        System.out.println(game.toString());
                    }
                    break;
                case 2:
                    // Adicionar game
                    System.out.print("Informe o ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Informe o nome: ");
                    String nome = scanner.next();
                    System.out.print("Informe a descrição: ");
                    String descricao = scanner.next();
                    System.out.print("Informe o preço: ");
					scanner.nextLine();
                    int preco = scanner.nextInt();
                    
                    Games newGame = new Games(id, nome, descricao, preco);
                    if (dao.inserirGame(newGame)) {
                        System.out.println("Jogo adicionado com sucesso.");
                    } else {
                        System.out.println("Erro ao adicionar o jogo.");
                    }
                    break;
                case 3:
                    // Remover game
                    System.out.print("Informe o ID do jogo a ser removido: ");
                    int idRemover = scanner.nextInt();
                    if (dao.excluirGame(idRemover)) {
                        System.out.println("Jogo removido com sucesso.");
                    } else {
                        System.out.println("Erro ao remover o jogo.");
                    }
                    break;

					case 4:
					// Atualizar game
					System.out.print("Informe o ID do jogo a ser atualizado: ");
					int idAtualizar = scanner.nextInt();
					
					// Verificar se o jogo existe antes de atualizar
					Games jogoExistente = new Games();
					
					System.out.print("Informe o novo nome: ");
					String novoNome = scanner.next();
					System.out.print("Informe a nova descrição: ");
					String novaDescricao = scanner.next();
					scanner.nextLine();
					System.out.print("Informe o novo preço: ");
					int novoPreco = scanner.nextInt();
					
					// Atualizar o jogo
					jogoExistente.setNome(novoNome);
					jogoExistente.setDescricao(novaDescricao);
					jogoExistente.setPreco(novoPreco);
					
					if (dao.atualizarGame(jogoExistente)) {
						System.out.println("Jogo atualizado com sucesso.");
					} else {
						System.out.println("Erro ao atualizar o jogo.");
					}
					break;
				

                case 5:
                    System.out.println("Saindo do menu.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
        
        dao.close();
        scanner.close();
    }
}
