package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import modelo.Usuario;

public class UserRepository {

	private final String FILE = "."
			+ File.separator 
			+ "data"
			+ File.separator
			+ "users.json";
	
	private final ObjectMapper mapper = 
			new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	public void save(Usuario user) throws IOException {
		
		List<Usuario> users = getUsers();
		users.add(user);
		updateAll(users);
		
	}
	
	public List<Usuario> getUsers() throws IOException {
		
		File file = new File(FILE);
		
		file.getParentFile().mkdirs();
		
		if(!file.exists() || file.length() == 0) {
			return new ArrayList<>();
		}
		
		return mapper.readValue(
			file, 
			new TypeReference<List<Usuario>>() {}
		);
				
	}
	
	public void updateAll(List<Usuario> users) throws IOException {
		File file = new File(FILE);
		file.getParentFile().mkdir();
		
	    mapper.writeValue(file, users);
	}
	
	public void delete(int index) throws IOException {
		List<Usuario> users = getUsers();
		users.remove(index);
		updateAll(users);
	}
	
	public void update(int index, Usuario updatedUser) throws IOException {
		List<Usuario> users = getUsers();
		users.set(index, updatedUser);
		updateAll(users);
	}
	
			
}
