package command;

public class CommandConstants {
	// commands received from clients or sent clients
	public static final String CMD_LOG_IN = "LogInCommand";
	public static final String CMD_LOG_OUT = "LogOutCommand";
	public static final String CMD_REGISTER = "RegisterCommand";
	
	// commands sent to clients
	public static final String CMD_REGISTER_RESULT = "RegisterResultCommand";
	public static final String CMD_LOGIN_RESULT = "LogInResultCommand";
	public static final String CMD_LOGOUT_RESULT = "LogOutResultCommand";
	
	// attribute names
	public static final String ATTR_USER_NAME = "UserName";
	public static final String ATTR_PASSWORD = "Password";
	public static final String ATTR_IP = "IP";
	public static final String ATTR_SUCCESS = "Success";
	public static final String ATTR_USER_ID = "UserID";
	public static final String ATTR_REQUEST_ID = "RequestID";
	
	// element names
	public static final String ELE_USER = "User";
}
