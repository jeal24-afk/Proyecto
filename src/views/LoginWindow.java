<<<<<<< HEAD
package views;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LoginWindow extends JFrame { 
	
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Mi Aplicación V2.0");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		LoginView panelito = new LoginView();
		add(panelito);
		
		setVisible(true);
		
		//validate();
		//repaint();
	}
	
=======
package views;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LoginWindow extends JFrame { 
	
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Mi Aplicación V2.0");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		LoginView panelito = new LoginView();
		add(panelito);
		
		setVisible(true);
		
		//validate();
		//repaint();
	}
	
>>>>>>> b645570da8c443768160a1a4a69b9a82623fd605
}