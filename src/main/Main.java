package main;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.RegistrationController;
import controllers.HomeController;
import controllers.LoginController;
import views.LoginView;
import views.LoginWindow;
import views.MainWindow;
public class Main {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		
		//MainWindow ventanita = new MainWindow();
		//
		LoginWindow ventanita = new LoginWindow();
		LoginView view = ventanita.getLoginView();
		new LoginController(view);
        ventanita.add(view);
        //
		new HomeController(new MainWindow());
        
        //ventanita.setVisible(true);

	}

}
