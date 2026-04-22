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

        if (!email.equals("jeal_24@uabcs.mx")) {
            throw new InvalidUserException("Usuario incorrecto");
        }

        if (!password.equals("1234")) {
            throw new InvalidPasswordException("Contraseña incorrecta");
        }

        return true;
    }

    public boolean camposValidos() {
        return validarEmail().isEmpty() && validarPassword().isEmpty();
    }
}
