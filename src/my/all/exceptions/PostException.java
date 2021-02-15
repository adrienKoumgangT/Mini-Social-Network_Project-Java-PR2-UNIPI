package my.all.exceptions;

@SuppressWarnings("javadoc")
public class PostException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PostException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public PostException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public PostException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause
	 */
	public PostException(Throwable cause) {
		super(cause);
	}
	

}
