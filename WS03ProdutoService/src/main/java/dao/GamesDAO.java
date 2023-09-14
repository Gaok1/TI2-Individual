package dao;

import model.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class GamesDAO extends DAO {	
	public GamesDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean inserirGame(Game game) {
		boolean status = false;
		try {
			String sql = "INSERT INTO game (id, nome, descricao, preco) VALUES (?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, game.getid());
			st.setString(2, game.getNome());
			st.setString(3, game.getDescricao());
			st.setFloat(4, game.getPreco());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	

	
	public Game get(int id) {
		Game game = null;
		
		try {
			String sql = "SELECT * FROM game WHERE id=?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				game = new Game(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getInt("preco"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return game;
	}
	
	public List<Game> get() {
		return get("");
	}

	
	public List<Game> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Game> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Game> getOrderByPreco() {
		return get("preco");		
	}

	public List<Game> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Game> get(String orderBy) {
		List<Game> games = new ArrayList<>();
	
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM game" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Game game = new Game(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("preco"));
				games.add(game);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return games;
	}
	
	
	
	
	public boolean update(Game game) {
		boolean status = false;
		try {
			String sql = "UPDATE game SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, game.getNome());
			st.setString(2, game.getDescricao());
			st.setFloat(3, game.getPreco());
			st.setInt(4, game.getid());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}