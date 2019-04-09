package gr.aueb.android.barista.core.model;


import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
public class Auth extends AbstractTelnetCommand {

	private String authToken;
	
	public Auth(String authToken) {
		super();
		this.authToken = authToken;
	}

	@Override
	public String getCommandString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(BaristaCommandPrefixes.AUTH)
				.append(" ")
				.append(authToken)
				.append("\r\n");
		return buffer.toString();
	}

}
