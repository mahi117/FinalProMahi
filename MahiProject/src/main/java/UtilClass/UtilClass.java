package UtilClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilClass {
    WebDriver driver;
    public WebDriverWait wait;
    public UtilClass(WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }
    public static void holdExecution(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
        }
    }
}
