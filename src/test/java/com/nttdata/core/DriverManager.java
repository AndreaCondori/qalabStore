package com.nttdata.core;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        //Se ejecutará Automáticamente
        String os = System.getProperty("os.name").toLowerCase();
        FirefoxOptions options = new FirefoxOptions();

        if (os.contains("win")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\Documents\\Drivers\\geckodriver-v0.34.0-win32\\geckodriver.exe");
            options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            options.setBinary("/usr/bin/firefox");
        }

        // Configuración de ejecución en modo headless
        options.addArguments("--headless"); // Ejecutar sin interfaz gráfica
        options.addArguments("--disable-gpu"); // Evitar problemas gráficos
        options.addArguments("--window-size=1920,1080"); // Definir tamaño de pantalla
        options.addArguments("--no-sandbox"); // Recomendado para entornos Linux sin GUI
        options.addArguments("--disable-dev-shm-usage"); // Evita errores de memoria en contenedores
        // System.setProperty("webdriver.http.factory", "jdk-http-client");
        //   System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        //  System.setProperty("")

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        if(scenario.isFailed()){
            screenShot();
        }
        driver.quit();
    }

    public static void screenShot(){
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(evidencia, "image/png", "evidencias");
    }
//    @AfterStep
//    public void afterStep(Scenario scenario) {
//        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//        scenario.attach(evidencia, "image/png", "Evidencia del paso");
//    }

}
