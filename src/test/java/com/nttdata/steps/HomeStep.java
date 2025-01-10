package com.nttdata.steps;

import com.nttdata.page.ArtPage;
import com.nttdata.page.CarritoPage;
import com.nttdata.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomeStep {
    WebDriver driver;
    HomePage homePage;
    ArtPage artPage;
    CarritoPage carritoPage;
    public HomeStep (WebDriver driver){
        this.driver= driver;
        homePage= new HomePage(driver);
        artPage = new ArtPage(driver);
        carritoPage= new CarritoPage(driver);
    }

    public void nagegarCategoria(String categoria) {
        homePage.navegaraCategoria(categoria);
    }

    public void seleccionarPrimerProducto() {
        artPage.seleccionarPrimerProducto();
    }



    public void agregarProductoTalla(String tallas) {
        String[]listaTallas= tallas.split(",");
        for(int i=0; i<listaTallas.length; i++){
            String talla= listaTallas[i].trim();
            artPage.seleccionarTalla(talla);
            artPage.agregarUnidadProducto();
            if(i<listaTallas.length-1){
                artPage.cerrarPopUp();
            }else {
                artPage.finalizarCompr();
            }
        }

    }

    public void verificarTallaItemsCart(String tallasItem) {
        String[] listaTallasEsperadas = tallasItem.split(",");
        List<String> tallasEnCarrito = carritoPage.obtenerTallasItem();
        assertEquals("Las tallas en el carrito no coinciden con las esperadas",
                Arrays.asList(listaTallasEsperadas), tallasEnCarrito);
    }

    public void verificarPrecioTalla(String tallas, String precios) {
    }
}
