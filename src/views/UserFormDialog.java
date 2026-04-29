package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.User;
import util.AppFont;

public class UserFormDialog extends JDialog {
	
	private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextArea txtDescripcion;
	public JCheckBox chkTerminos;
	public JLabel lblErrorNombre;
	public JLabel lblErrorEmail;
	public JLabel lblErrorTerms;
	public JLabel lblErrorDescripcion;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnValidar;

    private User user;
    private boolean saved = false;
    
    public UserFormDialog(JFrame parent, User user) {
    	super(parent, true);
    	
    	this.user = user;
    	
    	setTitle(user == null ? "Agregar usuario" : "Editar usuario");
    	
    	setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelFormulario());
        add(crearPanelBoton(), BorderLayout.SOUTH);
        
        loadData();
        
    }
    
	private JPanel crearPanelTitulo() {
	    JPanel panel = new JPanel();
	    JLabel titulo = new JLabel("Registro de Proveedor");
	    titulo.setFont(AppFont.title());
	    panel.add(titulo);
	    return panel;
	}
    
	private JPanel crearPanelBoton() {
	    JPanel panel = new JPanel();
	    btnValidar = new JButton("Registrarse");
	    panel.add(btnValidar);
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
    
	private JLabel crearLabelError() {
	    JLabel label = new JLabel();
	    label.setForeground(Color.RED);
	    return label;
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
	
    private void loadData() {
    	if(user != null) {
    		txtNombre.setText(user.getNombre());
            txtEmail.setText(user.getEmail());
            txtDescripcion.setText(user.getDescripcion());

    	}
    }
	
	  private void save() {
	    	String name = txtNombre.getText();
	    	String email = txtEmail.getText();

	        String description = txtDescripcion.getText();
	        
	        if(user == null) {
	        	user = new User(name, email,  description );
	        }else {
	        	user.setNombre(name);
	        	user.setEmail(email);
	            user.setDescripcion(description);
	        }
	        
	        saved = true;
	        dispose();
	    }
	
    public boolean isSaved() {
    	return saved;
    }
    
    public User getUser() {
    	return user;
    }
}
