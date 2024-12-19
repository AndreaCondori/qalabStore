package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public static By botonIniciarSesion = By.xpath("//div[@class='user-info']/a");
    private By campoUsuario = By.id("field-email");
    private By campoClave = By.id("field-password");
    private By botonLogin = By.id("submit-login");
    private By mensajeError = By.cssSelector(".alert.alert-danger");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ingresarUsuario(String usuario) {
        driver.findElement(campoUsuario).sendKeys(usuario);
    }

    public void IniciarSesion() {
        driver.findElement(botonIniciarSesion).click();
    }

    public void ingresarClave(String clave) {
        driver.findElement(campoClave).sendKeys(clave);
    }

    public void hacerClicEnLogin() {
        driver.findElement(botonLogin).click();
    }

    public String obtenerMensajeError() {
        return driver.findElement(mensajeError).getText();
    }

    public boolean estaAutenticado() {
        try {
            return driver.findElement(By.cssSelector(".account")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
