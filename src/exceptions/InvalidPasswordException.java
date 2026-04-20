package exceptions;

public class InvalidPaswordException extends Exception {

	public InvalidPaswordException() {
		 super("Caracteres Invalidos");
	}
	
	public InvalidPaswordException(String message) {
		 super(message);
	}
}
