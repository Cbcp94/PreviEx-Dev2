package Previex.contrato;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    //Url - Credenciales y Login
    String Url = "https://previexappdev.azurewebsites.net/";
    By BotonSesion = By.xpath("//*[@id=\"header\"]/nav/div/button");
    By BoxUsuario = By.id("i0116");
    String usuario = "usrglohits@central.jbgye.org.ec";
    By BoxContrasena = By.id("i0118");
    String contrasena = "s0D4hd@4d8KG";
    By BotonSiguiente = By.id("idSIButton9");
    By BotonNo = By.id("idBtn_Back");
    By cerraSession = By.cssSelector("#header > nav > ul > li > a > span");

    Base login;
//    public LoginTest(WebDriver navegador) {
//        super(navegador);
//    }

    private WebDriver navegador;
    @Before
    public void setUp() {
        login = new Base(navegador);
        navegador = login.chromeDriverConnection();
        login.visit(Url);
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("ingreso test");
    }

    @Test
    public void Loguearme () throws InterruptedException {
        String parentHandle = navegador.getWindowHandle();
        System.out.println("Pagina Principal - " + parentHandle);
        login.click(BotonSesion);
        Set<String> handles = navegador.getWindowHandles();
        //recorrer lista
        for (String handle : handles) {
            System.out.println(handle);
            if(!handle.equals(parentHandle)) {
                //trasladarse a la segunda ventana - inicio de sesión
                navegador.switchTo().window(handle);
                login.type(usuario, BoxUsuario);
                login.click(BotonSiguiente);
                login.type(contrasena, BoxContrasena);
                Thread.sleep(1000);
                System.out.println("estoy dentro");
                login.click(BotonSiguiente);
                login.click(BotonNo);
            }
        }
        //regresear a la primera ventana
        navegador.switchTo().window(parentHandle);
        System.out.println("sali");
    }

    @After
    public void tearDown(){
        login.click(cerraSession);
        //navegador.quit();
    }

}
