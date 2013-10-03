package ivan.training.beans.test;

import ivan.training.beans.RegisterBean;
import ivan.training.dao.UserDao;
import ivan.training.model.User;

import javax.ejb.EJB;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestRegisterBean extends TestCase {
	
	RegisterBean registerBean;
	@EJB UserDao userDao;
	@BeforeClass
	public void onLoad(){
		registerBean = new RegisterBean();
	}
	
	@Test
	public void testUserRegistration(){
		User user = new User();
		user.setUsername("petkan");
		user.setPassword("dragan");
		registerBean.setUser(user);
		registerBean.register();
		//check if the user is written in the database
		assertEquals(user.getUsername(), userDao.getUserByUsername(user.getUsername()).getUsername());
	}
	

}
