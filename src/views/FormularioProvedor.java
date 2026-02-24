package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.AppFont;
public class FormularioProvedor extends JFrame {
	public FormularioProvedor() {

		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		setVisible(true);
		
	}

	public void inicializarComponentes() {
		
		JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(AppFont.title());
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		for(int i = 0; i < 20; i++) {
			JLabel lbl = new JLabel("Campo " + i);
			panelComponentes.add(lbl);
			JTextField txt = new JTextField(20);
			panelComponentes.add(txt);
		}
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
	
}
	
	