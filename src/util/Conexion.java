package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Conexion {

	private Connection con = null;
	private static Conexion db;
	private PreparedStatement preparedStatement;

	private static final String url = "jdbc:postgresql://queenie.db.elephantsql.com/";
	private static final String dbName = "mnjgxshj";
	private static final String driver = "org.postgresql.Driver";
	private static final String userName = "mnjgxshj";
	private static final String password = "Uzjqo00sxV0W9OzPEB1q3wpoVvGMbbUV";

	public Conexion() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection) DriverManager.getConnection(url + dbName, userName, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return this.con;
	}
	
	public void cerrarConexion(){
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//patron singleton
	public static Conexion getConexion(){
		if(db == null){
			db = new Conexion();
		}
		return db;
	}
	
	public ResultSet query() throws SQLException{
        ResultSet res = this.preparedStatement.executeQuery();
        return res;
    }
	
	public int execute() throws SQLException {
        int result = this.preparedStatement.executeUpdate();
        return result;
 
    }
	
	public PreparedStatement setPreparedStatement(String sql)  throws SQLException{
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
}