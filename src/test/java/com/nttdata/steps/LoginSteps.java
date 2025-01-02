package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;


    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void performLogin(String username, String password) {
        loginPage.ingresarUsuario(username);
        loginPage.ingresarClave(password);
        loginPage.hacerClicEnLogin();
    }

    public boolean isLoginSuccessful() {
        try {
            return loginPage.obtenerMensajeError().isEmpty();
        } catch (Exception e) {
            return true;
        }
    }
}