package my.all.exceptions;

@SuppressWarnings("javadoc")
public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public UserException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
	}
	
}
