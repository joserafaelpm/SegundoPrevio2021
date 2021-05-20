package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CountryDao;
import dao.CountryDaoPostgreSQL;
import modelo.Country;

/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CountryDao countryDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.countryDao = new CountryDaoPostgreSQL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarCountry(request, response);
				break;
			case "/delete":
				eliminarCountry(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				actualizarCountry(request, response);
				break;
			default:
				listCountry(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("country.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException ,ServletException {
		String name = request.getParameter("name");
		Country country = new Country(name);
		countryDao.insertCountry(country);;
		//request.getRequestDispatcher("usuariolist.jsp").forward(request, response);
		response.sendRedirect("list");
	}

	private void listCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Country> listCountrys = countryDao.selectAllCountry();
		System.out.println("cualquiercosa");
		request.setAttribute("listCountrys", listCountrys);
		RequestDispatcher dispatcher = request.getRequestDispatcher("countrylist.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		Country countryActual = countryDao.selectCountry(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		request.setAttribute("country", countryActual);
		dispatcher.forward(request, response);

	}
	
	private void editarCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Country country = new Country(id, name);
		countryDao.updateCountry(country);
		response.sendRedirect("list");
	}

	private void actualizarCountry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		System.out.println("HOLAACTUALIZA");
		Country country = new Country(id, nombre);
		countryDao.updateCountry(country);
		response.sendRedirect("list");
	}

	private void eliminarCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id = request.getParameter("id");
		countryDao.deleteCountry(id);
		response.sendRedirect("list");

	}
	

}
