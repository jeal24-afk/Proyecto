package exceptions;

public class InvalidEmailException extends Exception{

	public InvalidEmailException() {
		super("Caracteres Invalidos");
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
}
