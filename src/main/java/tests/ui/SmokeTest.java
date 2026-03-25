package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import tests.utils.ConfigReader;
import java.util.List;

public class SmokeTest extends BaseTest {

    @Test
    public void loginAndValidateCartFlow() {

        LoginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed: user is not on inventory page");

        int itemCount = InventoryPage.getItemCount();
        Assert.assertTrue(itemCount > 0, "No items found on inventory page");

        List<String> items = InventoryPage.getItemNames();
        Assert.assertTrue(!items.isEmpty(),"Item list is empty");

        String expectedItem = InventoryPage.getFirstItemName();

        InventoryPage.addFirstItemToCart();
        InventoryPage.clickCart();

        String actualItem = CartPage.getCartItemName();
        Assert.assertEquals(actualItem, expectedItem, "Cart item does not match selected product");
    }
}