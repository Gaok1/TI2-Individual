package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "ti2cc-teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "gaok1";
		String password = "2004";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!\n\n");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirGame(Games game) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO game (id, nome, descricao, preco) "
					       + "VALUES ("+game.getid()+ ", '" + game.getNome() + "', '"  
					       + game.getDescricao() + "', '" + game.getPreco() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarGame(Games game) {
		boolean status = false;
		try {
			String sql = "UPDATE game SET preco = ?, nome = ?, descricao = ? WHERE id = ?";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setDouble(1, game.getPreco());
			preparedStatement.setString(2, game.getNome());
			preparedStatement.setString(3, game.getDescricao());
			preparedStatement.setInt(4, game.getid());
	
			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				status = true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}
	
	
	public boolean excluirGame(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM game WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Games[] getGames() {
		Games[] games = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM game");		
	         if(rs.next()){
	             rs.last();
	             games = new Games[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                games[i] = new Games(rs.getInt("id"), rs.getString("nome"), 
	                		                  rs.getString("descricao"), rs.getInt("preco"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return games;
	}

	

}