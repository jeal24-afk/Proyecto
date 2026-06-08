package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class LoginVista extends JFrame {

	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIngresar;
	private JLabel lblError;

	private static final Color COL_FONDO = new Color(128, 128, 128);
	private static final Color COL_PANEL = new Color(128, 128, 128);
	private static final Color COL_ACENTO = new Color(238, 34, 34);
	private static final Color COL_TEXTO = new Color(230, 230, 230);
	private static final Color COL_BORDE = new Color(255, 255, 255);
	private static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 28);
	private static final Font FUENTE_SUB = new Font("Arial", Font.PLAIN, 13);
	private static final Font FUENTE_CAMPO = new Font("Arial", Font.PLAIN, 14);
	private static final Font FUENTE_BTN = new Font("Arial", Font.BOLD, 15);

	public LoginVista() {
		setTitle("Tienda KONG");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(500, 650);
		setLocationRelativeTo(null);
		construirUI();
	}

	private void construirUI() {
		JPanel fondoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, COL_FONDO, 0, getHeight(), new Color(128, 128, 128));
				g2.setPaint(gp);
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		fondoPanel.setLayout(new GridBagLayout());
		setContentPane(fondoPanel);

		
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(COL_PANEL);
		tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COL_ACENTO, 2),
				BorderFactory.createEmptyBorder(35, 40, 35, 40)));
		tarjeta.setMaximumSize(new Dimension(340, 400));

		JLabel lblLogo = new JLabel(" TIENDA KONG", SwingConstants.CENTER);
		lblLogo.setFont(FUENTE_TITULO);
		lblLogo.setForeground(COL_ACENTO);
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblSub = new JLabel("Sistema Punto de Venta", SwingConstants.CENTER);
		lblSub.setFont(FUENTE_SUB);
		lblSub.setForeground(new Color(238, 34, 34));
		lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

		JSeparator sep = new JSeparator();
		sep.setForeground(COL_ACENTO);
		sep.setMaximumSize(new Dimension(260, 2));

		JLabel lblU = crearEtiqueta("Usuario");
		txtUsuario = crearCampo();

		JLabel lblP = crearEtiqueta("Contraseña");
		txtPassword = new JPasswordField();
		estilizarCampo(txtPassword);

		lblError = new JLabel(" ");
		lblError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblError.setForeground(new Color(220, 60, 60));
		lblError.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnIngresar = new JButton("INGRESAR") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (getModel().isPressed()) {
					g2.setColor(new Color(200, 130, 0));
				} else if (getModel().isRollover()) {
					g2.setColor(new Color(0, 204, 204));
				} else {
					g2.setColor(COL_ACENTO);
				}
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(Color.BLACK);
				g2.setFont(getFont());
				FontMetrics fm = g2.getFontMetrics();
				int x = (getWidth() - fm.stringWidth(getText())) / 2;
				int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
				g2.drawString(getText(), x, y);
			}
		};
		btnIngresar.setFont(FUENTE_BTN);
		btnIngresar.setPreferredSize(new Dimension(260, 44));
		btnIngresar.setMaximumSize(new Dimension(260, 44));
		btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnIngresar.setBorderPainted(false);
		btnIngresar.setFocusPainted(false);
		btnIngresar.setContentAreaFilled(false);
		btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		tarjeta.add(crearLogo());
		tarjeta.add(Box.createVerticalStrut(10));
		tarjeta.add(lblLogo);
		tarjeta.add(Box.createVerticalStrut(4));
		tarjeta.add(lblSub);
		tarjeta.add(Box.createVerticalStrut(12));
		tarjeta.add(sep);
		tarjeta.add(Box.createVerticalStrut(24));
		tarjeta.add(lblU);
		tarjeta.add(Box.createVerticalStrut(5));
		tarjeta.add(txtUsuario);
		tarjeta.add(Box.createVerticalStrut(16));
		tarjeta.add(lblP);
		tarjeta.add(Box.createVerticalStrut(5));
		tarjeta.add(txtPassword);
		tarjeta.add(Box.createVerticalStrut(6));
		tarjeta.add(lblError);
		tarjeta.add(Box.createVerticalStrut(20));
		tarjeta.add(btnIngresar);
		fondoPanel.add(tarjeta);
	}

	private JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(FUENTE_SUB);
		lbl.setForeground(new Color(238, 34, 34));
		lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		return lbl;
	}

	private JTextField crearCampo() {
		JTextField tf = new JTextField();
		estilizarCampo(tf);
		return tf;
	}

	private void estilizarCampo(JTextField tf) {
		tf.setFont(FUENTE_CAMPO);
		tf.setForeground(COL_TEXTO);
		tf.setBackground(new Color(96, 96, 96));
		tf.setCaretColor(COL_ACENTO);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COL_BORDE),
				BorderFactory.createEmptyBorder(8, 12, 8, 12)));
		tf.setMaximumSize(new Dimension(260, 40));
		tf.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	private ImageIcon cargarIcono(String ruta, int w, int h) {
		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			return new ImageIcon(icono.getScaledInstance(w, h, Image.SCALE_SMOOTH));
		} catch (Exception ex) {
			System.err.println("Error al cargar icono: " + ruta);
		}
		return null;
	}
	private Component crearLogo() {
		JPanel panelLogo = new JPanel();
		panelLogo.setOpaque(false);
		panelLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(cargarIcono("../assets/img/icono.png", 80, 80));

		panelLogo.add(lblLogo);
		return add(panelLogo);
	}
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void mostrarError(String msg) {
		lblError.setText(msg);
	}

	public void limpiarCampos() {
		txtUsuario.setText("");
		txtPassword.setText("");
		txtUsuario.requestFocus();
	}
}
