package modelo;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoConexion {

	public List<Producto> obtenerTodos() {
		List<Producto> lista = new ArrayList<>();
		String sql = "SELECT p.*, c.nombre AS nom_cat, v.nombre AS nom_prov " + "FROM productos p "
				+ "JOIN categorias c ON p.id_categoria = c.id_categoria "
				+ "JOIN proveedores v ON p.id_proveedor = v.id_proveedor " + "ORDER BY p.nombre";
		try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next())
				lista.add(mapear(rs));
		} catch (SQLException e) {
			System.err.println("Error al obtener productos: " + e.getMessage());
		}
		return lista;
	}

	public List<Producto> buscarPorNombre(String nombre) {
		List<Producto> lista = new ArrayList<>();
		String sql = "SELECT p.*, c.nombre AS nom_cat, v.nombre AS nom_prov " + "FROM productos p "
				+ "JOIN categorias c ON p.id_categoria = c.id_categoria "
				+ "JOIN proveedores v ON p.id_proveedor = v.id_proveedor " + "WHERE p.nombre LIKE ? ORDER BY p.nombre";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, "%" + nombre + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				lista.add(mapear(rs));
		} catch (SQLException e) {
			System.err.println("Error al buscar productos: " + e.getMessage());
		}
		return lista;
	}

	public boolean insertar(Producto p) {
		String sql = "INSERT INTO productos(nombre,descripcion,precio,stock,id_categoria,id_proveedor) VALUES(?,?,?,?,?,?)";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getIdCategoria());
			ps.setInt(6, p.getIdProveedor());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar producto: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizar(Producto p) {
		String sql = "UPDATE productos SET nombre=?,descripcion=?,precio=?,stock=?,id_categoria=?,id_proveedor=? WHERE id_producto=?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getIdCategoria());
			ps.setInt(6, p.getIdProveedor());
			ps.setInt(7, p.getIdProducto());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar producto: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminar(int idProducto) {
		String sql = "DELETE FROM productos WHERE id_producto = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idProducto);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar producto: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizarStock(int idProducto, int nuevoStock) {
		String sql = "UPDATE productos SET stock = ? WHERE id_producto = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, nuevoStock);
			ps.setInt(2, idProducto);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar stock: " + e.getMessage());
			return false;
		}
	}

	private Producto mapear(ResultSet rs) throws SQLException {
		Producto p = new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("descripcion"),
				rs.getDouble("precio"), rs.getInt("stock"), rs.getInt("id_categoria"), rs.getInt("id_proveedor"));
		p.setNombreCategoria(rs.getString("nom_cat"));
		p.setNombreProveedor(rs.getString("nom_prov"));
		return p;
	}
}
