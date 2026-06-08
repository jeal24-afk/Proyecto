package controllers;

import modelo.*;
import views.AdminVista;
import views.LoginVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminControlador {

	private AdminVista vista;
	private Usuario usuarioActual;
	private ProductoConexion productoDAO;
	private ProveedorConexion proveedorDAO;
	private CategoriaConexion categoriaDAO;
	private UsuarioConexion usuarioDAO;

	public AdminControlador(AdminVista vista, Usuario usuario) {
		this.vista = vista;
		this.usuarioActual = usuario;
		this.productoDAO = new ProductoConexion();
		this.proveedorDAO = new ProveedorConexion();
		this.categoriaDAO = new CategoriaConexion();
		this.usuarioDAO = new UsuarioConexion();

		cargarTodo();
		initListeners();
	}

	private void initListeners() {
		vista.getBtnNuevoProducto().addActionListener(e -> nuevoProducto());
		vista.getBtnEditarProducto().addActionListener(e -> editarProducto());
		vista.getBtnEliminarProducto().addActionListener(e -> eliminarProducto());
		vista.getBtnBuscarProducto().addActionListener(e -> buscarProducto());

		vista.getBtnNuevoProveedor().addActionListener(e -> nuevoProveedor());
		vista.getBtnEditarProveedor().addActionListener(e -> editarProveedor());
		vista.getBtnEliminarProveedor().addActionListener(e -> eliminarProveedor());

		vista.getBtnNuevoUsuario().addActionListener(e -> nuevoUsuario());
		vista.getBtnEditarUsuario().addActionListener(e -> editarUsuario());
		vista.getBtnEliminarUsuario().addActionListener(e -> eliminarUsuario());

		vista.getBtnNuevaCategoria().addActionListener(e -> nuevaCategoria());
		vista.getBtnEliminarCategoria().addActionListener(e -> eliminarCategoria());

		vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
	}

    private void cargarProductos() {
        List<Producto> lista = productoDAO.obtenerTodos();
        DefaultTableModel m = (DefaultTableModel) vista.getTablaProductos().getModel();
        m.setRowCount(0);
        for (Producto p : lista) {
            m.addRow(new Object[]{p.getIdProducto(), p.getNombre(), p.getDescripcion(),
                String.format("$%.2f", p.getPrecio()), p.getStock(),
                p.getNombreCategoria(), p.getNombreProveedor()});
        }
    }

    private void buscarProducto() {
        String txt = JOptionPane.showInputDialog(vista, "Nombre del producto:");
        if (txt == null) return;
        List<Producto> lista = productoDAO.buscarPorNombre(txt);
        DefaultTableModel m = (DefaultTableModel) vista.getTablaProductos().getModel();
        m.setRowCount(0);
        for (Producto p : lista) {
            m.addRow(new Object[]{p.getIdProducto(), p.getNombre(), p.getDescripcion(),
                String.format("$%.2f", p.getPrecio()), p.getStock(),
                p.getNombreCategoria(), p.getNombreProveedor()});
        }
    }

    private void nuevoProducto() {
        mostrarDialogoProducto(null);
    }

    private void editarProducto() {
        int fila = vista.getTablaProductos().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un producto.");
			return;
		}
        int id = (int) vista.getTablaProductos().getValueAt(fila, 0);
        List<Producto> todos = productoDAO.obtenerTodos();
        for (Producto p : todos) {
            if (p.getIdProducto() == id) { mostrarDialogoProducto(p); 
            return;
            }
        }
    }

    private void mostrarDialogoProducto(Producto existente) {
        List<Categoria>  cats  = categoriaDAO.obtenerTodas();
        List<Proveedor>  provs = proveedorDAO.obtenerTodos();

		if (cats.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Agrega categorías primero.");
			return;
		}
		if (provs.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Agrega proveedores primero.");
			return;
		}

		JTextField txtNombre = new JTextField(existente != null ? existente.getNombre() : "");
		JTextField txtDesc = new JTextField(existente != null ? existente.getDescripcion() : "");
		JTextField txtPrecio = new JTextField(existente != null ? String.valueOf(existente.getPrecio()) : "");
		JTextField txtStock = new JTextField(existente != null ? String.valueOf(existente.getStock()) : "");
		JComboBox<Categoria> cbCat = new JComboBox<>(cats.toArray(new Categoria[0]));
		JComboBox<Proveedor> cbProv = new JComboBox<>(provs.toArray(new Proveedor[0]));

		if (existente != null) {
			for (Categoria c : cats)
				if (c.getIdCategoria() == existente.getIdCategoria()) {
					cbCat.setSelectedItem(c);
					break;
				}
			for (Proveedor p : provs)
				if (p.getIdProveedor() == existente.getIdProveedor()) {
					cbProv.setSelectedItem(p);
					break;
				}
		}

		Object[] panel = { "Nombre:", txtNombre, "Descripción:", txtDesc, "Precio:", txtPrecio, "Stock:", txtStock,
				"Categoría:", cbCat, "Proveedor:", cbProv };

		int res = JOptionPane.showConfirmDialog(vista, panel, existente == null ? "Nuevo Producto" : "Editar Producto",
				JOptionPane.OK_CANCEL_OPTION);
		if (res != JOptionPane.OK_OPTION)
			return;

		try {
			Producto p = new Producto();
			p.setNombre(txtNombre.getText().trim());
			p.setDescripcion(txtDesc.getText().trim());
			p.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
			p.setStock(Integer.parseInt(txtStock.getText().trim()));
			p.setIdCategoria(((Categoria) cbCat.getSelectedItem()).getIdCategoria());
			p.setIdProveedor(((Proveedor) cbProv.getSelectedItem()).getIdProveedor());

			if (existente != null) {
				p.setIdProducto(existente.getIdProducto());
				productoDAO.actualizar(p);
			} else {
				productoDAO.insertar(p);
			}
			cargarProductos();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "Precio y stock deben ser números válidos.");
		}
	}

	private void eliminarProducto() {
		int fila = vista.getTablaProductos().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un producto.");
			return;
		}
		int id = (int) vista.getTablaProductos().getValueAt(fila, 0);
		int conf = JOptionPane.showConfirmDialog(vista, "¿Eliminar producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			productoDAO.eliminar(id);
			cargarProductos();
		}
	}

	private void cargarProveedores() {
		List<Proveedor> lista = proveedorDAO.obtenerTodos();
		DefaultTableModel m = (DefaultTableModel) vista.getTablaProveedores().getModel();
		m.setRowCount(0);
		for (Proveedor p : lista) {
			m.addRow(new Object[] { p.getIdProveedor(), p.getNombre(), p.getTelefono(), p.getCorreo(),
					p.getDireccion() });
		}
	}

	private void nuevoProveedor() {
		mostrarDialogoProveedor(null);
	}

	private void editarProveedor() {
		int fila = vista.getTablaProveedores().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un proveedor.");
			return;
		}
		int id = (int) vista.getTablaProveedores().getValueAt(fila, 0);
		for (Proveedor p : proveedorDAO.obtenerTodos()) {
			if (p.getIdProveedor() == id) {
				mostrarDialogoProveedor(p);
				return;
			}
		}
	}

	private void mostrarDialogoProveedor(Proveedor existente) {
		JTextField txtNombre = new JTextField(existente != null ? existente.getNombre() : "");
		JTextField txtTel = new JTextField(existente != null ? existente.getTelefono() : "");
		JTextField txtCorreo = new JTextField(existente != null ? existente.getCorreo() : "");
		JTextField txtDir = new JTextField(existente != null ? existente.getDireccion() : "");

		Object[] panel = { "Nombre:", txtNombre, "Teléfono:", txtTel, "Correo:", txtCorreo, "Dirección:", txtDir };
		int res = JOptionPane.showConfirmDialog(vista, panel,
				existente == null ? "Nuevo Proveedor" : "Editar Proveedor", JOptionPane.OK_CANCEL_OPTION);
		if (res != JOptionPane.OK_OPTION)
			return;

		Proveedor p = new Proveedor();
		p.setNombre(txtNombre.getText().trim());
		p.setTelefono(txtTel.getText().trim());
		p.setCorreo(txtCorreo.getText().trim());
		p.setDireccion(txtDir.getText().trim());

		if (existente != null) {
			p.setIdProveedor(existente.getIdProveedor());
			proveedorDAO.actualizar(p);
		} else {
			proveedorDAO.insertar(p);
		}
		cargarProveedores();
	}

	private void eliminarProveedor() {
		int fila = vista.getTablaProveedores().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un proveedor.");
			return;
		}
		int id = (int) vista.getTablaProveedores().getValueAt(fila, 0);
		int conf = JOptionPane.showConfirmDialog(vista, "¿Eliminar proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			proveedorDAO.eliminar(id);
			cargarProveedores();
		}
	}

	private void cargarUsuarios() {
		List<Usuario> lista = usuarioDAO.obtenerTodos();
		DefaultTableModel m = (DefaultTableModel) vista.getTablaUsuarios().getModel();
		m.setRowCount(0);
		for (Usuario u : lista) {
			m.addRow(new Object[] { u.getIdUsuario(), u.getNombre(), u.getApellido(), u.getUsuario(), u.getRol() });
		}
	}

	private void nuevoUsuario() {
		mostrarDialogoUsuario(null);
	}

	private void editarUsuario() {
		int fila = vista.getTablaUsuarios().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un usuario.");
			return;
		}
		int id = (int) vista.getTablaUsuarios().getValueAt(fila, 0);
		for (Usuario u : usuarioDAO.obtenerTodos()) {
			if (u.getIdUsuario() == id) {
				mostrarDialogoUsuario(u);
				return;
			}
		}
	}

	private void mostrarDialogoUsuario(Usuario existente) {
		JTextField txtNombre = new JTextField(existente != null ? existente.getNombre() : "");
		JTextField txtApellido = new JTextField(existente != null ? existente.getApellido() : "");
		JTextField txtUsuario = new JTextField(existente != null ? existente.getUsuario() : "");
		JPasswordField txtPass = new JPasswordField(existente != null ? existente.getPassword() : "");
		JComboBox<String> cbRol = new JComboBox<>(new String[] { "Administrador", "Cajero" });
		if (existente != null)
			cbRol.setSelectedItem(existente.getRol());

		Object[] panel = { "Nombre:", txtNombre, "Apellido:", txtApellido, "Usuario:", txtUsuario, "Contraseña:",
				txtPass, "Rol:", cbRol };
		int res = JOptionPane.showConfirmDialog(vista, panel, existente == null ? "Nuevo Usuario" : "Editar Usuario",
				JOptionPane.OK_CANCEL_OPTION);
		if (res != JOptionPane.OK_OPTION)
			return;

		Usuario u = new Usuario();
		u.setNombre(txtNombre.getText().trim());
		u.setApellido(txtApellido.getText().trim());
		u.setUsuario(txtUsuario.getText().trim());
		u.setPassword(new String(txtPass.getPassword()).trim());
		u.setRol((String) cbRol.getSelectedItem());

		if (existente != null) {
			u.setIdUsuario(existente.getIdUsuario());
			usuarioDAO.actualizar(u);
		} else {
			usuarioDAO.insertar(u);
		}
		cargarUsuarios();
	}

	private void eliminarUsuario() {
		int fila = vista.getTablaUsuarios().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona un usuario.");
			return;
		}
		int id = (int) vista.getTablaUsuarios().getValueAt(fila, 0);
		if (id == usuarioActual.getIdUsuario()) {
			JOptionPane.showMessageDialog(vista, "No puedes eliminarte a ti mismo.");
			return;
		}
		int conf = JOptionPane.showConfirmDialog(vista, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			usuarioDAO.eliminar(id);
			cargarUsuarios();
		}
	}

	private void cargarCategorias() {
		List<Categoria> lista = categoriaDAO.obtenerTodas();
		DefaultTableModel m = (DefaultTableModel) vista.getTablaCategorias().getModel();
		m.setRowCount(0);
		for (Categoria c : lista)
			m.addRow(new Object[] { c.getIdCategoria(), c.getNombre() });
	}

	private void nuevaCategoria() {
		String nombre = JOptionPane.showInputDialog(vista, "Nombre de la categoría:");
		if (nombre == null || nombre.trim().isEmpty())
			return;
		categoriaDAO.insertar(nombre.trim());
		cargarCategorias();
	}

	private void eliminarCategoria() {
		int fila = vista.getTablaCategorias().getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(vista, "Selecciona una categoría.");
			return;
		}
		int id = (int) vista.getTablaCategorias().getValueAt(fila, 0);
		int conf = JOptionPane.showConfirmDialog(vista, "¿Eliminar categoría?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			categoriaDAO.eliminar(id);
			cargarCategorias();
		}
	}

	private void cargarTodo() {
		cargarProductos();
		cargarProveedores();
		cargarUsuarios();
		cargarCategorias();
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
