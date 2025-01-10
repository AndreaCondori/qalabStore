package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarritoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By precioUnitario = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");
    private By precioTotalLocator = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");
    private By obtenerTitulo = By.xpath("//div[1]/h1");
    private By cantidadPedido = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input");
    private  By itemsCarrtito= By.cssSelector(".cart-item");

    public CarritoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public double obtenerPrecioTotalCarrito() {
        WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(precioTotalLocator));
        String precioTexto = precioElemento.getText();
        return parsearPrecio(precioTexto);
    }

    public double obtenerPrecioUnitarioCarrito() {
        WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(precioUnitario));
        return parsearPrecio(precioElemento.getText());
    }

    public double obtenerCantidadCarrito() {
        WebElement cantidadElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(cantidadPedido));
        String cantidadTexto = cantidadElemento.getAttribute("value");
        return parsearPrecio(cantidadTexto);
    }

    public String obtenerTituloCarrito() {
        return driver.findElement(obtenerTitulo).getText();
    }
    private double parsearPrecio(String precioTexto) {
        String precioLimpio = precioTexto.replaceAll("[^0-9.,]", "").replace(",", ".");
        return Double.parseDouble(precioLimpio);
    }

    public List<WebElement> obtenerItemsCarrito(){
       List<WebElement> items = (List<WebElement>) driver.findElements(itemsCarrtito);
       return  items;
    }
    public List<String> obtenerTallasItem(){
        List<WebElement> itemsCarrito=obtenerItemsCarrito();
        List<String>tallasCarrito = new ArrayList<>();
        for (WebElement item: itemsCarrito){
            String talla = item.findElement(By.cssSelector(".product-line-info.dimension .value")).getText().trim();
            tallasCarrito.add(talla);
        }
        return  tallasCarrito;

    }
}
