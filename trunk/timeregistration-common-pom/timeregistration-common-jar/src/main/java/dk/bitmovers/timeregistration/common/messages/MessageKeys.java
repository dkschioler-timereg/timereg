package dk.bitmovers.timeregistration.common.messages;

public class MessageKeys {

	private MessageKeys() {

	}

	/* 
	 * $ctx.$subject.$type.$category.$message-key
	 * ie:
	 * provider.user.error.userpassword.count-not-equal-one
	 * gui.login.text.loginform.username-label
	*/
	public static final String PROVIDER_USER_ERROR_USER_PASSWORD_COUNT_NOT_EQUALS_ONE = "provider.user.error.userpassword.count-not-equals-one";
	public static final String PROVIDER_USER_ERROR_USER_ACTIVE_COUNT_NOT_EQUALS_ONE = "provider.user.error.user.active-count-not-equals-one";
	
	
	public static final String PROVIDER_GENERAL_ERROR_SHARED = "provider.general.error.shared";
}
