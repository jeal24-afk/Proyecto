package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.UserController;

public class MainWindow extends JFrame {
	
	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	
	public JMenuItem mItemExit;
	public JButton btnUsers;
	
    private JButton btnVerUsuarios;
    private JButton btnLogout;
	
	public JButton btnHome;
	public UsuariosView usersPanel;
	
	private CardLayout cardLayout;
	private JPanel container;
	
	public MainWindow() {
			
			setSize(800,500);
			setTitle("Mi aplicación");
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setMenu();
	
			createNavbar();
			createViews();
			UsuariosView usuariosView = new UsuariosView();
			add(usuariosView);
			setVisible(true);
			
		}

	public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnHome = new JButton("Inicio");
		btnUsers = new JButton("Usuarios");
		
		navbar.add(btnHome);
		navbar.add(btnUsers);
		
		add(navbar, BorderLayout.NORTH);
	}

	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Bienvenido al Sistema"));
		
		usersPanel = new UsuariosView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
		
	}

	public void showView(String view) {
		cardLayout.show(container, view);
	}
	
	public void setMenu() {
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu archivo = new JMenu("Archivo");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		JMenuItem abrir = new JMenuItem("Abrir");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);
		
		JMenuItem guardar = new JMenuItem("Guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);
		
		archivo.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);
		
		JMenu otraOpcion = new JMenu("Otra opción");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);
		
		JMenu opcion1 = new JMenu("Opción 1");
		otraOpcion.add(opcion1);
		
		JMenuItem opcion3 = new JMenuItem("Opción 3");
		opcion1.add(opcion3);
		
		JMenuItem opcion2 = new JMenuItem("Opción 2");
		otraOpcion.add(opcion2);
		JPanel panel = new JPanel();
		add(panel);
		
	}
	
		public int confirmExit() {
		    return JOptionPane.showConfirmDialog(
		        this,
		        "¿Seguro que deseas regresar? Se perderán todos los datos",
		        "¿Seguro?",
		        JOptionPane.YES_NO_OPTION
		    );
		}
	
	public void mouseExisted (MouseEvent e ) {
	
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseClicked(MouseEvent e) {
		System.out.append("se hizo clic");
		
	}
	
	
}