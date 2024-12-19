package com.nttdata.stepsdefinitions;
import com.nttdata.core.DriverManager;
import com.nttdata.page.CarritoPage;
import com.nttdata.page.LoginPage;
import com.nttdata.steps.MyStoreSteps;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class MyStoreStepsDefinition {
    private WebDriver driver;
    private MyStoreSteps pasosMyStore;

    public MyStoreStepsDefinition() {
        driver = DriverManager.getDriver();
        pasosMyStore = new MyStoreSteps(driver);
    }

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        driver.get("https://qalab.bensg.com/store/login");
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
        pasosMyStore.cantidadPedido= pasosMyStore.cantidadPedido;
        pasosMyStore.seleccionarPrimerProducto();
        pasosMyStore.agregarProductoAlCarrito(cantidad);
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoConfirmacionProducto() {
        pasosMyStore.validarConfirmacionPopup();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoMontoTotalPopup() {
        double precioUnitario = pasosMyStore.obtenerPrecioUnitarioProducto();
        double precioTotalPopup = pasosMyStore.obtenerPrecioTotalPopup();
        int cantidad = 2; // Cantidad fija según el Gherkin

        double precioEsperado = precioUnitario * cantidad;

        // Log para verificar los valores
        System.out.println("Precio Unitario: " + precioUnitario);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio Total Esperado: " + precioEsperado);
        System.out.println("Precio Total Popup: " + precioTotalPopup);

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