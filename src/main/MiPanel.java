package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MiPanel extends JPanel{
	
	public MiPanel() {
		setLayout(null);
		//Esta opción genera un color a partir de RGB
		setBackground(new Color(102,127,68));
		JButton boton = new JButton("Bienvenido", new ImageIcon("src/img/icono.png"));
		boton.setBounds(10,10,200,50);
		
		try {
			Image icono = ImageIO.read(getClass().getResource("../img/icono.png"));
			icono = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));
		}catch(Exception ex){
			System.out.print("No se encontro la imagen");
		}
		add(boton);
		
		JLabel label = new JLabel("Titulo Lucha"); 
		label.setFont (new Font("Arial", Font.PLAIN, 30)); 
		label.setBounds (10,60,500, 100); add(label);
	
		JTextField textField = new JTextField();
		textField.setFont(new Font("Arial", Font. PLAIN, 30)); 
		textField.setBounds (10,180, 200,50); add(textField);
	
		JPasswordField password = new JPasswordField();
		password. setBounds (10, 240, 200,50);
		password.setFont(new Font("Arial", Font.PLAIN, 30));
		add(password);
	}
	
}
