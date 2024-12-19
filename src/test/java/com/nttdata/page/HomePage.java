package com.nttdata.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait espera;

    // Localizadores
    private By localizadorCategoria = By.xpath("//li[@id='category-3']/a");
    private By localizadorSubcategoria =  By.xpath("//li[@id='category-4']/a");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Métodos
    public void moverCursorSobreMenuRopa(String categoria, String subcategoria) {
        Actions acciones = new Actions(driver);
        try {


            WebElement elementoMenu = espera.until(ExpectedConditions.visibilityOfElementLocated(localizadorCategoria));
            acciones.moveToElement(elementoMenu).perform();

            // Hacer clic en la subcategoría
            WebElement elementoSubMenu = espera.until(ExpectedConditions.elementToBeClickable(localizadorSubcategoria));
            elementoSubMenu.click();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("No se encontró la categoría o subcategoría: " + categoria + " > " + subcategoria);
        }
    }




    public boolean navegarACategoriaHombres() {
        try {
            // Navega directamente a la categoría "3" y subcategoría "4" para Hombres
            moverCursorSobreMenuRopa("3", "4");
            return true; // Navegación exitosa
        } catch (NoSuchElementException e) {
            System.out.println("Error al navegar a la categoría Hombres: " + e.getMessage());
            return false; // Fallo en la navegación
        }
    }
}
