package models;

public class FormularioModel {

    private String nombre;
    private String email;
    private String descripcion;
    private boolean terminos;

    public FormularioModel(String nombre, String email, String descripcion, boolean terminos) {
        this.nombre = nombre;
        this.email = email;
        this.descripcion = descripcion;
        this.terminos = terminos;
    }

    public String validarNombre() {
        if (nombre.trim().isEmpty()) return "El nombre es obligatorio";
        if (nombre.trim().length() <= 3) return "Mínimo 4 caracteres";
        return "";
    }

    public String validarEmail() {
        if (email.trim().isEmpty()) return "El email es obligatorio";
        if (!email.contains("@")) return "Email inválido";
        return "";
    }

    public String validarDescripcion() {
        if (descripcion.trim().length() < 10) return "Descripción mínima 10 caracteres";
        return "";
    }

    public String validarTerminos() {
        if (!terminos) return "Debe aceptar los términos";
        return "";
    }

    public boolean esValido() {
        return validarNombre().isEmpty() &&
               validarEmail().isEmpty() &&
               validarDescripcion().isEmpty() &&
               validarTerminos().isEmpty();
    }
}
