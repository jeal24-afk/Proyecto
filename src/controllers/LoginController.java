package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.LoginModel;
import models.User;
import views.LoginView;
import views.MainWindow;
import views.FormularioRegistro;

public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        init();
    }

    private void init() {
    	view.getBtnRegister().addActionListener(e -> registro());
        view.getBtnLogin().addActionListener(e -> login());
        view.getLblRegister().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                registro();
            }
        });
    }

    private void login() {

        view.resetErrorMessages();

        LoginModel model = new LoginModel(
                view.getEmail(),
                view.getPassword()
        );

        view.showEmailError(model.validarEmail());
        view.showPasswordError(model.validarPassword());

        if (!model.camposValidos()) return;

        try {
            if (model.validarCredenciales()) {

                JOptionPane.showMessageDialog(view, "Sesión iniciada");

                new MainWindow();
                view.getWindow().dispose();
            }

        } catch (Exception ex) {
            view.showPasswordError("Credenciales incorrectas");
        }
    }

    private void registro() {
        new FormularioRegistro();
        view.getWindow().dispose();
    }
}