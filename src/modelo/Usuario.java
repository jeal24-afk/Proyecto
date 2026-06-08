package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

	private int idUsuario;
	private String nombre;
	private String apellido;
	private String usuario;
	private String password;
	private String rol;

	public Usuario() {
	}

	public Usuario(int idUsuario, String nombre, String apellido, String usuario, String password, String rol) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int id) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String toString() {
		return nombre + " " + apellido + " (" + rol + ")";
	}

}