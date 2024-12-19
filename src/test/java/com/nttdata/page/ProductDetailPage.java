package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductDetailPage {
    private WebDriver driver;
    private WebDriverWait espera;

    private By precioUnitarioLocator = By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[1]");
    private By campoCantidad = By.cssSelector("input[name='qty']");
    private By botonAgregarAlCarrito = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button");
    private By mensajePopupConfirmacion = By.id("myModalLabel");
    private By precioTotalPopup = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]");
    private By botonFinalizarCompra = By.xpath("//*[@id='blockcart-modal']//a[contains(@class, 'btn-primary')]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void establecerCantidadProducto(int cantidad) {
        WebElement elementoCantidad = espera.until(ExpectedConditions.visibilityOfElementLocated(campoCantidad));
        elementoCantidad.click();
        elementoCantidad.sendKeys(Keys.CONTROL + "a"); // Seleccionar todo el texto
        elementoCantidad.sendKeys(Keys.BACK_SPACE);
        elementoCantidad.sendKeys(String.valueOf(cantidad));
    }

    public void hacerClicEnAgregarAlCarrito() {
        WebElement botonAgregar = espera.until(ExpectedConditions.elementToBeClickable(botonAgregarAlCarrito));
        botonAgregar.click();
    }

    public String obtenerMensajeConfirmacionPopup() {
        WebElement elementoConfirmacion = espera.until(ExpectedConditions.visibilityOfElementLocated(mensajePopupConfirmacion));
        return elementoConfirmacion.getText();
    }

    public double obtenerPrecioUnitario() {
        String precioTexto = driver.findElement(precioUnitarioLocator).getText();
        return parsearPrecio(precioTexto);
    }

    public double obtenerPrecioTotalPopup() {
        WebElement elementoPrecio = espera.until(ExpectedConditions.visibilityOfElementLocated(precioTotalPopup));
        String precioTexto = elementoPrecio.getText();
        System.out.println("Texto Precio Total Popup (Después de esperar): " + precioTexto); // Debug
        return parsearPrecio(precioTexto);
    }

    private double parsearPrecio(String precioTexto) {
        if (precioTexto == null || precioTexto.isEmpty()) {
            throw new IllegalArgumentException("El texto del precio está vacío o es nulo.");
        }

        // Elimina caracteres no numéricos y adapta el formato para números decimales
        String precioLimpio = precioTexto.replaceAll("[^0-9.,]", "").replace(",", ".");
        return Double.parseDouble(precioLimpio);
    }

    public void hacerClicEnFinalizarCompra() {
        WebElement botonFinalizar = espera.until(ExpectedConditions.elementToBeClickable(botonFinalizarCompra));
        botonFinalizar.click();
    }
}