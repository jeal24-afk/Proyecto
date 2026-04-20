package views;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import components.RoundButton;
import components.TextPrompt;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import lib.SpringUtilities;


public class LoginView extends JPanel{
	
	LoginWindow window;
	Font font;
	JPanel fondo;
	JPanel franjaSuperior;
	JPanel franjaInferior;
	JPanel panelCentro;
	JLabel lblNombre;
	JPanel panelFormulario ;
	JLabel lblUsuario;
	JTextField txtUsuario;
	JLabel lblPassword;
	JPasswordField txtPassword ;
	JButton btnLogin;
	JButton btnRegister;
	JLabel lblRegister;
	JLabel lblPasswordRequerido;
	JLabel lblUsuarioRequerido;
	
	public LoginView(LoginWindow window) {
		this.window = window;
		font = new Font("Arial", Font.PLAIN, 14);
		setLayout(new BorderLayout());
		
		inicializarComponentes();
	}
	
	
	public String getEmail() {
		return txtUsuario.getText();
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}
	
	public LoginWindow getWindow() {
		return window;
	}
	public JLabel getLblNombre() {
		return lblNombre;
	}
	public JLabel getLblUsuario() {
		return lblUsuario;
	}
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}
	public JLabel getLblPassword() {
		return lblPassword;
	}
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}
	public JLabel getLblRegister() {
		return lblRegister;
	}
	public JLabel getLblPasswordRequerido() {
		return lblPasswordRequerido;
	}

	public void setLblPasswordRequerido(JLabel lblPasswordRequerido) {
		this.lblPasswordRequerido = lblPasswordRequerido;
	}

	public JLabel getLblUsuarioRequerido() {
		return lblUsuarioRequerido;
	}

	public void setLblUsuarioRequerido(JLabel lblUsuarioRequerido) {
		this.lblUsuarioRequerido = lblUsuarioRequerido;
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
	
	public JButton getBtnRegister() {
	    return btnRegister;
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
		panelCentro.setPreferredSize(new Dimension(300, 450));
		panelCentro.setBackground(Color.RED);
		panelCentro.setLayout(new BorderLayout());

		fondo.add(panelCentro);

		crearLogo();

		
		JPanel formPanel = new JPanel();
		formPanel.setOpaque(false);
		formPanel.setLayout(new SpringLayout());
		formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));

		
		JLabel lblEmail = new JLabel("Usuario:");
		lblEmail.setFont(font);
		formPanel.add(lblEmail);

		txtUsuario = new JTextField();
		txtUsuario.setPreferredSize(new Dimension(140, 25));
		txtUsuario.setMaximumSize(new Dimension(140, 25));
		formPanel.add(txtUsuario);

		formPanel.add(new JLabel());
		
		lblRegister = new JLabel("Registrarse");
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblUsuarioRequerido = new JLabel("");
		lblUsuarioRequerido.setForeground(Color.WHITE);
		formPanel.add(lblUsuarioRequerido);

		JLabel lblPasswordLabel = new JLabel("Contraseña:");
		lblPasswordLabel.setFont(font);
		formPanel.add(lblPasswordLabel);

		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(140, 25));
		txtPassword.setMaximumSize(new Dimension(140, 25));
		formPanel.add(txtPassword);

		formPanel.add(new JLabel());

		lblPasswordRequerido = new JLabel("");
		lblPasswordRequerido.setForeground(Color.WHITE);
		formPanel.add(lblPasswordRequerido);

		SpringUtilities.makeCompactGrid(formPanel, 4, 2, 5, 5, 10, 10);

		panelCentro.add(formPanel, BorderLayout.CENTER);

		crearBotones();
	}
	
	private void crearBotones() {

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.setOpaque(false);

		btnLogin = new JButton("Login");
		buttonsPanel.add(btnLogin);

		btnRegister = new JButton("Registro");
		buttonsPanel.add(btnRegister);
		
		panelCentro.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void crearLogo() {
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelLogo.setOpaque(false);

		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(cargarIcono("../img/icono.png", 80, 80));

		panelLogo.add(lblLogo);
		panelCentro.add(panelLogo, BorderLayout.NORTH);
	}
	
	private ImageIcon cargarIcono(String ruta, int w, int h) {

		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
		
		return null;
	}
	

}

