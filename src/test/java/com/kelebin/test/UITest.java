package com.kelebin.test;


import static org.junit.Assert.*;

import java.awt.*;
import java.io.IOException;

import com.kelebin.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        driver.manage().window().maximize();
    }

    @After
    public void after() throws Exception {
         driver.close();
    }

    @Test
    public void test() throws IOException, InterruptedException {

        String productVar = ("T-shirt, Color: Black");

       // Search for any product;
        WebElement searchP = driver.findElement(By.id("search_input"));
        searchP.sendKeys(productVar);
        searchP.submit();

       // Access this product on the table grid;
        WebElement product = driver.findElement(By.name("product_form_280"));
        product.click();

        String prodValue = driver.findElement(By.xpath("//*[@id=\"sec_discounted_price_280\"]")).getText();

       // Add this product to the cart;

        driver.findElement(By.id("button_cart_280")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"sw_dropdown_8\"]/a/span/span[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"dropdown_8\"]/div/div[2]/div[1]/a")).click();


       // Check the item on the cart and total price;

        assertTrue(driver.getPageSource().contains(productVar));

        String totalPrice = driver.findElement(By.xpath("//*[@id=\"sec_product_subtotal_3924321467\"]")).getText();

        assertEquals(totalPrice,prodValue);


       // Do the Checkout;
        driver.findElement(By.xpath("//*[@id=\"tygh_main_container\"]/div[3]/div/div[2]/div/div/div/div[3]/div[2]/a")).click();


       // Select the “Phone Order” Payment;

        WebElement inputAddress = driver.findElement(By.id("litecheckout_s_address"));
        inputAddress.sendKeys("random street");

        WebElement inputEmail = driver.findElement(By.id("litecheckout_email"));
        inputEmail.sendKeys("randomEmail@gmail.com");

        driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);

        Thread.sleep(1000);
        WebElement phoneOrd = driver.findElement(By.xpath("//*[@id=\"payments_2\"]"));
        phoneOrd.click();
       // Proceed with the order;

        Thread.sleep(1000);
        WebElement inputPhone = driver.findElement(By.id("customer_phone"));
        inputPhone.sendKeys("(403) 274-14177");

        WebElement checkBox = driver.findElement(By.name("accept_terms"));
        checkBox.click();

        WebElement placeOrderBtn = driver.findElement(By.id("litecheckout_place_order"));
        placeOrderBtn.click();

        //CAPTCHA :D
        assertTrue(driver.getPageSource().contains("Anti-bot validation"));
    }

}
