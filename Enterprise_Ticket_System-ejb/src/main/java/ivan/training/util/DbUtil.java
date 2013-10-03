package ivan.training.util;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DbUtil {
	
	@PersistenceContext(unitName = "Enterprise-Ticket_System-ejb")
	EntityManager em;

}
