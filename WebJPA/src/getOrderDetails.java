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

import model.Order;
import cutomTools.DBUtil;

/**
 * Servlet implementation class getInvoiceInfo
 */
@WebServlet("/getOrderDetails")
public class getOrderDetails extends HttpServlet {

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public getOrderDetails() {
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
		
		String custId = request.getParameter("custId");
		System.out.println(custId);
		
		String tableInfo = "";
		tableInfo += "<tr><td>ORDER ID</td><td>ORDER DATE</td><td>SHIP AMOUNT</td></tr>";
		
		System.out.println(5);
	
		List<Order> od = selectOrder(custId);
		
		if (od != null){	
		
			try {
			
		
			
			
		
				for(Order order: od){
					tableInfo += "<tr><td>" + order.getOrderId()
						+ "</td><td>" + order.getOrderDate()
						+ "</td><td>" + order.getShipAmount() +"</td></tr>";
				}
					request.setAttribute("tableInfo", tableInfo);
			}
				catch (Exception e){

					request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
				}
	
			getServletContext().getRequestDispatcher("/OrderList.jsp").forward(request, response);
		}
		
		else{
			tableInfo += "<tr><td>" + "null"
					+ "</td><td>" + "null"
					+ "</td><td>" +"null" +"</td></tr>";
			}
				request.setAttribute("tableInfo", tableInfo);
				getServletContext().getRequestDispatcher("/OrderList.jsp").forward(request, response);
		}
			
	public static List<Order> selectOrder(String custId) {

	
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT o FROM Order o WHERE o.customer.customerId = :customerId";
		System.out.println(3);
		TypedQuery<Order> q = em
				.createQuery(qString, Order.class);
		
		q.setParameter("customerId", Long.parseLong(custId));
		
		List<Order> order = null;
		try {
			order = q.getResultList();
			if (order == null || order.isEmpty())
				order = null;
		} catch (Exception e) {
			System.out.println(2);
			System.out.println(e);
		} finally {
			em.close();
		}
		
		System.out.println(1);
		return order;
	}
}