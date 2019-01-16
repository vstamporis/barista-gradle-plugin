package gr.aueb.android.barista.emulator.telnet.command;

/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
public class Auth implements TelnetCommand {

	public static final String AUTH = "auth";
	private String authToken;
	
	public Auth(String authToken) {
		super();
		this.authToken = authToken;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(AUTH)
		.append(" ")
		.append(authToken)
		.append("\r\n");
		return buffer.toString();
	}
	
}
