package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import modelo.Country;
import util.Conexion;

public class CountryDaoPostgreSQL implements CountryDao {

	private Conexion conexion;

	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, email, pais) VALUES(?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id= ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE usuario SET nombre=?, email=?, pais=? WHERE id=?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuario WHERE id =?;";
	private static final String SELECT_ALL_USUARIOS = "SELECT * FROM usuario;";

	public CountryDaoPostgreSQL() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insertCountry(Country country) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, country.getName());
			conexion.execute();
		} catch (SQLException e) {

		}
	}

	public void deleteCountry(String id) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setString(1, id);
			conexion.execute();
		} catch (SQLException e) {

		}
	}
	
	public void updateCountry(Country country) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, country.getName());
			preparedStatement.setString(4, country.getId());
			conexion.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Country> selectAllCountry(){
		List<Country> countrys = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_USUARIOS);
			ResultSet rs = conexion.query();
			while(rs.next()) {
				String id = rs.getString("id");
				System.out.println(id);
				String name = rs.getString("name");
				countrys.add(new Country(id, name));
			}
			
		}catch(SQLException e){
			
		}
		return countrys;
	}
	
	
	public Country selectCountry(String id){
		Country country = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
			preparedStatement.setString(1, "id");
			ResultSet rs = conexion.query();
			while(rs.next()) {
				String name = rs.getString("name");
				country = new Country(id,name);
			}
			
		}catch(SQLException e){
			
		}
		return country;
	}


}
