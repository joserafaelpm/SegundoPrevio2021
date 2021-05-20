package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Country;

public interface CountryDao {
	public void insertCountry(Country contry) throws SQLException;
	public Country selectCountry(String id);
	public List < Country > selectAllCountry();
	public void deleteCountry(String id) throws SQLException;
	public void updateCountry(Country contry) throws SQLException;
}
