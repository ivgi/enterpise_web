package ivan.training.beans;

import ivan.training.dao.UserDao;
import ivan.training.model.User;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 7629201579252680093L;
	
	@Inject
	User user;
	@EJB
	UserDao userDao;
	String errorMessage;
	
	public RegisterBean(){}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void register(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = userDao.createUser(user);
		context.addMessage(null, message); 
	}

}
