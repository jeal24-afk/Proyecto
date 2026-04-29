package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
	
    private String nombre;
    private String email;
    private String password;
    private String descripcion;
	
    
    public User(String nombre, String email, String descripcion) {
        this.nombre = nombre;
        this.email = email;
        this.descripcion = descripcion;
    }	
	
    public String toCsv() {
        return nombre + "," + email + "," + descripcion;
    }



    public static User fromCsv(String linea) {
        String[] datos = linea.split(",");
        return new User(datos[0], datos[1], datos[2]);
    }
    
    public String getNombre() {
        return nombre;
    }
    public String getEmail() { 
    	return email;   	
    }
    public String getDescripcion() { 
    	return descripcion; 
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
	
	
}