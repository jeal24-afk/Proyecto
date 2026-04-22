package exceptions;

public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		 super("Caracteres Invalidos");
	}
	
	public InvalidPasswordException(String message) {
		 super(message);
	}
}
