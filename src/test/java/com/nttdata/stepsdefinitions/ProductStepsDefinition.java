package com.nttdata.stepsdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepsDefinition {

    @Given("")
    public void asas(){

    }

    @When("navego a la categoría {string}")
    public void navegoALaCategoria(String arg0, String arg1) {

    }

    @And("agrego la talla {string} del primer producto al carrito")
    public void agregoLaTallaDelPrimerProductoAlCarrito(String arg0, String arg1) {
    }

    @And("valido que el precio para la talla {string} sea correcto")
    public void validoQueElPrecioParaLaTallaSeaCorrecto(String arg0, String arg1) {
    }

    @When("visualizo el carrito")
    public void visualizoElCarrito() {
    }

    @Then("verifico que cada talla del producto se liste como un ítem separado")
    public void verificoQueCadaTallaDelProductoSeListeComoUnItemSeparado() {
    }

    @And("valido que el precio total en el carrito sea la suma de los precios por talla")
    public void validoQueElPrecioTotalEnElCarritoSeaLaSumaDeLosPreciosPorTalla() {
    }
}
