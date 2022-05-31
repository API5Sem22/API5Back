package br.sp.fatec.api.dw.integracaoTestes.clientesLivres;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.Assert;

public class ClientesLivresTeste {

    @Test
    void confereTitulo(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://datawarriorsweb.herokuapp.com/vendorFreeWallets");

        WebElement element = driver.findElementByClassName("title");

        String title = element.getText();
        Assertions.assertEquals("Minha Carteira", title);

        driver.quit();
    }


    @Test
    void buscaClientesLivresTeste(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://datawarriorsweb.herokuapp.com/vendorFreeWallets");

        WebElement table = driver.findElementById("Grid_content_table");

        Assert.notNull(table.getText());

        driver.quit();
    }
}
