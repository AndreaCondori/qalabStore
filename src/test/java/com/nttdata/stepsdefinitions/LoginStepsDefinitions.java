package com.nttdata.stepsDefinition;

import com.nttdata.core.DriverManager;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginStepsDefinitions {
    private WebDriver driver;
    private LoginSteps loginSteps;

    public LoginStepsDefinitions() {
        this.driver = DriverManager.getDriver();
        this.loginSteps = new LoginSteps(driver);
    }

    @Given("que estoy en la página de login")
    public void queEstoyEnLaPaginaDeLogin() {
        driver.get("https://qalab.bensg.com/store/login");
    }

    @When("ingreso el usuario {string} y la contraseña {string}")
    public void ingresoElUsuarioYLaContrasena(String username, String password) {
        loginSteps.performLogin(username, password);
    }

    @Then("debería ver la página principal de la tienda")
    public void deberiaVerLaPaginaPrincipalDeLaTienda() {
        Assert.assertTrue("El inicio de sesión falló.", loginSteps.isLoginSuccessful());
    }

    @Then("debería ver un mensaje de error indicando credenciales incorrectas")
    public void deberiaVerUnMensajeDeErrorIndicandoCredencialesIncorrectas() {
        Assert.assertFalse("El mensaje de error no fue mostrado.", loginSteps.isLoginSuccessful());
    }
}