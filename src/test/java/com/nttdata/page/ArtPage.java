package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ArtPage {

    private WebDriver driver;
    public ArtPage(WebDriver driver){
        this.driver= driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));

    }

WebDriverWait wait;
    private By primerProducto= By.cssSelector(".products .product:nth-child(1) .product-title a");
    private By  dropDownDimension = By.id("group_3");
    private By botonAgregarProducto = By.cssSelector(".btn.btn-primary.add-to-cart");
    private By continuarComprando= By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/button");
    private By btnfinalizarCompra=By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");
    private By btnCerrarPopUp= By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[1]/button/span/i");

    public  void seleccionarPrimerProducto(){
        WebElement productoPrimero = driver.findElement(primerProducto);
        wait.until(ExpectedConditions.elementToBeClickable(productoPrimero)).click();
    }

    public  void  seleccionarTalla(String talla){
        wait.until(ExpectedConditions.elementToBeClickable(dropDownDimension));

        Select selectDimension = new Select(driver.findElement(dropDownDimension));
        selectDimension.selectByVisibleText(talla);

    }

    public void agregarUnidadProducto() {
        driver.findElement(botonAgregarProducto).click();

    }
    public void cerrarPopUp() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCerrarPopUp)).click();
        refrescarPagina();
    }

    public void refrescarPagina() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(dropDownDimension));
    }
    public void  finalizarCompr(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(btnfinalizarCompra)).click();
    }
}
