package Previex.contrato;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Base {
    private WebDriver navegador; //declarar objeto WebDriver

    public Base(WebDriver navegador) { //crear constructor de la clase - se pasa objeto de tipo WebDriver
        this.navegador = navegador; // objeto declaro igual al driver que se pasa como parametro
    }

    //Crear metodo para la conexion con el navegador chrome
    public WebDriver chromeDriverConnection() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\crist\\Escritorio\\Automatizacion Selenium\\Selenium_Intellij\\ChromeDriver\\chromedriver.exe");
        navegador = new ChromeDriver();
        return navegador;
    }

    //Wraping de los metodos de selenium que necesitemos
    //se esta llamando dentro de los siguientes metodos los metodos del API de Selenium
    public WebElement findElement(By locator) { //En este metodo lo que se esta haciendo es crear un envoltorio donde creamos nuestro porpio metodo
        return navegador.findElement(locator);
    }

    public List<WebElement> findElements(By locator) { //Devuelve la lista de los elementos que estoy pasando como parametro
        return navegador.findElements(locator);
    }

    public String getText(WebElement element) { //Devuelve el texto del elemento que estoy pasando como parametro
        return element.getText();
    }

    public String getText(By locator) { //Devuelve el texto del elemento que estamos bsucando a traves de este localizador
        return navegador.findElement(locator).getText();
    }

    public void type(String inputText, By locator) { //Accion de escribir texto
        navegador.findElement(locator).sendKeys(inputText);
    }

    public void click(By locator) { //realiza la accion de clic
        navegador.findElement(locator).click();
    }

    public Boolean isDisplayed(By locator) { //Me indica si es truu or false el displayed
        try {
            return navegador.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void visit(String url){ //en cargado de recibir la Url y abrir la pagina
        navegador.get(url);
    }

}

