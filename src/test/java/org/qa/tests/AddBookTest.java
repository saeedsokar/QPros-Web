package org.qa.tests;

import org.checkerframework.checker.units.qual.C;
import org.qa.pages.CartPage;
import org.qa.pages.HomePage;
import org.qa.utilities.WebBaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddBookTest extends WebBaseTest {

    final String testBook = "Thinking in HTML";
    final HomePage homePage = new HomePage();
    final CartPage cartPage = new CartPage();

    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 0)
    public void addBookToCartFromHome(){
        String resultBookTitle = homePage.isItemTitleDisplayed(testBook);
        softAssert.assertEquals(resultBookTitle.contains(testBook),true);
        boolean isPriceDisplayed = homePage.isItemPricesDisplayed();
        softAssert.assertEquals(isPriceDisplayed,true);
        boolean isBookAddedToBasket = homePage.addItemToBasket(testBook);
        softAssert.assertEquals(isBookAddedToBasket,true);
        boolean isShopCartClicked = homePage.clickOnShopCart();
        softAssert.assertEquals(isShopCartClicked,true);
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void checkShopCart(){
        boolean isApplyCouponVisible = cartPage.isApplyCouponVisible();
        softAssert.assertEquals(isApplyCouponVisible,true);
        boolean isBookAddedToCart = cartPage.isBookTitleVisible(testBook);
        softAssert.assertEquals(isBookAddedToCart,true);
        boolean isBookPriceVisible = cartPage.isBookPriceVisible();
        softAssert.assertEquals(isBookPriceVisible,true);
        softAssert.assertAll();
    }
}
