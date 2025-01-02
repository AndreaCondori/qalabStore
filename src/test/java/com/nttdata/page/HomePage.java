package com.nttdata.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By categoriaLocator = By.xpath("//a[contains(@href, 'clothes')]");
    private By subcategoriaLocator;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navegarACategoriaYSubcategoria(String categoria, String subcategoria) {
        Actions acciones = new Actions(driver);

        try {
            categoriaLocator = By.xpath("//a[contains(@href, '" + categoria.toLowerCase() + "')]");
            WebElement categoriaElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(categoriaLocator));

            acciones.moveToElement(categoriaElemento).perform();

            subcategoriaLocator = By.xpath("//li[@class='category']//a[contains(text(), '" + subcategoria + "')]");
            WebElement subcategoriaElemento = wait.until(ExpectedConditions.elementToBeClickable(subcategoriaLocator));

            subcategoriaElemento.click();

        } catch (TimeoutException e) {
            throw new NoSuchElementException("No se encontró la categoría '" + categoria + "' o la subcategoría '" + subcategoria + "'");
        }
    }
}
