package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.steps.HomeStep;
import com.nttdata.steps.MyStoreSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ProductStepsDefinition {

        HomeStep homeStep;
        WebDriver driver;
        MyStoreSteps myStoreSteps;
    public ProductStepsDefinition() {
        this.driver = DriverManager.getDriver();
        homeStep = new HomeStep(driver);
        myStoreSteps= new MyStoreSteps(driver);
    }
    @When("navego a la categoría {string}")
    public void navegoALaCategoria(String categoria) {
        homeStep.nagegarCategoria(categoria);
    }

    @And("agrego las tallas {string} del primer producto al carrito")
    public void agregoLasTallasDelPrimerProductoAlCarrito(String talla) {
        homeStep.seleccionarPrimerProducto();
        homeStep.agregarProductoTalla(talla);
    }

    @Then("verifico que cada talla {string} del producto se liste como un ítem separado en el carrito")
    public void verificoQueCadaTallaDelProductoSeListeComoUnItemSeparadoEnElCarrito(String tallasItem) {
       homeStep.verificarTallaItemsCart(tallasItem);
    }

    @And("valido que los precios en el carrito sean correctos para las tallas {string} con precios {string}")
    public void validoQueLosPreciosEnElCarritoSeanCorrectosParaLasTallasConPrecios(String tallas, String precios) {
        homeStep.verificarPrecioTalla(tallas, precios);
    }
}
