package models;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;

public class LoginModel {

    private String email;
    private String password;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String validarEmail() {
        if (email.trim().isEmpty()) return "Requerido";
        return "";
    }

    public String validarPassword() {
        if (password.trim().isEmpty()) return "Requerido";
        return "";
    }

    public boolean validarCredenciales() throws InvalidUserException, InvalidPasswordException {
    	
        if (email.equals("")) {
            throw new InvalidUserException("Usuario incorrecto");
        }

        if (password.equals("")) {
            throw new InvalidPasswordException("Contraseña incorrecta");
        }

        return true;
    }
		
    public boolean camposValidos() {
        return validarEmail().isEmpty() && validarPassword().isEmpty();
    }
}
