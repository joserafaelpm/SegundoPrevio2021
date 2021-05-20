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
@WebServlet("/CountryServlet/*")
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
		String action = request.getParameter("action");
		switch (action) {
		case "eliminar":
			try {
				this.eliminar(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "mostrar":
			this.showForm(request, response);
			break;
			/*	case "/buscar":
			this.buscar(request, response);
			break;	 */
    	default:
			break;
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "buscar":
			this.buscar(request, response);
			break; 	
		case "registrar":
			try {
				this.registrar(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "actualizar":
			try {
				this.actualizar(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	//Metodos
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		Country country = new Country(name);
		countryDao.insertCountry(country);;
		request.getRequestDispatcher("countrylist.jsp").forward(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Country country = new Country(id);
		countryDao.deleteCountry(id);
		request.getRequestDispatcher("countrylist.jsp").forward(request, response);
		
		
	}

	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Country country = new Country(id, name);
		countryDao.updateCountry(country);
		request.getRequestDispatcher("countrylist.jsp").forward(request, response);
	}

	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Country countryActual = countryDao.selectCountry(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("country.jsp");
		request.setAttribute("country", countryActual);
		dispatcher.forward(request, response);
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("country.jsp");
		dispatcher.forward(request, response);
	}

}
