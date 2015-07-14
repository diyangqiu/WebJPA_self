import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Customer;
import cutomTools.DBUtil;

/**
 * Servlet implementation class getInvoiceInfo
 */
@WebServlet("/addCustomer")
public class addCustomer extends HttpServlet {

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public addCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			

			getServletContext().getRequestDispatcher("/CustomerList.jsp").forward(request, response);
		}

	public static void insert(model.Customer cust){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try{
			em.persist(cust);
			trans.commit();
		}
		catch (Exception e){
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
	}
}