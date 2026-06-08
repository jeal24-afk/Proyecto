package modelo;

import config.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class VentaConexion {
	
	public boolean registrarVenta(int idUsuario, List<ItemCarrito> items, double total) {
		Connection con = DatabaseConnection.getConnection();
		try {
			con.setAutoCommit(false);

			int idVenta;
			String sqlVenta = "INSERT INTO ventas(total, id_usuario) VALUES(?,?)";
			try (PreparedStatement ps = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
				ps.setDouble(1, total);
				ps.setInt(2, idUsuario);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				idVenta = rs.getInt(1);
			}

			String sqlDetalle = "INSERT INTO detalle_ventas(id_venta,id_producto,cantidad,precio_unitario,subtotal) VALUES(?,?,?,?,?)";
			String sqlStock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";
			String sqlHistorial = "INSERT INTO historial_inventario(id_producto,movimiento,cantidad) VALUES(?,'SALIDA',?)";

			for (ItemCarrito item : items) {
				int idProd = item.getProducto().getIdProducto();
				int cantidad = item.getCantidad();
				double precio = item.getProducto().getPrecio();

				try (PreparedStatement ps = con.prepareStatement(sqlDetalle)) {
					ps.setInt(1, idVenta);
					ps.setInt(2, idProd);
					ps.setInt(3, cantidad);
					ps.setDouble(4, precio);
					ps.setDouble(5, item.getSubtotal());
					ps.executeUpdate();
				}

				try (PreparedStatement ps = con.prepareStatement(sqlStock)) {
					ps.setInt(1, cantidad);
					ps.setInt(2, idProd);
					ps.executeUpdate();
				}

				try (PreparedStatement ps = con.prepareStatement(sqlHistorial)) {
					ps.setInt(1, idProd);
					ps.setInt(2, cantidad);
					ps.executeUpdate();
				}
			}

			con.commit();
			return true;

		} catch (SQLException e) {
			System.err.println("Error al registrar venta: " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return false;
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
