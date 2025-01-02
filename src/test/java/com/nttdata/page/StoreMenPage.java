package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreMenPage {
    private WebDriver driver;

    private By primerProducto = By.cssSelector(".products .product:nth-child(1) .product-title a");

    public StoreMenPage(WebDriver driver) {
        this.driver = driver;
    }

    public void hacerClicEnPrimerProducto() {
        driver.findElement(primerProducto).click();
    }
}
