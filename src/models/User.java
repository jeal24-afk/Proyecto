package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String description;
	private String password;
	
	public User() {
    }
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(int id,String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String description) {
		this.name = name;
		this.email = email;
		this.description = description;

	}
	
	public User(int id,String name, String email, String description) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPassword() {
		return password;
	}

	public String toString() {
		return "Nombre: " + name +
		           "\nEmail: " + email +
		           "\nDescripción: " + description +
		           "\nLenguajes:\n" ;
	}
	
	public String toCsv() {
		return name + "," +
		           email + "," +
		           description ;
	}
	
}