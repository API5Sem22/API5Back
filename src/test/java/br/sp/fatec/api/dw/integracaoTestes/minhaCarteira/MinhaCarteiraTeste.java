package br.sp.fatec.api.dw.integracaoTestes.minhaCarteira;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.Assert;


public class MinhaCarteiraTeste {

    @Test
    void carteiraSemItems(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://datawarriorsweb.herokuapp.com/vendorWallet");

        WebElement inputBox = driver.findElementByClassName("mdc-text-field__input");
        WebElement searchButton = driver.findElementByClassName("mdc-text-field__icon--leading");

        inputBox.sendKeys("1111111111", Keys.ENTER);


        WebElement table = driver.findElementByClassName("e-emptyrow");


        Assertions.assertEquals(table.getText(), "No records to display");

        driver.quit();
    }

    @Test
    void buscaTodaMinhaCarteira(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://datawarriorsweb.herokuapp.com/vendorWallet");

        WebElement table = driver.findElementById("Grid_content_table");

        Assert.notNull(table.getText());

        driver.quit();
    }

    @Test
     void buscaMinhaCarteiraCnaeTeste(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://datawarriorsweb.herokuapp.com/vendorWallet");

        WebElement inputBox = driver.findElementByClassName("mdc-text-field__input");
        WebElement searchButton = driver.findElementByClassName("mdc-text-field__icon--leading");

       inputBox.sendKeys("9430800", Keys.ENTER);

        searchButton.click();

        driver.quit();

     }

     @Test
     void confereTituloTeste(){

         WebDriverManager.chromedriver().setup();
         ChromeDriver driver = new ChromeDriver();

         driver.get("http://datawarriorsweb.herokuapp.com/vendorWallet");

         WebElement element = driver.findElementByClassName("title");

         String title = element.getText();
         Assertions.assertEquals("Minha Carteira", title);

         driver.quit();
     }

     @Test
     void confereTitutoErro(){

         WebDriverManager.chromedriver().setup();
         ChromeDriver driver = new ChromeDriver();

         driver.get("http://datawarriorsweb.herokuapp.com/vendorWallet");

         WebElement element = driver.findElementByClassName("title");

         String title = element.getText();
         Assertions.assertNotEquals("Carteira", title);

         driver.quit();
     }

    @Test
    void chromeSession() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.quit();
    }

}
