package views;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
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
	
	public JTextField txtNombre;
	public JTextField txtEmail;
	public JTextArea txtDescripcion;
	public JCheckBox chkTerminos;
	public JButton btnValidar;
	
	public JLabel lblErrorNombre;
	public JLabel lblErrorEmail;
	public JLabel lblErrorDescripcion;
	public JLabel lblErrorTerms;

public FormularioRegistro() {

    setTitle("Registro");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    add(crearPanelTitulo(), BorderLayout.NORTH);
    add(crearPanelFormulario(), BorderLayout.CENTER);
    add(crearPanelBoton(), BorderLayout.SOUTH);

    setVisible(true);
}

	private JPanel crearPanelTitulo() {
	    JPanel panel = new JPanel();
	    JLabel titulo = new JLabel("Registro de Proveedor");
	    titulo.setFont(AppFont.title());
	    panel.add(titulo);
	    return panel;
	}

	private JScrollPane crearPanelFormulario() {
	
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
	
	    txtNombre = new JTextField();
	    txtEmail = new JTextField();
	    txtDescripcion = new JTextArea(4, 20);
	    chkTerminos = new JCheckBox("Aceptar términos");
	
	    lblErrorNombre = crearLabelError();
	    lblErrorEmail = crearLabelError();
	    lblErrorDescripcion = crearLabelError();
	    lblErrorTerms = crearLabelError();
	
	    panel.add(crearCampo("Nombre:", txtNombre, lblErrorNombre));
	    panel.add(crearCampo("Email:", txtEmail, lblErrorEmail));
	    panel.add(crearCampo("Descripción:", new JScrollPane(txtDescripcion), lblErrorDescripcion));
	    panel.add(crearCampo("", chkTerminos, lblErrorTerms));
	
	    return new JScrollPane(panel);
	}
	
	private JPanel crearPanelBoton() {
	    JPanel panel = new JPanel();
	    btnValidar = new JButton("Registrarse");
	    panel.add(btnValidar);
	    return panel;
	}

	private JPanel crearCampo(String texto, Component campo, JLabel error) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
	    JLabel label = new JLabel(texto);
	
	    panel.add(label);
	    panel.add(campo);
	    panel.add(error);
	
	    return panel;
	}

	private JLabel crearLabelError() {
	    JLabel label = new JLabel();
	    label.setForeground(Color.RED);
	    return label;
	}

	public String getNombre() { 
		return txtNombre.getText(); 
		}
	public String getEmail() { 
		return txtEmail.getText(); 
		}
	public String getDescripcion() { 
		return txtDescripcion.getText(); 
	}
	public boolean isTerminosAceptados() { 
		return chkTerminos.isSelected(); 
	}
	
	public JButton getBtnValidar() { 
		return btnValidar; 
	}
	
	public void setErrorNombre(String msg) { 
		lblErrorNombre.setText(msg); 
	}
	public void setErrorEmail(String msg) { 
		lblErrorEmail.setText(msg); 
	}
	public void setErrorDescripcion(String msg) { 
		lblErrorDescripcion.setText(msg); 
		}
	public void setErrorTerminos(String msg) { 
		lblErrorTerms.setText(msg); 
	}
	public void mostrarMensaje(String msg) {
	    JOptionPane.showMessageDialog(this, msg);
	}

public void limpiarFormulario() {
    txtNombre.setText("");
    txtEmail.setText("");
    txtDescripcion.setText("");
    chkTerminos.setSelected(false);
}
}