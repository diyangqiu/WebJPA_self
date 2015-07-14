package cutomTools;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("WebJPA");
	public static EntityManagerFactory getEmFactory(){
		return emf;
	}
}
