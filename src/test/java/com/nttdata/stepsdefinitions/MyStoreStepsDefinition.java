package com.nttdata.stepsdefinitions;
import com.nttdata.core.DriverManager;
import com.nttdata.page.CarritoPage;
import com.nttdata.page.LoginPage;
import com.nttdata.steps.MyStoreSteps;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class MyStoreStepsDefinition {
    private WebDriver driver;
    private MyStoreSteps pasosMyStore;

    public MyStoreStepsDefinition() {
        this.driver = getDriver();
        pasosMyStore = new MyStoreSteps(driver);
    }

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {

        driver.get("https://qalab.bensg.com/store/login");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConUsuarioYClave(String usuario, String clave) {
        pasosMyStore.iniciarSesion(usuario, clave);
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoACategoriaYSubcategoria(String categoria, String subcategoria) {
        boolean resultado = pasosMyStore.navegarACategoria( categoria, subcategoria);
        if (!resultado) {
            Assert.fail("La categoría o subcategoría no existe: " + categoria + " > " + subcategoria);
        }
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesProducto(int cantidad) {
        pasosMyStore.cantidadPedido = cantidad;
        pasosMyStore.seleccionarPrimerProducto();
        pasosMyStore.agregarProductoAlCarrito(cantidad);
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoConfirmacionProducto() {
        pasosMyStore.validarConfirmacionPopup();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoMontoTotalPopup() {
        int cantidad = pasosMyStore.cantidadPedido;
        double precioUnitario = pasosMyStore.obtenerPrecioUnitarioProducto();
        double precioTotalPopup = pasosMyStore.obtenerPrecioTotalPopup();

        double precioEsperado = precioUnitario * cantidad;

        Assert.assertEquals("El precio total en el popup no es correcto.", precioEsperado, precioTotalPopup, 0.01);
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        pasosMyStore.finalizarCompra();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoTituloPaginaCarrito() {
        pasosMyStore.validarTituloCarrito();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void validoPreciosCarrito() {

        Assert.assertTrue("",pasosMyStore.validarCantidadPedidovsCantidadCarrito());
    }
}