package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import components.RoundButton;
import components.TextPrompt;

public class LoginView extends JPanel{
		
	public LoginView() {
		crearLogo();
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {

	    setLayout(new BorderLayout());

	    JPanel franjaSuperior = new JPanel();
	    franjaSuperior.setBackground(Color.RED);
	    franjaSuperior.setPreferredSize(new Dimension(0, 25));
	    add(franjaSuperior, BorderLayout.NORTH);

	    JPanel franjaInferior = new JPanel();
	    franjaInferior.setBackground(Color.RED);
	    franjaInferior.setPreferredSize(new Dimension(0, 25));
	    add(franjaInferior, BorderLayout.SOUTH);

	    JPanel panelCentro = new JPanel(new GridBagLayout());
	    
	    JLabel lblNombre = new JLabel("Tienda Kong");
	    lblNombre.setFont(new Font("Arial", Font.BOLD, 22));
	    lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
	    panelFormulario.setPreferredSize(new Dimension(300, 120));

	    JLabel lblUsuario = new JLabel("Usuario:");
	    JTextField txtUsuario = new JTextField();

	    JLabel lblPassword = new JLabel("Contraseña:");
	    JPasswordField txtPassword = new JPasswordField();

	    JButton btnLogin = new JButton("Ingresar");
	    JButton btnRegistro = new JButton("Registrarse");
	    
	    panelFormulario.add(lblNombre);
	    panelFormulario.add(new JLabel(""));
	    panelFormulario.add(lblUsuario);
	    panelFormulario.add(txtUsuario);
	    panelFormulario.add(lblPassword);
	    panelFormulario.add(txtPassword);
	    panelFormulario.add(btnLogin);
	    panelFormulario.add(btnRegistro);	    
	    panelCentro.add(panelFormulario);
	    add(panelCentro, BorderLayout.CENTER);
	   
	}
	

	
	private void crearLogo() {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(450, 110, 100, 100);
		lblLogo.setIcon(cargarIcono("../img/icono.png", 100, 100));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogo,BorderLayout.CENTER);
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










