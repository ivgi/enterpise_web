package ivan.training.dao.test;

import ivan.training.common.resources.FacesNotificationMessages;
import ivan.training.dao.UserDao;
import ivan.training.model.User;

import javax.ejb.EJB;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoTest extends TestCase {

	@EJB
	UserDao userDao;
	User user;
	
	// TODO add messages DO NOT FORGET GIT!!!!
	
	@BeforeClass
	public void init(){
		User user = new User();
		user.setUsername("ivan");
		user.setPassword("alabala");
		this.user = user;
	}
	

	@Test
	public void testCreateAndGetUser() {
		assertEquals(FacesNotificationMessages.getSuccessfuluserregistration().getSummary(),userDao.createUser(user).getSummary());
		assertEquals(user.getUsername(), userDao.getUserByUsername(user.getUsername()).getUsername());
		assertEquals(user.getPassword(), userDao.getUserByUsername(user.getUsername()).getPassword());
	}
	
	@Test
	public void testUniqueConstraintViolation(){
		userDao.createUser(user);
		assertEquals(FacesNotificationMessages.getUniqueconstraintviolation().getSummary(),userDao.createUser(user).getSummary());
	}

}
