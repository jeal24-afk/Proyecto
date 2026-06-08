package modelo;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorConexion {

	public List<Proveedor> obtenerTodos() {
		List<Proveedor> lista = new ArrayList<>();
		String sql = "SELECT * FROM proveedores ORDER BY nombre";
		try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next())
				lista.add(mapear(rs));
		} catch (SQLException e) {
			System.err.println("Error al obtener proveedores: " + e.getMessage());
		}
		return lista;
	}

	public boolean insertar(Proveedor p) {
		String sql = "INSERT INTO proveedores(nombre,telefono,correo,direccion) VALUES(?,?,?,?)";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getTelefono());
			ps.setString(3, p.getCorreo());
			ps.setString(4, p.getDireccion());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar proveedor: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizar(Proveedor p) {
		String sql = "UPDATE proveedores SET nombre=?,telefono=?,correo=?,direccion=? WHERE id_proveedor=?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getTelefono());
			ps.setString(3, p.getCorreo());
			ps.setString(4, p.getDireccion());
			ps.setInt(5, p.getIdProveedor());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar proveedor: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminar(int idProveedor) {
		String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idProveedor);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar proveedor: " + e.getMessage());
			return false;
		}
	}

	private Proveedor mapear(ResultSet rs) throws SQLException {
		return new Proveedor(rs.getInt("id_proveedor"), rs.getString("nombre"), rs.getString("telefono"),
				rs.getString("correo"), rs.getString("direccion"));
	}
}
