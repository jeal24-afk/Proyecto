package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.UserFormDialog;
import views.UsuariosView;

public class UserController {
	
	private UsuariosView view;
    private UserRepository repository;
	private UserTableModel model;

    public UserController(UsuariosView view) {
        this.view = view;
        this.repository = new UserRepository();

        cargarUsuarios();
    }
    private void cargarUsuarios() {
        try {
            List<User> users = repository.getUsers();

    		if(model == null) {
				model = new UserTableModel(users);
				view.setTableModel(model);
			}else {
				model.setUsers(users);
			}
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
    }
    
	private void openForm(User user) {
		
		UserFormDialog dialog = new UserFormDialog(null, user);
		dialog.setVisible(true);
		
		if(dialog.isSaved()) {
			User savedUser = dialog.getUser();
			
			try {
				if(user == null) {
					repository.save(savedUser);
				}else {
					int row = view.getSelectedRow();
					repository.update(row, savedUser);
				}
				
				cargarUsuarios();
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
			
		}
		
	}
}
