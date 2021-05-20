package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Country;

public interface CountryDao {
	public void insertUser(Country contry) throws SQLException;
	public Country selectUser(int id);
	public List < Country > selectAllUsers();
	public boolean deleteUser(int id) throws SQLException;
	public boolean updateUser(Country contry) throws SQLException;
}
