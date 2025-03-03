package com.nttdata.steps;

import com.nttdata.page.*;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


import com.nttdata.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStoreSteps {
    private WebDriver driver;

    private WebDriverWait wait;
    private LoginPage paginaLogin;
    private HomePage paginaInicio;
    private StoreMenPage paginaHombres;
    private ProductDetailPage paginaDetalleProducto;
    private CarritoPage paginaCarrito;
    public int cantidadPedido;

    public MyStoreSteps(WebDriver driver) {
        this.driver = driver;
        this.paginaLogin = new LoginPage(driver);
        this.paginaInicio = new HomePage(driver);
        this.paginaHombres = new StoreMenPage(driver);
        this.paginaDetalleProducto = new ProductDetailPage(driver);
        this.paginaCarrito = new CarritoPage(driver);
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void iniciarSesion(String usuario, String clave) {
        paginaLogin.IniciarSesion();
        paginaLogin.ingresarUsuario(usuario);
        paginaLogin.ingresarClave(clave);
        paginaLogin.hacerClicEnLogin();
        if (!paginaLogin.estaAutenticado()) {
            throw new AssertionError("Error: No se pudo autenticar con las credenciales proporcionadas.");
        }

    }
    public boolean navegarACategoria(String categoria,String subcategoria) {
        try {
            paginaInicio.navegarACategoriaYSubcategoria(categoria, subcategoria);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void seleccionarPrimerProducto() {
        paginaHombres.hacerClicEnPrimerProducto();
    }

    public void agregarProductoAlCarrito(int cantidad) {
        paginaDetalleProducto.establecerCantidadProducto(cantidad);
        paginaDetalleProducto.hacerClicEnAgregarAlCarrito();
    }

    public double obtenerPrecioUnitarioProducto() {
        return paginaDetalleProducto.obtenerPrecioUnitario();
    }

    public double obtenerPrecioTotalPopup() {
        return paginaDetalleProducto.obtenerPrecioTotalPopup();
    }


    public void finalizarCompra() {
        paginaDetalleProducto.hacerClicEnFinalizarCompra();
    }

    public void validarTituloCarrito() {
        String tituloCarrito = paginaCarrito.obtenerTituloCarrito();
        Assert.assertTrue("El título de la página del carrito no es correcto.", tituloCarrito.contains("CARRITO"));
    }

    public void validarConfirmacionPopup() {
        String mensajeConfirmacion = paginaDetalleProducto.obtenerMensajeConfirmacionPopup();
        System.out.printf(mensajeConfirmacion);
        Assert.assertTrue("El mensaje de confirmación no es correcto.",
                mensajeConfirmacion.contains("Producto añadido correctamente a su carrito de compra"));
    }


    public boolean validarCantidadPedidovsCantidadCarrito(){
        if(cantidadPedido==paginaCarrito.obtenerCantidadCarrito())
        {return true;}
        else {
            return false;
        }
    }




}