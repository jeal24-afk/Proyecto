package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.AppFont;
public class FormularioRegistro extends JFrame {

	
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextArea txtDescripcion;
	private JCheckBox chkTerminos;

	private JLabel lblErrorNombre;
	private JLabel lblErrorEmail;
	private JLabel lblErrorTerms;
	private JLabel lblErrorDescripcion;
	
	public FormularioRegistro() {

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		add(crearPanelTitulo(), BorderLayout.NORTH);
		add(crearPanelFormulario());
		add(crearPanelBoton(), BorderLayout.SOUTH);
		pack();
		setVisible(true);	
	}
	
	private JPanel crearPanelTitulo() {
		
		JPanel panel = new JPanel();

		JLabel titulo = new JLabel("Registro de Provedor");
		titulo.setFont(AppFont.title());

		panel.add(titulo);

		return panel;
	}
	
	private JScrollPane crearPanelFormulario() {
		
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		txtNombre = new JTextField();
		txtEmail = new JTextField();

		chkTerminos = new JCheckBox("Aceptar términos");
		chkTerminos.setAlignmentX(Component.LEFT_ALIGNMENT);

		txtDescripcion = new JTextArea(4, 20);

		lblErrorNombre = crearPanelError();
		lblErrorEmail = crearPanelError();
		lblErrorTerms = crearPanelError();
		lblErrorDescripcion = crearPanelError();

		/* CREAR PANELES CON COMPONENTES */

		// Nombre
		panel.add(crearCampo("Nombre: ", txtNombre, lblErrorNombre));
		// Email
		panel.add(crearCampo("Email: ", txtEmail, lblErrorEmail));


		panel.add(crearCampo("Descripción:", new JScrollPane(txtDescripcion), lblErrorDescripcion));
		JPanel termsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		termsPanel.add(chkTerminos);
		termsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(crearCampo("", termsPanel, lblErrorTerms));

		return scroll;
	}
	
	private JLabel crearPanelError() {
		JLabel label = new JLabel();
		label.setFont(AppFont.small());
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
	private JPanel crearPanelBoton() {

		JPanel panel = new JPanel();

		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(e -> validarFormulario());

		panel.add(btnValidar);

		return panel;
	}
	
	private JPanel crearCampo(String labelText, Component field, JLabel errorLabel) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);
		panel.add(errorLabel);

		return panel;

	}
	
	private void validarFormulario() {
		resetearErrorLabels();

		boolean valid = true;

		if (!validarNombre()) {
			valid = false;
		}

		if (!validarEmail()) {
			valid = false;
		}
        
        if(!validarTerminos()) 
        	valid = false;
        
        if(!validarDescripcion()) 
        	valid = false;     

		if (valid) {
			JOptionPane.showMessageDialog(this, "Registro exitoso");
		}
		
	}
	

	private void resetearErrorLabels() {
		lblErrorNombre.setText("");
		lblErrorEmail.setText("");
		lblErrorTerms.setText("");
		lblErrorDescripcion.setText("");
	}
		
		private boolean validarNombre() {

			if (txtNombre.getText().trim().isEmpty()) {
				lblErrorNombre.setText("El nombre es obligatorio");
				return false;
			}

			return true;
		}
		

		private boolean validarEmail() {

			if (txtEmail.getText().trim().isEmpty()) {
				lblErrorEmail.setText("El email es obligatorio");
				return false;
			}

			if (!txtEmail.getText().contains("@")) {
				lblErrorEmail.setText("Email inválido");
				return false;
			}

			return true;
		}		
		
		private boolean validarTerminos() {

			if (!chkTerminos.isSelected()) {
				lblErrorTerms.setText("Debe aceptar los términos");
				return false;
			}

			return true;
		}
		
		private boolean validarDescripcion() {

			if (txtDescripcion.getText().trim().length() < 10) {
				lblErrorDescripcion.setText("Descripción mínima 10 caracteres");
				return false;
			}

			return true;
		}
		

	
}
	
	