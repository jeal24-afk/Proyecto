package views;

import modelo.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class AdminVista extends JFrame {

	private JTabbedPane tabbedPane;
	private JTable tablaProductos;
	private JButton btnNuevoProducto, btnEditarProducto, btnEliminarProducto, btnBuscarProducto;
	private JTable tablaProveedores;
	private JButton btnNuevoProveedor, btnEditarProveedor, btnEliminarProveedor;
	private JTable tablaUsuarios;
	private JButton btnNuevoUsuario, btnEditarUsuario, btnEliminarUsuario;
	private JTable tablaCategorias;
	private JButton btnNuevaCategoria, btnEliminarCategoria;
	private JButton btnCerrarSesion;

	private static final Color COL_FONDO = new Color(128, 128, 128);
	private static final Color COL_PANEL = new Color(128, 128, 128);
	private static final Color COL_ACENTO = new Color(238, 34, 34);
	private static final Color COL_VERDE = new Color(40, 190, 100);
	private static final Color COL_ROJO = new Color(210, 60, 60);
	private static final Color COL_AZUL = new Color(50, 130, 220);
	private static final Color COL_TEXTO = new Color(220, 230, 240);
	private static final Color COL_FILA1 = new Color(96, 96, 96);
	private static final Color COL_FILA2 = new Color(96, 96, 96);
	private static final Font FUENTE_BTN = new Font("Arial", Font.BOLD, 13);

	public AdminVista(Usuario usuario) {
		setTitle("Tienda KONG – Administrador: " + usuario.getNombre() + " " + usuario.getApellido());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1300, 750);
		setLocationRelativeTo(null);
		construirUI();
	}

	private void construirUI() {
		JPanel principal = new JPanel(new BorderLayout(0, 0));
		principal.setBackground(COL_FONDO);
		setContentPane(principal);

		principal.add(crearBarra(), BorderLayout.NORTH);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(COL_FONDO);
		tabbedPane.setForeground(COL_TEXTO);
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
		UIManager.put("TabbedPane.selected", COL_PANEL);
		UIManager.put("TabbedPane.background", COL_FONDO);
		UIManager.put("TabbedPane.foreground", COL_TEXTO);

		tabbedPane.addTab("Productos", crearTabProductos());
		tabbedPane.addTab("Proveedores", crearTabProveedores());
		tabbedPane.addTab("Usuarios", crearTabUsuarios());
		tabbedPane.addTab("Categorías", crearTabCategorias());

		principal.add(tabbedPane, BorderLayout.CENTER);
	}

	private JPanel crearBarra() {
		JPanel barra = new JPanel(new BorderLayout());
		barra.setBackground(COL_PANEL);
		barra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COL_ACENTO),
				BorderFactory.createEmptyBorder(12, 16, 12, 16)));

		JLabel titulo = new JLabel("Panel Administrador – Tienda KONG");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		titulo.setForeground(COL_ACENTO);

		btnCerrarSesion = crearBoton("Cerrar Sesión", COL_ROJO);
		btnCerrarSesion.setPreferredSize(new Dimension(150, 36));

		barra.add(titulo, BorderLayout.WEST);
		barra.add(btnCerrarSesion, BorderLayout.EAST);
		return barra;
	}

	private JPanel crearTabProductos() {
		String[] cols = { "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Proveedor" };
		DefaultTableModel tm = new DefaultTableModel(cols, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tablaProductos = crearTabla(tm);

		btnNuevoProducto = crearBoton("Nuevo", COL_VERDE);
		btnEditarProducto = crearBoton("Editar", COL_AZUL);
		btnEliminarProducto = crearBoton("Eliminar", COL_ROJO);
		btnBuscarProducto = crearBoton("Buscar", COL_ACENTO);
		btnBuscarProducto.setForeground(Color.BLACK);

		return construirTabPanel(tablaProductos, btnNuevoProducto, btnEditarProducto, btnEliminarProducto,
				btnBuscarProducto);
	}

	private JPanel crearTabProveedores() {
		String[] cols = { "ID", "Nombre", "Teléfono", "Correo", "Dirección" };
		DefaultTableModel tm = new DefaultTableModel(cols, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tablaProveedores = crearTabla(tm);

		btnNuevoProveedor = crearBoton("Nuevo", COL_VERDE);
		btnEditarProveedor = crearBoton("Editar", COL_AZUL);
		btnEliminarProveedor = crearBoton("Eliminar", COL_ROJO);

		return construirTabPanel(tablaProveedores, btnNuevoProveedor, btnEditarProveedor, btnEliminarProveedor);
	}

	private JPanel crearTabUsuarios() {
		String[] cols = { "ID", "Nombre", "Apellido", "Usuario", "Rol" };
		DefaultTableModel tm = new DefaultTableModel(cols, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tablaUsuarios = crearTabla(tm);

		btnNuevoUsuario = crearBoton(" Nuevo", COL_VERDE);
		btnEditarUsuario = crearBoton("Editar", COL_AZUL);
		btnEliminarUsuario = crearBoton("Eliminar", COL_ROJO);

		return construirTabPanel(tablaUsuarios, btnNuevoUsuario, btnEditarUsuario, btnEliminarUsuario);
	}

	private JPanel crearTabCategorias() {
		String[] cols = { "ID", "Nombre" };
		DefaultTableModel tm = new DefaultTableModel(cols, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tablaCategorias = crearTabla(tm);

		btnNuevaCategoria = crearBoton("Nueva", COL_VERDE);
		btnEliminarCategoria = crearBoton("Eliminar", COL_ROJO);

		return construirTabPanel(tablaCategorias, btnNuevaCategoria, btnEliminarCategoria);
	}

	private JPanel construirTabPanel(JTable tabla, JButton... botones) {
		JPanel panel = new JPanel(new BorderLayout(6, 6));
		panel.setBackground(COL_FONDO);
		panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 80)));
		scroll.getViewport().setBackground(COL_FILA1);

		JPanel barBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		barBotones.setBackground(COL_FONDO);
		barBotones.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		for (JButton b : botones) {
			b.setPreferredSize(new Dimension(130, 38));
			barBotones.add(b);
		}

		panel.add(scroll, BorderLayout.CENTER);
		panel.add(barBotones, BorderLayout.SOUTH);
		return panel;
	}

	private JTable crearTabla(DefaultTableModel model) {
		JTable tabla = new JTable(model);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tabla.setForeground(COL_TEXTO);
		tabla.setBackground(COL_FILA1);
		tabla.setSelectionBackground(new Color(50, 90, 130));
		tabla.setSelectionForeground(Color.WHITE);
		tabla.setRowHeight(28);
		tabla.setShowGrid(false);
		tabla.setIntercellSpacing(new Dimension(0, 2));
		tabla.setFillsViewportHeight(true);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean focus, int row,
					int col) {
				super.getTableCellRendererComponent(t, v, sel, focus, row, col);
				setForeground(COL_TEXTO);
				if (!sel)
					setBackground(row % 2 == 0 ? COL_FILA1 : COL_FILA2);
				setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				return this;
			}
		});

		JTableHeader header = tabla.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(25, 45, 65));
		header.setForeground(COL_ACENTO);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COL_ACENTO));
		((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);

		return tabla;
	}

	private JButton crearBoton(String texto, Color fondo) {
		JButton btn = new JButton(texto) {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Color base = fondo;
				if (getModel().isPressed())
					base = fondo.darker().darker();
				else if (getModel().isRollover())
					base = fondo.brighter();
				g2.setColor(base);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
				g2.setColor(getForeground());
				g2.setFont(getFont());
				FontMetrics fm = g2.getFontMetrics();
				int x = (getWidth() - fm.stringWidth(getText())) / 2;
				int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
				g2.drawString(getText(), x, y);
			}
		};
		btn.setFont(FUENTE_BTN);
		btn.setForeground(Color.WHITE);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return btn;
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public JTable getTablaProveedores() {
		return tablaProveedores;
	}

	public JTable getTablaUsuarios() {
		return tablaUsuarios;
	}

	public JTable getTablaCategorias() {
		return tablaCategorias;
	}

	public JButton getBtnNuevoProducto() {
		return btnNuevoProducto;
	}

	public JButton getBtnEditarProducto() {
		return btnEditarProducto;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public JButton getBtnNuevoProveedor() {
		return btnNuevoProveedor;
	}

	public JButton getBtnEditarProveedor() {
		return btnEditarProveedor;
	}

	public JButton getBtnEliminarProveedor() {
		return btnEliminarProveedor;
	}

	public JButton getBtnNuevoUsuario() {
		return btnNuevoUsuario;
	}

	public JButton getBtnEditarUsuario() {
		return btnEditarUsuario;
	}

	public JButton getBtnEliminarUsuario() {
		return btnEliminarUsuario;
	}

	public JButton getBtnNuevaCategoria() {
		return btnNuevaCategoria;
	}

	public JButton getBtnEliminarCategoria() {
		return btnEliminarCategoria;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public void mostrarMensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Tienda KONG", JOptionPane.INFORMATION_MESSAGE);
	}
}
