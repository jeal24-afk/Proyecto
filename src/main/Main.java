package main;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.FormularioController;
import controllers.LoginController;
import views.FormularioRegistro;
import views.LoginView;
import views.LoginWindow;
import views.MainWindow;
public class Main {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		
		//MainWindow ventanita = new MainWindow();
		LoginWindow ventanita = new LoginWindow();
		LoginView view = ventanita.getLoginView();
		new LoginController(view);
        ventanita.add(view);
        
        //FormularioRegistro view2 = new FormularioRegistro();
        //new FormularioController(view2); 
        
        ventanita.setVisible(true);

	}

}
