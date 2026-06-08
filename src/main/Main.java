package main;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.LoginControlador;
import views.CajeroVista;
import views.LoginVista;


public class Main {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		
		//MainWindow ventanita = new MainWindow();
		//
        LoginVista loginVista = new LoginVista();
        new LoginControlador(loginVista);
        loginVista.setVisible(true);
        //
        //CajeroVista cajeroVista = new CajeroVista(admin);
        //ventanita.setVisible(true);

	}

}
