package com.kelebin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    WebElement searchP = driver.findElement(By.id("search_input"));
}
