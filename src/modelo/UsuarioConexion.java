package modelo;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioConexion {

	public Usuario autenticar(String usuario, String password) {
		String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return mapear(rs);
			}
		} catch (SQLException e) {
			System.err.println("Error al autenticar: " + e.getMessage());
		}
		return null;
	}

	public List<Usuario> obtenerTodos() {
		List<Usuario> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios ORDER BY nombre";
		try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next())
				lista.add(mapear(rs));
		} catch (SQLException e) {
			System.err.println("Error al obtener usuarios: " + e.getMessage());
		}
		return lista;
	}

	public boolean insertar(Usuario u) {
		String sql = "INSERT INTO usuarios(nombre,apellido,usuario,password,rol) VALUES(?,?,?,?,?)";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setString(3, u.getUsuario());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getRol());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar usuario: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizar(Usuario u) {
		String sql = "UPDATE usuarios SET nombre=?,apellido=?,usuario=?,password=?,rol=? WHERE id_usuario=?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setString(3, u.getUsuario());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getRol());
			ps.setInt(6, u.getIdUsuario());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar usuario: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminar(int idUsuario) {
		String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar usuario: " + e.getMessage());
			return false;
		}
	}

	private Usuario mapear(ResultSet rs) throws SQLException {
		return new Usuario(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("apellido"),
				rs.getString("usuario"), rs.getString("password"), rs.getString("rol"));
	}
}
