package modelo;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaConexion {

	public List<Categoria> obtenerTodas() {
		List<Categoria> lista = new ArrayList<>();
		String sql = "SELECT * FROM categorias ORDER BY nombre";
		try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next())
				lista.add(new Categoria(rs.getInt("id_categoria"), rs.getString("nombre")));
		} catch (SQLException e) {
			System.err.println("Error al obtener categorías: " + e.getMessage());
		}
		return lista;
	}

	public boolean insertar(String nombre) {
		String sql = "INSERT INTO categorias(nombre) VALUES(?)";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, nombre);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar categoría: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminar(int idCategoria) {
		String sql = "DELETE FROM categorias WHERE id_categoria = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idCategoria);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar categoría: " + e.getMessage());
			return false;
		}
	}
}
