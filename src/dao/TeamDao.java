package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Team;

public interface TeamDao {
	public void insertUser(Team team) throws SQLException;
	public Team selectUser(int id);
	public List < Team > selectAllUsers();
	public boolean deleteUser(int id) throws SQLException;
	public boolean updateUser(Team team) throws SQLException;
}
