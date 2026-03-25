package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    static By items = By.className("inventory_item");
    static By itemNames = By.className("inventory_item_name");
    static By addToCartBtns = By.cssSelector(".inventory_item button");
    static By cartIcon = By.className("shopping_cart_link");

    public static int getItemCount() {
        try {
            BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(items));
            return BaseTest.driver.findElements(items).size();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get item count from inventory page", e);
        }
    }

    public static List<String> getItemNames() {
        try {
            return BaseTest.driver.findElements(itemNames)
                    .stream()
                    .map(e -> e.getText())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get item names from inventory page", e);
        }
    }

    public static String getFirstItemName() {
        try {
            return getItemNames().get(0);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get first item name", e);
        }
    }

    public static void addFirstItemToCart() {
        try {
            BaseTest.driver.findElements(addToCartBtns).get(0).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to add first item to cart", e);
        }
    }

    public static void clickCart() {
        try {
            BaseTest.driver.findElement(cartIcon).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click cart icon", e);
        }
    }
}