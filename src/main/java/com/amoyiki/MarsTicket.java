package com.amoyiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Selenium submit name to Mars on NASA
 * @author amoyiki
 * @license 2021/3/18
 */

public class MarsTicket {


    public static WebDriver openChrome(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        String path = System.getProperty("user.dir");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("download.default_directory", path);
        options.setExperimentalOption("prefs", hashMap);
        WebDriver webDriver = new ChromeDriver(options);
//        webDriver.manage().window().maximize();
        return webDriver;
    }



    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = openChrome();
        String url = "https://mars.nasa.gov/participate/send-your-name/future";
        webDriver.get(url);
        WebElement firstName = webDriver.findElement(By.id("FirstName"));
        firstName.sendKeys("name1");

        WebElement lastName = webDriver.findElement(By.id("LastName"));
        lastName.sendKeys("name2");

        WebElement countryCode = webDriver.findElement(By.id("CountryCode"));
        new Select(countryCode).selectByValue("CN");

        WebElement postalCode = webDriver.findElement(By.id("PostalCode"));
        postalCode.sendKeys("");

        WebElement email = webDriver.findElement(By.id("Email"));
        email.sendKeys("test@mail.com");
        String currWin = webDriver.getWindowHandle();
        WebElement searchSubmit = webDriver.findElement(By.name("SearchSubmit"));
        searchSubmit.click();
        Set<String> handles = webDriver.getWindowHandles();
        for (String s : handles) {
            if (!s.equals(currWin)) {
                webDriver.switchTo().window(s);
            }
        }
        currWin = webDriver.getWindowHandle();
        Thread.sleep(3000);

        WebElement downloadTicket = webDriver.findElement(By.id("downloadTicket"));
        downloadTicket.click();
        System.out.println("all operation done!");
        Thread.sleep(5000);
        webDriver.quit();

    }
}