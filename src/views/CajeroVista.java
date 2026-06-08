package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modelo.Usuario;

import java.awt.*;

public class CajeroVista extends JFrame{

    private JTextField  txtBuscar;
    private JButton     btnBuscar, btnAgregar, btnEliminar, btnVaciar, btnCobrar, btnCerrarSesion;
    private JTable      tablaProductos, tablaCarrito;
    private JLabel      lblTotal;
    
    private static final Color COL_FONDO = new Color(128, 128, 128);
    private static final Color COL_PANEL  = new Color(128, 128, 128);
    private static final Color COL_ACENTO = new Color(238, 34, 34);
    private static final Color COL_VERDE = new Color(40, 190, 100);
    private static final Color COL_ROJO = new Color(210, 60, 60);
    private static final Color COL_TEXTO = new Color(230, 230, 230);
    private static final Color COL_TABLA_H = new Color(30, 50, 70);
    private static final Color COL_FILA1 = new Color(96, 96, 96);
    private static final Color COL_FILA2 = new Color(96, 96, 96);
    private static final Font  FUENTE_BTN = new Font("Arial", Font.BOLD, 13);
    private static final Font  FUENTE_TABLA = new Font("Arial", Font.PLAIN, 13);

    public CajeroVista(Usuario usuario) {
        setTitle(" Tienda KONG – Cajero: " + usuario.getNombre() + " " + usuario.getApellido());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setBackground(COL_FONDO);
        construirUI();
    }
    
    private void construirUI() {
        JPanel principal = new JPanel(new BorderLayout(8, 8));
        principal.setBackground(COL_FONDO);
        principal.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(principal);

        principal.add(crearBarra(), BorderLayout.NORTH);
        principal.add(crearCuerpo(), BorderLayout.CENTER);
    }
    
    private JPanel crearBarra() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(COL_PANEL);
        barra.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, COL_ACENTO),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JLabel titulo = new JLabel("Punto de Venta", SwingConstants.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(COL_ACENTO);

        btnCerrarSesion = crearBoton("Cerrar Sesión", COL_ROJO);
        btnCerrarSesion.setPreferredSize(new Dimension(140, 36));

        barra.add(titulo, BorderLayout.WEST);
        barra.add(btnCerrarSesion, BorderLayout.EAST);
        return barra;
    }
    
    private JSplitPane crearCuerpo() {
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            crearPanelProductos(), crearPanelCarrito());
        split.setDividerLocation(700);
        split.setBackground(COL_FONDO);
        split.setBorder(null);
        split.setDividerSize(6);
        return split;
    }
    
    private JPanel crearPanelProductos() {
        JPanel p = new JPanel(new BorderLayout(6, 6));
        p.setBackground(COL_FONDO);
    
        JPanel barBusq = new JPanel(new BorderLayout(6, 0));
        barBusq.setBackground(COL_FONDO);
        txtBuscar = new JTextField();
        txtBuscar.setFont(FUENTE_TABLA);
        txtBuscar.setBackground(new Color(96, 96, 96));
        txtBuscar.setForeground(COL_TEXTO);
        txtBuscar.setCaretColor(COL_ACENTO);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(128, 128, 128)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        txtBuscar.setToolTipText("Buscar producto...");
        btnBuscar = crearBoton("Buscar", COL_ACENTO);
        btnBuscar.setForeground(Color.BLACK);
        barBusq.add(txtBuscar, BorderLayout.CENTER);
        barBusq.add(btnBuscar, BorderLayout.EAST);
     
        String[] cols = {"ID", "Nombre", "Categoría", "Precio", "Stock"};
        DefaultTableModel tm = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaProductos = crearTabla(tm);
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(60);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        estilizarScroll(scroll);

        btnAgregar = crearBoton("Agregar al carrito", COL_VERDE);
        btnAgregar.setForeground(Color.WHITE);

        JLabel lblCat = new JLabel("Catálogo de Productos");
        lblCat.setFont(new Font("Arial", Font.BOLD, 14));
        lblCat.setForeground(COL_TEXTO);

        p.add(lblCat, BorderLayout.NORTH);
        p.add(barBusq, BorderLayout.BEFORE_FIRST_LINE);

        JPanel centro = new JPanel(new BorderLayout(4, 4));
        centro.setBackground(COL_FONDO);
        centro.add(barBusq, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(btnAgregar, BorderLayout.SOUTH);

        p.add(lblCat, BorderLayout.NORTH);
        p.add(centro, BorderLayout.CENTER);
        return p;
    }
    
    private JPanel crearPanelCarrito() {
        JPanel p = new JPanel(new BorderLayout(6, 6));
        p.setBackground(COL_FONDO);

        JLabel lblTit = new JLabel("Carrito");
        lblTit.setFont(new Font("Arial", Font.BOLD, 14));
        lblTit.setForeground(COL_TEXTO);

        String[] cols = {"Producto", "Cant.", "Precio Unit.", "Subtotal"};
        DefaultTableModel tm = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaCarrito = crearTabla(tm);

        JScrollPane scroll = new JScrollPane(tablaCarrito);
        estilizarScroll(scroll);

        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 22));
        lblTotal.setForeground(COL_ACENTO);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 8));

        btnEliminar = crearBoton("Eliminar", COL_ROJO);
        btnEliminar.setForeground(Color.WHITE);
        btnVaciar   = crearBoton("Vaciar", new Color(255, 255, 0));
        btnVaciar.setForeground(COL_ACENTO);
        btnCobrar   = crearBoton("COBRAR", COL_VERDE);
        btnCobrar.setForeground(Color.WHITE);
        btnCobrar.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel botones = new JPanel(new GridLayout(1, 3, 6, 0));
        botones.setBackground(COL_FONDO);
        botones.add(btnEliminar);
        botones.add(btnVaciar);
        botones.add(btnCobrar);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(COL_FONDO);
        sur.add(lblTotal, BorderLayout.NORTH);
        sur.add(botones, BorderLayout.SOUTH);

        p.add(lblTit, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        p.add(sur, BorderLayout.SOUTH);
        return p;
    }
    
    private JTable crearTabla(DefaultTableModel model) {
        JTable tabla = new JTable(model);
        tabla.setFont(FUENTE_TABLA);
        tabla.setForeground(COL_TEXTO);
        tabla.setBackground(COL_FILA1);
        tabla.setSelectionBackground(new Color(50, 90, 130));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setRowHeight(28);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 2));
        tabla.setFillsViewportHeight(true);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean focus, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, focus, row, col);
                setForeground(COL_TEXTO);
                if (!sel) setBackground(row % 2 == 0 ? COL_FILA1 : COL_FILA2);
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return this;
            }
        });

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBackground(COL_TABLA_H);
        header.setForeground(COL_ACENTO);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COL_ACENTO));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);

        return tabla;
    }
    
    private JButton crearBoton(String texto, Color fondo) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color base = fondo;
                if (getModel().isPressed())  base = fondo.darker().darker();
                else if (getModel().isRollover()) base = fondo.brighter();
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
        btn.setPreferredSize(new Dimension(100, 38));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void estilizarScroll(JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 80)));
        scroll.getViewport().setBackground(COL_FILA1);
        scroll.getVerticalScrollBar().setBackground(COL_PANEL);
    }
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Tienda KONG", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public JTextField getTxtBuscar() { 
    	return txtBuscar; 
    }
    public JButton getBtnBuscar() { 
    	return btnBuscar;
    }
    public JButton getBtnAgregar() { 
    	return btnAgregar; 
    }
    public JButton getBtnEliminar() { 
    	return btnEliminar; 
    }
    public JButton getBtnVaciar() { return 
    		btnVaciar; 
    }
    public JButton getBtnCobrar() { 
    	return btnCobrar; 
    }
    public JButton getBtnCerrarSesion() { 
    	return btnCerrarSesion; 
    }
    public JTable getTablaProductos() { 
    	return tablaProductos; 
    }
    public JTable getTablaCarrito() { 
    	return tablaCarrito; 
    }
    public JLabel getLblTotal() { 
    	return lblTotal; 
    }
    
}
