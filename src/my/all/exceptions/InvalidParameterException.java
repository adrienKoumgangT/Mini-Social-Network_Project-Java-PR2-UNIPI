package my.all.exceptions;

@SuppressWarnings("javadoc")
public class InvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public InvalidParameterException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public InvalidParameterException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause
	 */
	public InvalidParameterException(Throwable cause) {
		super(cause);
	}

}
