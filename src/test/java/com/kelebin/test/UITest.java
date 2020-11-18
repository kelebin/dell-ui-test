package com.kelebin.test;


import static org.junit.Assert.*;

import java.io.IOException;

import com.kelebin.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class UITest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.cs-cart.com");
    }

    @After
    public void after() throws Exception {
        // driver.quit();
    }

    @Test
    public void test() throws IOException {

        String productVar = ("T-shirt, Color: Black");

       // Search for any product;
        WebElement searchP = driver.findElement(By.id("search_input"));
        searchP.sendKeys(productVar);
        searchP.submit();

       // Access this product on the table grid;
        WebElement product = driver.findElement(By.name("product_form_280"));
        product.click();

       // Add this product to the cart;

        driver.findElement(By.id("button_cart_280")).click();

       // Check the item on the cart and total price;

        /*WebElement CartDD = driver.findElement(By.id("sw_dropdown_8"));
        CartDD.click();*/

        driver.get("https://demo.cs-cart.com/stores/ecc67790cac3b9b0/cart/");
        //driver.findElement(By.linkText("View cart")).click();
        assertTrue(driver.getPageSource().contains(productVar));
        //assertEquals(productVar,driver.findElement(By.t));

       // Do the Checkout;
        driver.findElement(By.linkText("Proceed to checkout")).click();


       // Select the “Phone Order” Payment;
       // Proceed with the order;
    }

}
