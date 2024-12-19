package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    // Constructor
    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    // Métodos de interacción con la página de login
    public void performLogin(String username, String password) {
        loginPage.ingresarUsuario(username);
        loginPage.ingresarClave(password);
        loginPage.hacerClicEnLogin();
    }

    public boolean isLoginSuccessful() {
        try {
            // Asumimos que la ausencia de error indica un login exitoso
            return loginPage.obtenerMensajeError().isEmpty();
        } catch (Exception e) {
            return true; // No hay mensaje de error visible
        }
    }
}