package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Team;

public interface TeamDao {
	public void insertTeam(Team team) throws SQLException;
	public Team selectTeam(String id);
	public List < Team > selectAllTeam();
	public void deleteTeam(String id) throws SQLException;
	public void updateTeam(Team team) throws SQLException;
}
