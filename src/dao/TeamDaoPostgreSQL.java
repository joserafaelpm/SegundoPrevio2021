package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import modelo.Team;
import util.Conexion;

public class TeamDaoPostgreSQL implements TeamDao {

	private Conexion conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, email, pais) VALUES(?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id= ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE usuario SET nombre=?, email=?, pais=? WHERE id=?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuario WHERE id =?;";
	private static final String SELECT_ALL_USUARIOS = "SELECT * FROM usuario;";
	
	public TeamDaoPostgreSQL() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insertTeam(Team team) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, team.getName());
			preparedStatement.setString(2, team.getCountry());
			conexion.execute();
		} catch (SQLException e) {

		}
	}

	public void deleteTeam(String id) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setString(1, id);
			conexion.execute();
		} catch (SQLException e) {

		}
	}
	
	public void updateTeam(Team team) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, team.getName());
			preparedStatement.setString(2, team.getCountry());
			preparedStatement.setString(3, team.getId());
			conexion.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Team> selectAllTeam(){
		List<Team> teams = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_USUARIOS);
			ResultSet rs = conexion.query();
			while(rs.next()) {
				String id = rs.getString("id");
				System.out.println(id);
				String name = rs.getString("name");
				String country = rs.getString("country");
				teams.add(new Team(id, name, country));
			}
			
		}catch(SQLException e){
			
		}
		return teams;
	}
	
	
	public Team selectTeam(String id){
		Team team = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
			preparedStatement.setString(1, id);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String country = rs.getString("country");
				team = new Team(id, name, country);
			}
			
		}catch(SQLException e){
			
		}
		return team;
	}

}
