package controllers;

import modelo.*;
import views.CajeroVista;
import views.LoginVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CajeroControlador {

	private CajeroVista vista;
	private Usuario usuarioActual;
	private ProductoConexion productoDAO;
	private VentaConexion ventaDAO;
	private List<ItemCarrito> carrito;

	public CajeroControlador(CajeroVista vista, Usuario usuario) {
		this.vista = vista;
		this.usuarioActual = usuario;
		this.productoDAO = new ProductoConexion();
		this.ventaDAO = new VentaConexion();
		this.carrito = new ArrayList<>();

		cargarProductos();
		initListeners();
	}

	private void initListeners() {
		vista.getBtnBuscar().addActionListener(e -> buscarProductos());
		vista.getBtnAgregar().addActionListener(e -> agregarAlCarrito());
		vista.getBtnEliminar().addActionListener(e -> eliminarDelCarrito());
		vista.getBtnVaciar().addActionListener(e -> vaciarCarrito());
		vista.getBtnCobrar().addActionListener(e -> realizarVenta());
		vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
		vista.getTxtBuscar().addActionListener(e -> buscarProductos());
	}

	private void cargarProductos() {
		List<Producto> productos = productoDAO.obtenerTodos();
		actualizarTablaProductos(productos);
	}

	private void buscarProductos() {
		String texto = vista.getTxtBuscar().getText().trim();
		List<Producto> productos;
		if (texto.isEmpty()) {
			productos = productoDAO.obtenerTodos();
		} else {
			productos = productoDAO.buscarPorNombre(texto);
		}
		actualizarTablaProductos(productos);
	}

	private void actualizarTablaProductos(List<Producto> lista) {
		DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
		model.setRowCount(0);
		for (Producto p : lista) {
			model.addRow(new Object[] { p.getIdProducto(), p.getNombre(), p.getNombreCategoria(),
					String.format("$%.2f", p.getPrecio()), p.getStock() });
		}
	}

	private void agregarAlCarrito() {
		int fila = vista.getTablaProductos().getSelectedRow();
		if (fila < 0) {
			vista.mostrarMensaje("Selecciona un producto de la lista.");
			return;
		}

		int idProducto = (int) vista.getTablaProductos().getValueAt(fila, 0);
		String nombre = (String) vista.getTablaProductos().getValueAt(fila, 1);
		int stock = (int) vista.getTablaProductos().getValueAt(fila, 4);
		String precioStr = ((String) vista.getTablaProductos().getValueAt(fila, 3)).replace("$", "");
		double precio = Double.parseDouble(precioStr);

		if (stock <= 0) {
			vista.mostrarMensaje("Producto sin stock disponible.");
			return;
		}

		String cantStr = JOptionPane.showInputDialog(vista, "Cantidad de \"" + nombre + "\":", "1");
		if (cantStr == null)
			return;

		int cantidad;
		try {
			cantidad = Integer.parseInt(cantStr.trim());
			if (cantidad <= 0)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) {
			vista.mostrarMensaje("Cantidad inválida.");
			return;
		}

		if (cantidad > stock) {
			vista.mostrarMensaje("No hay suficiente stock. Disponible: " + stock);
			return;
		}

		for (ItemCarrito item : carrito) {
			if (item.getProducto().getIdProducto() == idProducto) {
				int total = item.getCantidad() + cantidad;
				if (total > stock) {
					vista.mostrarMensaje("Cantidad excede el stock disponible.");
					return;
				}
				item.setCantidad(total);
				actualizarTablaCarrito();
				return;
			}
		}

		Producto p = new Producto(idProducto, nombre, "", precio, stock, 0, 0);
		carrito.add(new ItemCarrito(p, cantidad));
		actualizarTablaCarrito();
	}

	private void eliminarDelCarrito() {
		int fila = vista.getTablaCarrito().getSelectedRow();
		if (fila < 0) {
			vista.mostrarMensaje("Selecciona un item del carrito.");
			return;
		}
		carrito.remove(fila);
		actualizarTablaCarrito();
	}

	private void vaciarCarrito() {
		carrito.clear();
		actualizarTablaCarrito();
	}

	private void actualizarTablaCarrito() {
		DefaultTableModel model = (DefaultTableModel) vista.getTablaCarrito().getModel();
		model.setRowCount(0);
		double total = 0;
		for (ItemCarrito item : carrito) {
			model.addRow(new Object[] { item.getProducto().getNombre(), item.getCantidad(),
					String.format("$%.2f", item.getProducto().getPrecio()),
					String.format("$%.2f", item.getSubtotal()) });
			total += item.getSubtotal();
		}
		vista.getLblTotal().setText(String.format("Total: $%.2f", total));
	}

	private void realizarVenta() {
		if (carrito.isEmpty()) {
			vista.mostrarMensaje("El carrito está vacío.");
			return;
		}

		double total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();

		int confirmar = JOptionPane.showConfirmDialog(vista, String.format("¿Confirmar venta por $%.2f?", total),
				"Confirmar Venta", JOptionPane.YES_NO_OPTION);

		if (confirmar != JOptionPane.YES_OPTION)
			return;

		boolean exito = ventaDAO.registrarVenta(usuarioActual.getIdUsuario(), carrito, total);

		if (exito) {
			vista.mostrarMensaje(String.format("Venta registrada por $%.2f", total));
			vaciarCarrito();
			cargarProductos();
		} else {
			vista.mostrarMensaje("Error al registrar la venta. Intenta nuevamente.");
		}
	}

	private void cerrarSesion() {
		int conf = JOptionPane.showConfirmDialog(vista, "¿Cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			vista.dispose();
			LoginVista login = new LoginVista();
			new LoginControlador(login);
			login.setVisible(true);
		}
	}
}
