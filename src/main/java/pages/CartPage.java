package pages;

import base.BaseTest;
import org.openqa.selenium.By;

public class CartPage {

    static By cartItemName = By.className("inventory_item_name");

    public static String getCartItemName() {
        return BaseTest.driver.findElement(cartItemName).getText();
    }
}