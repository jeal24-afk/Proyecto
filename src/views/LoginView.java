package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import components.TextPrompt;
import lib.SpringUtilities;

public class LoginView extends JPanel {

	private LoginWindow window;
	private Font font;
	private JPanel fondo, franjaSuperior, franjaInferior, panelCentro;

	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;
	private JLabel lblRegister;
	private JLabel lblUsuarioRequerido, lblPasswordRequerido;

	public LoginView(LoginWindow window) {
		this.window = window;
		this.font = new Font("Arial", Font.PLAIN, 14);
		setLayout(new BorderLayout());

		inicializarComponentes();
	}

	private void inicializarComponentes() {
		fondo = new JPanel(new GridBagLayout());
		fondo.setBackground(Color.WHITE);
		add(fondo, BorderLayout.CENTER);

		franjaSuperior = new JPanel();
		franjaSuperior.setBackground(Color.RED);
		franjaSuperior.setPreferredSize(new Dimension(0, 20));
		add(franjaSuperior, BorderLayout.NORTH);

		franjaInferior = new JPanel();
		franjaInferior.setBackground(Color.RED);
		franjaInferior.setPreferredSize(new Dimension(0, 20));
		add(franjaInferior, BorderLayout.SOUTH);

		panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(350, 480));
		panelCentro.setBackground(Color.RED);
		panelCentro.setLayout(new BorderLayout());
		fondo.add(panelCentro);

		crearLogo();
		crearFormulario();
		crearBotones();
	}

	private void crearLogo() {
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelLogo.setOpaque(false);
		panelLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(cargarIcono("../assets/img/icono.png", 80, 80));

		panelLogo.add(lblLogo);
		panelCentro.add(panelLogo, BorderLayout.NORTH);
	}

	private void crearFormulario() {
		JPanel formPanel = new JPanel();
		formPanel.setOpaque(false);
		formPanel.setLayout(new SpringLayout());
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

		JLabel lblEmail = new JLabel("Usuario:");
		lblEmail.setFont(font);
		lblEmail.setForeground(Color.WHITE);
		formPanel.add(lblEmail);

		txtUsuario = new JTextField();
		new TextPrompt("Ingresa tu usuario", txtUsuario);
		formPanel.add(txtUsuario);

		formPanel.add(new JLabel());

		lblUsuarioRequerido = new JLabel("");
		lblUsuarioRequerido.setFont(new Font("Arial", Font.BOLD, 10));
		lblUsuarioRequerido.setForeground(Color.YELLOW);
		formPanel.add(lblUsuarioRequerido);

		JLabel lblPasswordLabel = new JLabel("Contraseña:");
		lblPasswordLabel.setFont(font);
		lblPasswordLabel.setForeground(Color.WHITE);
		formPanel.add(lblPasswordLabel);

		txtPassword = new JPasswordField();
		new TextPrompt("Ingresa tu contraseña", txtPassword);
		formPanel.add(txtPassword);

		formPanel.add(new JLabel());

		lblPasswordRequerido = new JLabel("");
		lblPasswordRequerido.setFont(new Font("Arial", Font.BOLD, 10));
		lblPasswordRequerido.setForeground(Color.YELLOW);
		formPanel.add(lblPasswordRequerido);

		SpringUtilities.makeCompactGrid(formPanel, 4, 2, 5, 5, 10, 10);
		panelCentro.add(formPanel, BorderLayout.CENTER);
	}

	private void crearBotones() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.setOpaque(false);

		JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		registerPanel.setOpaque(false);
		lblRegister = new JLabel("¿No tienes cuenta? Regístrate aquí");
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerPanel.add(lblRegister);

		JPanel loginBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		loginBtnPanel.setOpaque(false);
		btnLogin = new JButton("Login");
		configurarBoton(btnLogin);
		loginBtnPanel.add(btnLogin);

		southPanel.add(registerPanel, BorderLayout.NORTH);
		southPanel.add(loginBtnPanel, BorderLayout.CENTER);

		panelCentro.add(southPanel, BorderLayout.SOUTH);
	}

	private void configurarBoton(JButton boton) {
		boton.setFont(font);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boton.setBackground(Color.BLACK);
				boton.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				boton.setBackground(null);
				boton.setForeground(Color.BLACK);
			}
		});
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
	
	public LoginWindow getWindow() {
		return window;
	}
	public JTextField getEmailField() {
		return txtUsuario;
	}

	public JPasswordField getPasswordField() {
		return txtPassword;
	}

	public JLabel getLblRegister() {
		return lblRegister;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void showEmailError(String message) {
		lblUsuarioRequerido.setText(message);
	}

	public void showPasswordError(String message) {
		lblPasswordRequerido.setText(message);
	}

	public void resetErrorMessages() {
		lblUsuarioRequerido.setText("");
		lblPasswordRequerido.setText("");
	}

	public String getEmail() {
		return txtUsuario.getText();
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}
}

