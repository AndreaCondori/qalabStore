package com.nttdata.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;
    private static Scenario scenario;
    private WebDriverWait wait;

    public static WebDriver getDriver(){
        return driver;
    }

    @Before(order = 0)
    public void setUp(){
        // Usar WebDriverManager para gestionar automáticamente el geckodriver
        WebDriverManager.firefoxdriver().setup();  // Esto descarga y configura automáticamente el geckodriver

        // Opciones para Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // Si es necesario especificar la ubicación de Firefox

        // Crear el driver de Firefox con las opciones configuradas
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();  // Maximiza la ventana del navegador
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Configura la espera explícita
        //Se ejecutará Automáticamente
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\Documents\\Drivers\\geckodriver-v0.34.0-win32\\geckodriver.exe");
//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        // System.setProperty("webdriver.http.factory", "jdk-http-client");
        //   System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        //  System.setProperty("")

//        driver = new FirefoxDriver(options);
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        driver.quit();
    }

    public static void screenShot(){
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(evidencia, "image/png", "evidencias");
    }
}