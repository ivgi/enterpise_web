package ivan.training.common.resources;

import javax.faces.application.FacesMessage;

public class FacesNotificationMessages {

	// TODO list messages from localization specific files

	private static final FacesMessage uniqueConstraintViolation = new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Username already exists",
			"Please choose a different name");

	private static final FacesMessage otherError = new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Error creating user!",
			"Unexpected error when creating your account");

	private static final FacesMessage successfulUserRegistration = new FacesMessage(
			FacesMessage.SEVERITY_INFO, "Succesful user registration", null);

	public static FacesMessage getSuccessfuluserregistration() {
		return successfulUserRegistration;
	}

	public static FacesMessage getUniqueconstraintviolation() {
		return uniqueConstraintViolation;
	}

	public static FacesMessage getOthererror() {
		return otherError;
	}

}
