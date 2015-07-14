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
@WebServlet("/getCustomerList")
public class getCustomerList extends HttpServlet {

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public getCustomerList() {
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
		

		
		String tableInfo = "";
		tableInfo += "<tr><td>CUSTOMER ID</td><td>CUSTOMER NAME</td><td>Email Address</th></tr>";
		
		request.setAttribute("tableInfo", tableInfo);
		
	try {
			
		
			List<Customer> customers = selectAllCustomer();

			for (Customer cust : customers) {
				tableInfo += "<tr><td>" + cust.getCustomerId()
						+ "</td><td><a href='getOrderDetails?custId=" + cust.getCustomerId() + "'>"
						+ cust.getFirstName()
						+ " " + cust.getLastName()
						+ "</a></td><td>" + cust.getEmailAddress() + "</td></tr>";
			}
			request.setAttribute("tableInfo", tableInfo);
		} catch (Exception e){

			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
			getServletContext().getRequestDispatcher("/CustomerList.jsp").forward(request, response);
		}

	public static List<Customer> selectAllCustomer() {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c from Customer c";
		System.out.println(3);
		TypedQuery<Customer> q = em
				.createQuery(qString, Customer.class);
		System.out.println(6);
		List<Customer> Customer = null;
		try {
			Customer = q.getResultList();
			if (Customer == null || Customer.isEmpty())
				Customer = null;
			System.out.println(8);
		} catch (Exception e) {
			System.out.println(2);
			System.out.println(e);
		} finally {
			em.close();
		}
		
		System.out.println(1);
		return Customer;
	}
}