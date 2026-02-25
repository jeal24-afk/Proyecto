package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import components.RoundButton;
import components.TextPrompt;

public class LoginView extends JPanel{
	
	Font fuente;
	
	public LoginView() {
		
		fuente = new Font("Arial", Font.PLAIN, 14);
		setLayout(new BorderLayout());
		
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		crearBotones();
		crearLogo();
		crearFormulario();
	}
	
	private void crearBotones() {
		
		JButton boton = new JButton("Login");
		boton.setBounds(400,700,150,30);
		boton.setBackground(Color.GREEN);
		boton.setToolTipText("Haz click aquí");
		boton.setFont(fuente);
				
		add(boton,BorderLayout.CENTER);
		
	}
	
	private void crearLogo() {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(400, 50, 100, 100);
		lblLogo.setIcon(cargarIcono("../img/icono.png", 100, 100));
		add(lblLogo,BorderLayout.CENTER);
	}
	
	private void crearFormulario() {

		
		int lblX = 200, y = 200, txtX = 300;
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(fuente);
		lblEmail.setBounds(lblX,y,200,40);
		add(lblEmail,BorderLayout.CENTER);
		
		JTextField txtEmail = new JTextField();
		TextPrompt promptEmail = new TextPrompt("Ingresa tu usuario", txtEmail);
		txtEmail.setFont(fuente);
		txtEmail.setBounds(txtX,y,200,40);
		add(txtEmail,BorderLayout.CENTER);
		
		JLabel lblEmailRequerido = new JLabel("El email es requerido.");
		lblEmailRequerido.setBounds(txtX, y+35, 200, 30);
		lblEmailRequerido.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequerido.setForeground(Color.RED);
		add(lblEmailRequerido,BorderLayout.CENTER);
		
		y += 70;
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(lblX,y,200,40);
		add(lblContrasena,BorderLayout.CENTER);
		
		JPasswordField contrasena = new JPasswordField();
		TextPrompt promptContrasena = new TextPrompt("Ingresa tu contraseña", contrasena);
		contrasena.setFont(fuente);
		contrasena.setBounds(txtX,y,200,40);
		add(contrasena,BorderLayout.CENTER);
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










