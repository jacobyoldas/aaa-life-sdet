package pages;

import base.BaseTest;
import org.openqa.selenium.By;

public class LoginPage {


    static By username = By.id("user-name");
    static By password = By.id("password");
    static By loginBtn = By.id("login-button");

    public static void login(String user, String pass) {
        try {
            BaseTest.driver.findElement(username).sendKeys(user);
            BaseTest.driver.findElement(password).sendKeys(pass);
            BaseTest.driver.findElement(loginBtn).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter username/password", e);
        }
    }
}