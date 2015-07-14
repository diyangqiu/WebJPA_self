
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import cutomTools.DBUtil;


public class ModifyCustomerInfo {

	public static void main(String[] args) {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try
		{
			model.DemoCustomer cust = em.find(model.DemoCustomer.class, (long) 2);
			System.out.println(cust.getCreditLimit());
			trans.begin();
			BigDecimal bd = new BigDecimal(4200);
			cust.setCreditLimit(bd);
			em.merge(cust);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			em.close();
			System.out.println("cerrado!");
		}

	}
	
	public static void insert(model.DemoCustomer cust)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try
		{
			em.persist(cust);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
	}
	
	
	public static void update(model.DemoCustomer cust)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try
		{
			em.merge(cust);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
	}
	
	public static void delete(model.DemoCustomer cust)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try
		{
			em.remove(em.merge(cust));
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
	}
	
	public static model.DemoCustomer selectCustomer(String email)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c From DEMO_CUSTOMER c" + "Where c.email = :email";
		TypedQuery<model.DemoCustomer> q = em.createQuery(qString, model.DemoCustomer.class);
		q.setParameter("email", email);
		
		try
		{
			model.DemoCustomer user = q.getSingleResult();
			return user;
		}
		catch(NoResultException e)
		{
			return null;
		}
		finally
		{
			em.close();
		}
	}
	
	public static boolean emailExists(String email)
	{
		model.DemoCustomer u = selectCustomer(email);
		return u != null;
	}
	
	public static List<model.DemoCustomer> selectCustomer()
	{
		return null;
	}
}