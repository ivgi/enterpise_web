package ivan.training.dao;

import ivan.training.common.resources.FacesNotificationMessages;
import ivan.training.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@PersistenceContext(unitName = "Enterprise-Ticket_System-ejb")
	protected EntityManager em;
	
	@Resource 
    private UserTransaction utx;

	public User findById(long id) {
		return em.find(User.class, id);
	}

	public FacesMessage createUser(User newUser) {
		User user = getUserByUsername(newUser.getUsername());
		if (user == null) {
			logger.info("creating user " + newUser.getUsername());
			try {
				utx.begin();
				em.persist(newUser);
				utx.commit();
				return FacesNotificationMessages.getSuccessfuluserregistration();
			} catch (Exception ex) {
				logger.error("error perssisting user", ex);
				return FacesNotificationMessages.getOthererror();
			}
		} else {
			return FacesNotificationMessages.getUniqueconstraintviolation();
		}
	}

	public User getUserByUsername(String username) {
		try {
			User user = (User) em.createNamedQuery("User.findByUsername")
					.setParameter("username", username).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			if(logger.isDebugEnabled())
			logger.debug("getUserByUsername returned no result, user is null");
			return null;
		}

	}

}
