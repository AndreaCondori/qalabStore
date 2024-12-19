package com.nttdata.steps;

import com.nttdata.page.*;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


import com.nttdata.page.*;
import org.openqa.selenium.WebDriver;

public class MyStoreSteps {
    private WebDriver driver;
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
    }

    public void iniciarSesion(String usuario, String clave) {
        paginaLogin.IniciarSesion();
        paginaLogin.ingresarUsuario(usuario);
        paginaLogin.ingresarClave(clave);
        paginaLogin.hacerClicEnLogin();
    }

    public boolean navegarACategoria(String categoria, String subcategoria) {
        try {
            paginaInicio.moverCursorSobreMenuRopa(categoria, subcategoria);
            return true; // Navegación exitosa
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false; // Falló la navegación
        }
    }

    public void seleccionarPrimerProducto() {
        paginaHombres.hacerClicEnPrimerProducto();
    }

    public void agregarProductoAlCarrito(int cantidad) {
        paginaDetalleProducto.establecerCantidadProducto(cantidad);
        paginaDetalleProducto.hacerClicEnAgregarAlCarrito();
    }

    public String obtenerMensajeConfirmacionPopup() {
        return paginaDetalleProducto.obtenerMensajeConfirmacionPopup();
    }

    public double obtenerPrecioUnitarioCarrito() {
        return paginaCarrito.obtenerPrecioUnitarioCarrito();
    }
    public double obtenerPrecioUnitarioProducto() {
        return paginaDetalleProducto.obtenerPrecioUnitario();
    }

    public double obtenerPrecioTotalPopup() {
        return paginaDetalleProducto.obtenerPrecioTotalPopup();
    }

    public double obtenerPrecioTotalCarrito() {

        return paginaCarrito.obtenerPrecioTotalCarrito();
    }

    public void finalizarCompra() {
        paginaDetalleProducto.hacerClicEnFinalizarCompra();
    }

    public void validarTituloCarrito() {
        String tituloCarrito = paginaCarrito.obtenerTituloCarrito();
        Assert.assertTrue("El título de la página del carrito no es correcto.", tituloCarrito.contains("CARRITO"));
    }

    public void validarConfirmacionPopup() {
        String mensajeConfirmacion = obtenerMensajeConfirmacionPopup();
        Assert.assertTrue("El mensaje de confirmación no es correcto.",
                mensajeConfirmacion.contains("Producto añadido correctamente a su carrito de compra"));
    }

    public void validarPrecioTotalCarrito(double precioUnitario, int cantidad) {
        double precioEsperado = precioUnitario * cantidad;
        double precioCarrito = obtenerPrecioTotalCarrito();
        Assert.assertEquals("El precio total en el carrito no coincide.", precioEsperado, precioCarrito, 0.01);
    }

    public void validarPrecioTotalPopup(int cantidad) {
        double precioUnitario = obtenerPrecioUnitarioProducto();
        double precioTotalPopup = obtenerPrecioTotalPopup();
        double precioEsperado = precioUnitario * cantidad;

        Assert.assertEquals("El precio total en el popup no coincide.", precioEsperado, precioTotalPopup, 0.01);
    }
    public boolean validarCantidadPedidovsCantidadCarrito(){
        if(cantidadPedido==paginaCarrito.obtenerCantidadCarrito())
        {return true;}
        else {
            return false;
        }
    }
    public boolean estaAutenticado() {
        return paginaLogin.estaAutenticado();
    }

    public String obtenerMensajeError() {
        return paginaLogin.obtenerMensajeError();
    }



}