package controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import models.FormularioModel;
import models.User;
import repository.UserRepository;
import views.FormularioRegistro;
import views.MainWindow;

public class FormularioController {
	
    private FormularioRegistro view;
    private UserRepository repository;
    
    public FormularioController(FormularioRegistro view) {
        this.view = view;
        this.repository = new UserRepository();
        
        init();
    }

    private void init() {
    	
        view.btnValidar.addActionListener(e -> validarFormulario());
    }

    private void validarFormulario() {

        FormularioModel model = new FormularioModel(
                view.txtNombre.getText(),
                view.txtEmail.getText(),
                view.txtDescripcion.getText(),
                view.chkTerminos.isSelected()
        );

        view.lblErrorNombre.setText(model.validarNombre());
        view.lblErrorEmail.setText(model.validarEmail());
        view.lblErrorDescripcion.setText(model.validarDescripcion());
        view.lblErrorTerms.setText(model.validarTerminos());

        if (model.esValido()) {

            User user = new User(
                view.txtNombre.getText(),
                view.txtEmail.getText(),
                "1234"
            );

            registerUser(user);
        }
    }
    
    private void registerUser(User user) {
    	try {
    		
    		repository.save(user);
    		
    		JOptionPane.showMessageDialog(view, "Usuario registrado");
    		
    	}catch(IOException e) {
    		JOptionPane.showMessageDialog(view, e.getMessage());
    	}
    	
    }
}