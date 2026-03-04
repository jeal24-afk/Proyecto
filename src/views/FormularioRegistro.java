package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.AppFont;
public class FormularioRegistro extends JFrame {
	public FormularioRegistro() {

		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		inicializarComponentes();
		setVisible(true);	
	}

	public void inicializarComponentes() {
		
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
        setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de Provedor");
		lblTitulo.setFont(AppFont.title());
        lblTitulo.setBounds(100, 20, 250, 25);
        add(lblTitulo,BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 70, 100, 25);
        add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(150, 70, 150, 25);
        add(txtNombre);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 110, 100, 25);
        add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 110, 150, 25);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 150, 100, 25);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 150, 150, 25);
        add(txtPassword);
        
        JLabel lblPasswordConfir = new JLabel("Confirmar:");
        lblPasswordConfir.setBounds(50, 190, 100, 25);
        add(lblPasswordConfir);

        JPasswordField txtPasswordCofir = new JPasswordField();
        txtPasswordCofir.setBounds(150, 190, 150, 25);
        add(txtPasswordCofir);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(130, 250, 120, 30);
        add(btnRegistrar);

		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
	}
	
}
	
	