package controllers;

import modelo.Usuario;
import modelo.UsuarioConexion;
import views.LoginVista;
import views.AdminVista;
import views.CajeroVista;

public class LoginControlador {

    private LoginVista  vista;
    private UsuarioConexion  usuarioDAO;

    public LoginControlador(LoginVista vista) {
        this.vista      = vista;
        this.usuarioDAO = new UsuarioConexion();
        initListeners();
    }

    private void initListeners() {
        vista.getBtnIngresar().addActionListener(e -> iniciarSesion());
        vista.getTxtPassword().addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String user = vista.getTxtUsuario().getText().trim();
        String pass = new String(vista.getTxtPassword().getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarError("Por favor ingresa usuario y contraseña.");
            return;
        }

        Usuario u = usuarioDAO.autenticar(user, pass);

        if (u == null) {
            vista.mostrarError("Usuario o contraseña incorrectos.");
            vista.limpiarCampos();
            return;
        }

        vista.dispose();

        if ("Administrador".equals(u.getRol())) {
            AdminVista adminVista = new AdminVista(u);
            new AdminControlador(adminVista, u);
            adminVista.setVisible(true);
        } else {
            CajeroVista cajeroVista = new CajeroVista(u);
            new CajeroControlador(cajeroVista, u);
            cajeroVista.setVisible(true);
        }
    }
}
