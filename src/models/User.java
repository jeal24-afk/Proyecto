package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
	
    private String nombre;
    private String email;
    private String password;
	
    
    public User(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }	
	
    public String toCsv() {
        return nombre + "," + email + "," + password;
    }



    public static User fromCsv(String linea) {
        String[] datos = linea.split(",");
        return new User(datos[0], datos[1], datos[2]);
    }

    public String getEmail() { 
    	return email;   	
    }
    public String getPassword() { 
    	return password; 
    }

	
	
}